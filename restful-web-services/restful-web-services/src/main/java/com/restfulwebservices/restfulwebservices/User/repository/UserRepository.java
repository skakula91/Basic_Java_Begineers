package com.restfulwebservices.restfulwebservices.User.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.restfulwebservices.restfulwebservices.User.entity.*;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
