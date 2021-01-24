package com.restfulwebservices.restfulwebservices.User.repository;

import com.restfulwebservices.restfulwebservices.User.entity.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int userCount = 3;
    static {
        users.add(new User(1,"kiran",new Date()));
        users.add(new User(2,"sai",new Date()));
        users.add(new User(3,"shravya",new Date()));
    }

    public List<User> findAll()
    {
        return users;
    }

    public User save(User user)
    {
        if(user.getId() == null)
        {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id)
    {
        Optional<User> user =  users.stream().filter(u -> u.getId() == id).findFirst();
        return user.orElse(null);
    }

    public User deleteById(int id)
    {
        Iterator<User> iterator = users.iterator();

        while(iterator.hasNext())
        {
            User user = iterator.next();
            if(user.getId() == id)
            {
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
