package com.restfulwebservices.restfulwebservices.User.controller;

import com.restfulwebservices.restfulwebservices.User.repository.UserDaoService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.restfulwebservices.restfulwebservices.User.entity.*;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
//jackson-dataformat-xml is used to send xml response
@RestController
public class UserController {
    @Autowired
    private UserDaoService service;
    //Retrieve all users
    @GetMapping("/users")
    public List<User> retrieveAllUsers()
    {
        return service.findAll();
    }

    // Get user by id
    @GetMapping("/users/{userId}")
    public EntityModel<User> getUserById(@PathVariable int userId)
    {
        User user =  service.findOne(userId);
        if(user == null)
            throw new UserNotFoundException("id-"+userId);

        // use Hateoas to generate corresponding urls to the data
        EntityModel<User> resource = EntityModel.of(user);
        WebMvcLinkBuilder linkTo =  linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity saveUser(@Valid @RequestBody User user)
    {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUserById(@PathVariable int userId)
    {
        User user =  service.deleteById(userId);
        if(user == null)
            throw new UserNotFoundException("id-"+userId);
    }
}
