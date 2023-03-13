package com.example.ontap2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig3 extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().anyRequest().authenticated();
    }
    // method trả về DelegatingPasswordEncoder là con của PasswordEncoder nhưng mục đích
    // để quản lý các PasswordEncoder khác bằng HashMap
    public static PasswordEncoder delegatePasswordEncoder(String encodingType) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        return new DelegatingPasswordEncoder(encodingType, encoders);
    }
    // Tạo 1 Bean PasswordEncoder lấy trong HashMap encoders;
    @Bean
    public PasswordEncoder encoder(){
        return SecurityConfig3.delegatePasswordEncoder("bcrypt");
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        Collection<UserDetails> users = new ArrayList<>();
        User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder()::encode);
        var user = userBuilder.username("trung").password("123").roles("User").build();
        System.out.println(user.getPassword());
        users.add(user);
        return new InMemoryUserDetailsManager(users);
    }
}
