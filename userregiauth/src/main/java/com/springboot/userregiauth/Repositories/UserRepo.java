package com.springboot.userregiauth.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.userregiauth.ModelClasses.User;

public interface UserRepo extends JpaRepository<User,Long> {
    //gives the user details
    User findByName(String username);
    
}