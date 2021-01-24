package com.restfulwebservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//For dynamic mapping define the filter we create in the controller on the bean
@JsonFilter("testBeanFilter")
//ignore properties in the response output
//@JsonIgnoreProperties(value = {"field1","field3"})
public class TestBean {
    private String field1;
    private String field2;
    //ignore only one field- better way to ignore rather than adding attribute on class
    //@JsonIgnore
    private String field3;

    public TestBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }
}
