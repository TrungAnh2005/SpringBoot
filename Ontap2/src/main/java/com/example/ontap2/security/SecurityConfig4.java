package com.example.ontap2.security;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SecurityConfig4 extends WebSecurityConfigurerAdapter {
    // method trả về DelegatingPasswordEncoder là con của PasswordEncoder nhưng mục đích
    // để quản lý các PasswordEncoder khác bằng HashMap
    public static PasswordEncoder delegatePasswordEncoder(String encodingType){
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        return new DelegatingPasswordEncoder(encodingType, encoders);
    }
    // Tạo 1 Bean PasswordEncoder lấy trong HashMap encoders;
    @Bean
    public PasswordEncoder encoder(){
        return SecurityConfig4.delegatePasswordEncoder("bcrypt");
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        Collection<UserDetails> users = new ArrayList<>();
        User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder()::encode);
        var user = userBuilder.username("trung").password("123").roles("User").build();
        var user1 = userBuilder.username("trung1").password("123").roles("Operator").build();
        var user2 = userBuilder.username("trung2").password("123").roles("Admin").build();
        System.out.println(user.getPassword());
        users.add(user);
        users.add(user1);
        users.add(user2);
        return new InMemoryUserDetailsManager(users);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // bắt exception khi  phân quyền xảy ra lỗi
                .exceptionHandling()
                // điều hướng đến api /un-authorizes
                .accessDeniedPage("/api/un-authorizes")
                .and()
                // xử lý phân quyền các api
                .authorizeRequests()
                .antMatchers("/api/product").hasAnyRole("Admin", "Operator")
                .antMatchers("/api/you").hasAnyRole("Admin", "Operator")
                .antMatchers("/api/coffee").hasAnyRole("Admin", "User")
                // các request còn lại cần phải xác thực
                .anyRequest().authenticated()
                .and()
                // dùng form login để đăng nhập
                .formLogin()
                // khi login để chuyển trên api /login
                .loginProcessingUrl("/login")
                // tất cả người dùng đều có thể truy cập api này
                .permitAll();
    }
}
