package com.restfulwebservices.restfulwebservices.lombokExample;

import lombok.*;

//for generating setter
//@Setter
// no args constructor
//data annotation can be used for setting getters,setters, tostring
@Data
@NoArgsConstructor
//Used for builder pattern
@Builder
@AllArgsConstructor
//@ToString
public class Student {
    private long id;
    private String firstName;
    private String lastName;
}
