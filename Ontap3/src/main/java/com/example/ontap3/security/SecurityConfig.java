package com.example.ontap3.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomAuthenProvider customAuthenProvider;

    @Override
    //AuthenticationManager  sử dụng customAuthenProvider để xác thực thông tin đăng nhập của người dùng
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests()
                .antMatchers("/api/products").hasAnyRole(Role.OPERATOR, Role.ADMIN, Role.USER)
                .antMatchers("/api/backoffice").hasAnyRole(Role.OPERATOR, Role.ADMIN)
                .antMatchers("/api/secret").hasRole(Role.USER)
                .antMatchers("/**").permitAll();
    }

    public static PasswordEncoder delegatePasswordEncoder(String encodingType){
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        return new DelegatingPasswordEncoder(encodingType, encoders);
    }
    @Bean
    public PasswordEncoder encoder(){
        return SecurityConfig.delegatePasswordEncoder("bcrypt");
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        Collection<UserDetails> users = new ArrayList<>();
        User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder()::encode);
        var user = userBuilder.username("trung").password("123").roles(Role.USER).build();
        var operator = userBuilder.username("operator").password("123").roles(Role.OPERATOR).build();
        var boss = userBuilder.username("boss").password("123").roles(Role.USER, Role.ADMIN).build();
        users.add(user);
        users.add(boss);
        users.add(operator);
        return new InMemoryUserDetailsManager(users);
    }

}
