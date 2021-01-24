package com.restfulwebservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    //@RequestMapping(method = RequestMethod.GET,path = "/hello-world")
    @GetMapping(path = "/hello-world")
    public String helloWorld()
    {
        return "Hello world";
    }

    @GetMapping(path = "/hello-world-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name)
    {
        return new HelloWorldBean(String.format("Hello world %s",name));
    }

    //example for internationalization
    @GetMapping("/hello-world-internationalized")
    //public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required = false) Locale locale) --or
    public String helloWorldInternationalized()
    {
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }
}
