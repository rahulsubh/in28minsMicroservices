package com.rahul.Controller;

import com.rahul.Dao.ApiResponse;
import com.rahul.Exceptions.PostNotFoundException;
import com.rahul.Exceptions.UserNotFoundException;
import com.rahul.Repository.UserRepo;
import com.rahul.model.Post;
import com.rahul.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostsController {

    private final UserRepo userRepo;

    @GetMapping("/jpa/users/{userId}/posts")
    public List<Post> getPostsForUser(@PathVariable int userId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new UserNotFoundException(String.valueOf(userId))
        );
        return user.getPosts();
    }

    @GetMapping("/jpa/users/{userId}/posts/{postId}")
    public Post getPostForUser(@PathVariable int userId, @PathVariable int postId){
        User user = userRepo.findById(userId).orElseThrow(
                () -> new UserNotFoundException(String.valueOf(userId))
        );
        return user
                .getPosts()
                .stream()
                .filter(post -> post.getId() == postId)
                .findFirst()
                .orElseThrow(() -> new PostNotFoundException(
                        String.format("Post with id %d not found for user %d", postId, userId
                )));
    }
}
