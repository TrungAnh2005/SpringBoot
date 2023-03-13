package com.example.ontap2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfig1 extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    //Phương thức này tạo ra một đối tượng InMemoryUserDetailsManager để quản lý  thông tin người dùng trong bộ nhớ.
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        //Tạo ra một danh sách các UserDetails  để lưu trữ thông tin người dùng được xác thực .
        Collection<UserDetails> users = new ArrayList<>();
        var user = User.withUsername("trung").password("123").authorities("read").build();
        //Thêm đối tượng UserDetails vừa tạo vào danh sách người dùng
        users.add(user);
        //Trả về một đối tượng InMemoryUserDetailsManager chứa danh sách các UserDetails.
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public PasswordEncoder encoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
