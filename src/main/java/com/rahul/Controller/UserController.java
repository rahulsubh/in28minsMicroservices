package com.rahul.Controller;

import com.rahul.Dao.ApiResponse;
import com.rahul.Exceptions.UserNotFoundException;
import com.rahul.Dao.UserDaoService;
import com.rahul.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{userId}")
    public EntityModel<User> getUser(@PathVariable int userId) {
        User user = userDaoService.findById(userId);
        if (user == null){
            throw new UserNotFoundException(String.valueOf(userId));
        }
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userDaoService.saveUser(user);
        URI location = fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int userId) {
       userDaoService.deleteById(userId);
       return ResponseEntity.ok(
               ApiResponse.builder()
                       .message("User deleted successfully")
                       .status(true)
                       .build()
       );
    }
}
