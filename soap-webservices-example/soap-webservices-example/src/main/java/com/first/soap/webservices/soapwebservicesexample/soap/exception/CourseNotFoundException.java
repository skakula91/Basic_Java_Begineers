package com.first.soap.webservices.soapwebservicesexample.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT)
public class CourseNotFoundException extends RuntimeException {

    private  static final  long serialVersionUID  = 12345454355l;

    public CourseNotFoundException(String message) {
        super(message);
    }
}
