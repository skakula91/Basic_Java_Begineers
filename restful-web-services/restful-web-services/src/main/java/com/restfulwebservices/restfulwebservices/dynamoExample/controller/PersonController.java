package com.restfulwebservices.restfulwebservices.dynamoExample.controller;

import com.restfulwebservices.restfulwebservices.dynamoExample.Person;
import com.restfulwebservices.restfulwebservices.dynamoExample.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/person")
    public Person savePerson(@RequestBody Person person)
    {
        return personRepository.create(person);
    }

    @GetMapping("/person/{id}")
    public Person findPerson(@PathVariable String id)
    {
        return personRepository.findPersonById(id);
    }

    @DeleteMapping("/person")
    public String  findPerson(@RequestBody Person person)
    {
        return personRepository.delete(person);
    }

    @PutMapping("/person")
    public String updatePerson(@RequestBody Person person)
    {
        return personRepository.edit(person);
    }
}
