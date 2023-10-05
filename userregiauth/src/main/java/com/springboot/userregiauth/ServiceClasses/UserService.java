package com.springboot.userregiauth.ServiceClasses;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.userregiauth.ModelClasses.Role;
import com.springboot.userregiauth.ModelClasses.User;
import com.springboot.userregiauth.ModelClasses.UserDto;
import com.springboot.userregiauth.Repositories.RoleRepo;
import com.springboot.userregiauth.Repositories.UserRepo;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo,RoleRepo roleRepo,PasswordEncoder passwordEncoder){
        this.userRepo=userRepo;
        this.roleRepo=roleRepo;
        this.passwordEncoder=passwordEncoder;
    }


    public boolean registerNewUser(UserDto userDto){
        Set<String> roles=userDto.getRole();
        Set<Role> userRoles;
        try{
            userRoles=this.getOrCreateRole(roles);
            if(userRoles==null)
                throw new Exception("");
        }
        catch(Exception exception){
            return false;
        }
        User user=new User();
        String password=passwordEncoder.encode(userDto.getPassword());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(password);
        user.setGender(userDto.getGender());
        user.setRoles(userRoles);
        try{
            user=userRepo.save(user);
        }
        catch(Exception exception){
            return false;
        }
        return user!=null;
    }
    private Set<Role> getOrCreateRole(Set<String> roles){
        Set<Role> result=new HashSet<>();
        for(String res:roles){
            Role role=roleRepo.findOneByName(res);
            if(role==null){
                Role newRole=new Role();
                newRole.setName(res);
                try{
                    roleRepo.save(newRole);
                }
                catch(Exception exception){
                    return null;
                }
                result.add(newRole);
            }
            else{
                result.add(role);
            }
        }
        return result;
    }
    
}
