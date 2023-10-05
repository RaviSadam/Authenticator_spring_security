package com.springboot.userregiauth.ControllerClasses;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.userregiauth.ModelClasses.UserDto;
import com.springboot.userregiauth.ServiceClasses.UserService;

@Controller
public class MainController {


    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("/register")
    public String registerHome(){
        return "registration";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute UserDto userDto,Model model){
        boolean b=userService.registerNewUser(userDto);
        if(b)
            return "Success";
        model.addAttribute("error", "There was an error processing your request.");
        return "Error";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "redirect:/login";
    }
}
