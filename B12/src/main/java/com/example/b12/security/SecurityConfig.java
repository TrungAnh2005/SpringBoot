package com.example.b12.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//Đây là một annotation của Spring để đánh dấu rằng class đang được định nghĩa là một class cấu hình.
// Trong trường hợp này, class được đánh dấu bằng @Configuration sẽ chứa các cấu hình liên quan đến bảo mật cho ứng dụng web.
@Configuration

// Đây là một annotation để kích hoạt bảo mật cho ứng dụng web bằng Spring Security.
// Nếu không có annotation này, Spring Security sẽ không hoạt động và không thực hiện các cấu hình bảo mật.
// Khi sử dụng @EnableWebSecurity, Spring Security sẽ tự động tạo ra một số cấu hình mặc định để bảo vệ ứng dụng, và cho phép các cấu hình bảo mật tùy chỉnh thông qua các phương thức của class được đánh dấu bằng @Configuration.
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
