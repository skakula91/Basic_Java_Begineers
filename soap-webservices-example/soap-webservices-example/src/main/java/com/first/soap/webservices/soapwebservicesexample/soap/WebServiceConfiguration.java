package com.first.soap.webservices.soapwebservicesexample.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import javax.security.auth.callback.CallbackHandler;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@EnableWs
// tells this is the configuration file
@Configuration
public class WebServiceConfiguration extends WsConfigurerAdapter {
    // MessageDispatcherServlet - routes the request to the corresponding endPoint
     // inputs: Application context, url -> /ws/*

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context)
    {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(messageDispatcherServlet,"/ws/*");
    }

    //create wsdl
    // /ws/courses.wsdl
    // course-detail.wsdl

    // Bean to create wsdl
    @Bean(name = "courses")
    public DefaultWsdl11Definition DefaultWsdl11Definition(XsdSchema coursesSchema)
    {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();

        // PortType - CoursePort
        // Namespace - http://examplespringbootwebservice/courses
        definition.setPortTypeName("coursePort");
        definition.setTargetNamespace("http://examplespringbootwebservice/courses");
        definition.setLocationUri("/ws");
        definition.setSchema(coursesSchema);
        return definition;
    }

    @Bean
    public XsdSchema coursesSchema()
    {
        ClassPathResource path = new  ClassPathResource("course-detail.xsd");
        return new SimpleXsdSchema(path);
    }

    //XWS security interceptor
    @Bean
    public XwsSecurityInterceptor securityInterceptor()
    {
        XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
        securityInterceptor.setCallbackHandler(callbackHandler());
        securityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
        // callback handler -> simple password validation callback handler
        // Security Policy -> securityPolicy.xml
        return securityInterceptor;
    }

    @Bean
    public SimplePasswordValidationCallbackHandler callbackHandler() {
        SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
        handler.setUsersMap(Collections.singletonMap("user","password"));
        return handler;
    }

    //add it to list of interceptor
    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(securityInterceptor());
    }
}
