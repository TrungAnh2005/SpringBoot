package com.example.ontap2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfig2 extends WebSecurityConfigurerAdapter {
    // tiêm PasswordEncoder vào biến encoder
    @Autowired
    //PasswordEncoder được sử dụng để mã hóa mật khẩu người dùng.
    private PasswordEncoder encoder;

    @Bean
    public PasswordEncoder encoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    //Phương thức này tạo ra một đối tượng InMemoryUserDetailsManager để quản lý  thông tin người dùng trong bộ nhớ.
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        //Tạo ra một danh sách các UserDetails  để lưu trữ thông tin người dùng được xác thực .
        Collection<UserDetails> users = new ArrayList<>();
        User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
        var user = userBuilder.username("trung").password("123").roles("User").build();
        System.out.println(user.getPassword());
        users.add(user);
        return new InMemoryUserDetailsManager(users);
    }
    // (encoder::encode) nó được sử dụng để truyền phương thức encode() của đối tượng encoder (một đối tượng PasswordEncoder được khai báo và định nghĩa ở trên) tới phương thức passwordEncoder() của đối tượng User.UserBuilder.
    // Điều này cho phép phương thức mã hóa mật khẩu được sử dụng để mã hóa mật khẩu của người dùng khi tạo đối tượng User
}
