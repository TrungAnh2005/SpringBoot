package com.example.b12.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //Annotation @Bean được sử dụng để khai báo rằng phương thức sẽ trả về một đối tượng được quản lý bởi Spring.
    @Bean
    //Phương thức userDetailsService() trả về một đối tượng UserDetailsService.
    // UserDetailsService được sử dụng để xác thực thông tin người dùng.
    // Trong trường hợp này, đối tượng UserDetailsService được trả về sử dụng InMemoryUserDetailsManager để lưu trữ thông tin người dùng trong bộ nhớ.
    public UserDetailsService userDetailsService(){
        //Tạo đối tượng user với tên đăng nhập,password và quyền
        var user1 = User.withUsername("trunganh").password("123").authorities("read").build();
        var user2 = User.withUsername("trunganh123").password("321").authorities("read").build();

        //Trả về một đối tượng InMemoryUserDetailsManager với các đối tượng người dùng user1 và user2 được truyền vào.
        return new InMemoryUserDetailsManager(user1, user2);
    }
    @Bean
    //Phương thức passwordEncoder() trả về một đối tượng PasswordEncoder.
    // PasswordEncoder được sử dụng để mã hóa mật khẩu người dùng.
    public PasswordEncoder passwordEncoder(){
        //Trả về một đối tượng NoOpPasswordEncoder, đây là một PasswordEncoder không làm gì cả, chỉ trả về mật khẩu đã được mã hóa mà không thực hiện bất kỳ thuật toán mã hóa nào
        return NoOpPasswordEncoder.getInstance();
    }
}
