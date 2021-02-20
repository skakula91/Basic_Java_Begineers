package com.test.web.application.first.springbootfirstwebapplication.Controllers;

import com.test.web.application.first.springbootfirstwebapplication.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("name")
public class WelcomeController {

//    @Autowired
//    LoginService service;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showWelcomePage(ModelMap map)
    {
        map.put("name",getLoggedinUserName());
        return "welcome";
    }
    private  String getLoggedinUserName()
    {
       Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       if(principal instanceof UserDetails)
       {
           String username = ((UserDetails)principal).getUsername();
           return username;
       }
       return principal.toString();
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String showWelcomePage(ModelMap map, @RequestParam String name,  @RequestParam String password)
//    {
//        boolean isValidUser = service.validateUser(name,password);
//        if(!isValidUser)
//        {
//            map.put("invalid","invalid credentials");
//            return "login";
//        }
//        map.put("name",name);
//        return "welcome";
//    }
}
