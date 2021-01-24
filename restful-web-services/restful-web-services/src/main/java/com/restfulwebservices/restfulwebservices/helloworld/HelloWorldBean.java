package com.restfulwebservices.restfulwebservices.helloworld;

public class HelloWorldBean {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HelloWorldBean(String description) {
        this.description = description;
    }
}
