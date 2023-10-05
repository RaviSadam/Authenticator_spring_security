package com.springboot.userregiauth.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.userregiauth.ModelClasses.Role;

public interface RoleRepo extends JpaRepository<Role,Integer> {
    public Role findOneByName(String name); 
    
}
