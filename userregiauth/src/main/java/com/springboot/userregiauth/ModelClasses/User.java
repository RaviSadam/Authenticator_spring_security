package com.springboot.userregiauth.ModelClasses;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="users")
public class User implements UserDetails{
    @Id
    @SequenceGenerator(
        name="user_id_generator",
        sequenceName = "user_id_generator",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_id_generator"
    )
    private long id;

    @Column(length = 30,nullable = false)
    private String name;
    private String password;
    private String email;

    @Column(length = 15)
    private String gender;

    @Column(length = 15)
    private String phone;

    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = CascadeType.PERSIST
    )
    @JoinTable(
        name="user_roles",
        joinColumns ={
            @JoinColumn(name="user_id",referencedColumnName = "id",nullable = false)
        },
        inverseJoinColumns={
            @JoinColumn(
                name="role_id",
                referencedColumnName = "id",
                nullable = false
            )
        }

    )
    private Set<Role> roles;




    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getUserRoles();
    }

    @Override
    public String getPassword() {
       return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private Collection<? extends GrantedAuthority> getUserRoles(){
        Set<GrantedAuthority> authorities = new HashSet<>();
        for(Role role:this.roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
    
}
