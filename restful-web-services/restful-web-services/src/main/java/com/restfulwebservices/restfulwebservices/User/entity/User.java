package com.restfulwebservices.restfulwebservices.User.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.*;

// tag is used for swagger documentation
@ApiModel(description = "user information")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "name should have atleast 2 characters")
    private String name;
    @Past
    @ApiModelProperty(notes = "birth date should be in the past")
    private Date birthDate;

    @OneToMany(mappedBy = "user")
    private List<com.restfulwebservices.restfulwebservices.User.entity.Post> posts;

    public List<com.restfulwebservices.restfulwebservices.User.entity.Post> getPosts() {
        return posts;
    }

    public void setPosts(List<com.restfulwebservices.restfulwebservices.User.entity.Post> posts) {
        this.posts = posts;
    }

    protected User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }
}
