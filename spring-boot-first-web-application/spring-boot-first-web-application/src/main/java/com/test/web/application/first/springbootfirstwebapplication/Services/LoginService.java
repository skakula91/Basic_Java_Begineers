package com.test.web.application.first.springbootfirstwebapplication.Services;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public boolean validateUser(String userid, String password)
    {
        if(userid.equalsIgnoreCase("sai") && password.equalsIgnoreCase("test"))
        {
            return  true;
        }
        return  false;
    }
}
