package com.first.soap.webservices.soapwebservicesexample.soap.service;

import com.first.soap.webservices.soapwebservicesexample.soap.bean.Course;
import com.sun.net.httpserver.Authenticator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class CourseDetailService {
    private static List<Course> courses = new ArrayList<>();
    static
    {
        Course course1 = new Course(1,"Course1", "Description1");
        courses.add(course1);
        Course course2 = new Course(2,"Course2", "Description2");
        courses.add(course2);
        Course course3 = new Course(3,"Course3", "Description3");
        courses.add(course3);
        Course course4 = new Course(4,"Course4", "Description4");
        courses.add(course4);

    }

    //Find course by id
    public Course findById(int id)
    {
        Optional<Course> matchingObject = courses.stream().filter(c -> c.getId() == id).findFirst();
        return matchingObject.orElse(null);
    }

    //Get courses
    public List<Course> findAll() {
        return courses;
    }

    public enum Status
    {
        SUCCESS,FAILURE
    }

    // delete course
    public Status deleteById(int id)
    {

        // using iterator and lambda
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext())
        {
            Course course = iterator.next();
            if(course.getId() == id)
            {
                iterator.remove();
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;

//        Optional<Course> matchingObject = courses.stream().filter(c -> c.getId() == id).findFirst();
//        if(matchingObject == null)
//        {
//            return 0;
//        }
//        courses.remove(matchingObject);
//        return 1;
    }
    //update course
}
