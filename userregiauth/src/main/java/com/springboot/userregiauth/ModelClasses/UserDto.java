package com.springboot.userregiauth.ModelClasses;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
    private String name;
    private String password;
    private String email;
    private String phone;
    private String gender;
    private Set<String> role; 
}
