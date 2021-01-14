package com.first.soap.webservices.soapwebservicesexample.soap;

import com.first.soap.webservices.soapwebservicesexample.soap.bean.Course;
import com.first.soap.webservices.soapwebservicesexample.soap.exception.CourseNotFoundException;
import com.first.soap.webservices.soapwebservicesexample.soap.service.CourseDetailService;
import examplespringbootwebservice.courses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CourseDetailEndpoint {

    @Autowired
    CourseDetailService service;
    // method
    // input - GetCourseDetailsRequest
    // output - GetCourseDetailsResponse
    // namespace - http://examplespringbootwebservice/courses
    // name - GetCourseDetailsRequest

    @PayloadRoot(namespace = "http://examplespringbootwebservice/courses", localPart = "GetCourseDetailsRequest")
    //tag used to convert java object to xml
    @ResponsePayload
    public GetCourseDetailsResponse ProcessCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request)
    {
        Course course = service.findById(request.getId());

        if(course == null)
            throw new CourseNotFoundException("Invalid Course Id"+ request.getId());
        return mapCourseDetails(course);
    }

    private GetCourseDetailsResponse mapCourseDetails(Course course) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        response.setCourseDetails(mapCourse(course));
        return response;
    }


    @PayloadRoot(namespace = "http://examplespringbootwebservice/courses", localPart = "GetAllCourseDetailsRequest")
    //tag used to convert java object to xml
    @ResponsePayload
    public GetAllCourseDetailsResponse ProcessAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request)
    {
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
        List<Course> courses = service.findAll();

        return mapAllCourseDetails(courses);
    }


    @PayloadRoot(namespace = "http://examplespringbootwebservice/courses", localPart = "DeleteCourseDetailsRequest")
    //tag used to convert java object to xml
    @ResponsePayload
    public DeleteCourseDetailsResponse ProcessDeleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request)
    {
        DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
        CourseDetailService.Status status = service.deleteById(request.getId());

        response.setStatus(mapStatus(status));
        return response;
    }

    private examplespringbootwebservice.courses.Status mapStatus(CourseDetailService.Status status)
    {
        if(status == CourseDetailService.Status.FAILURE)
            return Status.FAILURE;
        else
            return Status.SUCCESS;
    }


    private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses)
    {
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
        for(Course course:courses)
        {
            CourseDetails mapCourse = mapCourse(course);
            response.getCourseDetails().add(mapCourse);
        }
        return response;
    }


    private CourseDetails mapCourse(Course course) {
        CourseDetails details = new CourseDetails();
        details.setId(course.getId());
        details.setName(course.getName());
        details.setDescription(course.getDescription());
        return details;
    }
}
