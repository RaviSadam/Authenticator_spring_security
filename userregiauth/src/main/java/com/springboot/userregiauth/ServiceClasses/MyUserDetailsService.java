package com.springboot.userregiauth.ServiceClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.userregiauth.ModelClasses.User;
import com.springboot.userregiauth.Repositories.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByName(username);
        if(user==null){
            throw new UsernameNotFoundException("username not founded");
        }
        return user;
    }
    
}
