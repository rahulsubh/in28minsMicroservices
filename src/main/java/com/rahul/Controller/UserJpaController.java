package com.rahul.Controller;

import com.rahul.Dao.ApiResponse;
import com.rahul.Exceptions.UserNotFoundException;
import com.rahul.Repository.PostRepo;
import com.rahul.Repository.UserRepo;
import com.rahul.model.Post;
import com.rahul.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequiredArgsConstructor
public class UserJpaController {

    private final UserRepo userRepo;
    private final PostRepo postRepo;

    @GetMapping("/jpa/users")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/jpa/users/{userId}")
    public EntityModel<User> getUser(@PathVariable int userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null){
            throw new UserNotFoundException(String.valueOf(userId));
        }
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userRepo.save(user);
        URI location = fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int userId) {
        userRepo.deleteById(userId);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("User deleted successfully")
                        .status(true)
                        .build()
        );
    }

    @PostMapping("/jpa/users/{userId}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable int userId, @Valid @RequestBody Post post){
        User user = userRepo.findById(userId).orElseThrow(
                () -> new UserNotFoundException(String.valueOf(userId))
        );
        post.setUser(user);
        Post savedPost = postRepo.save(post);
        URI uri = fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
