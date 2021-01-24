package com.restfulwebservices.restfulwebservices.User.controller;

import com.restfulwebservices.restfulwebservices.User.entity.*;
import com.restfulwebservices.restfulwebservices.User.repository.PostRepository;
import com.restfulwebservices.restfulwebservices.User.repository.UserRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
//jackson-dataformat-xml is used to send xml response
@RestController
public class UserJPAController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    //Retrieve all users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers()
    {
        return userRepository.findAll();
    }

    // Get user by id
    @GetMapping("/jpa/users/{userId}")
    public EntityModel<User> getUserById(@PathVariable int userId)
    {
        Optional<User> user =  userRepository.findById(userId);

        if(!user.isPresent())
            throw new UserNotFoundException("id-"+userId);

        // use Hateoas to generate corresponding urls to the data
        EntityModel<User> resource = EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo =  linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity saveUser(@Valid @RequestBody User user)
    {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/jpa/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{userId}")
    public void deleteUserById(@PathVariable int userId)
    {
        userRepository.deleteById(userId);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveUserPosts(@PathVariable int id)
    {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException("user not found with id"+id);

        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPosts(@PathVariable int id, @RequestBody Post post)
    {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException("user not found with id"+id);

        User userSaving = user.get();
        post.setUser(userSaving);

        postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
