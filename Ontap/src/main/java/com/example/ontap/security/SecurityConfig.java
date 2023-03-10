package com.example.ontap.security;
//Khi đã add dependency security trong file pom.xml rồi thì chương trình sẽ tự động mạc định sinh ra xác thực sử dung security của spring
//Tạo class SecurityConfig để cấu hình xác thực(authentication) theo ý của mình

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//@Configuration được sử dụng để đánh dấu rằng đây là một class cấu hình . Nó cho phép Spring tìm và quản lý các bean và phụ thuộc của class này.
//Trong trường hợp này, class được đánh dấu bằng @Configuration sẽ chứa các cấu hình liên quan đến bảo mật cho ứng dụng web.
@Configuration

//@EnableWebSecurity được sử dụng để kích hoạt tính năng bảo mật web của Spring Security.
@EnableWebSecurity
public class SecurityConfig {//hạt đậu này có nghĩa (navigate to the string beans declaration)  class này được đưa vào @SpringBootApplication trong @SpringBootApplication các bean sẽ liên kết và trao đổi với nhau

    //Annotation @Bean được sử dụng để khai báo rằng phương thức sẽ trả về một đối tượng được quản lý bởi Spring.
    @Bean//hạt đậu có mũi tên sang trái khi bấm vào không hiện ra đối tượng nào (not found) nghĩa là không đối tượng nào dùng đến nó mà chính class này dùng đến nó

    //Phương thức userDetailsService() trả về một đối tượng UserDetailsService.
    // UserDetailsService được sử dụng để xác thực thông tin người dùng.
    // Trong trường hợp này, đối tượng UserDetailsService được trả về sử dụng InMemoryUserDetailsManager để lưu trữ thông tin người dùng trong bộ nhớ.
    public UserDetailsService userDetailsService(){

        //Tạo đối tượng user với tên đăng nhập,password và quyền
        //User là một class của Spring Security và cung cấp các thuộc tính cho người dùng như username,password,authorities...vv
        // các thuộc tính này đại diện cho các thông tin cần thiết để xác thưc và phân quyền người dùng trong ứng dụng web, build dùng để tạo ra đối tượng User
        var user = User.withUsername("trung").password("123").authorities("read").build();

        //Trả về một đối tượng InMemoryUserDetailsManager với các đối tượng người dùng user1 và user2 được truyền vào.
        return new InMemoryUserDetailsManager(user);
    }

    @Bean //Hạt đậu có mũi tên sang trái khi bấm vào hiện ra các đối tượng có nghĩa nó đang tiêm cái bean cho đối tượng đấy

    //Phương thức passwordEncoder() trả về một đối tượng PasswordEncoder.
   // PasswordEncoder được sử dụng để mã hóa mật khẩu người dùng.
    public PasswordEncoder passwordEncoder(){
            //Trả về một đối tượng NoOpPasswordEncoder nó sẽ trả về mật khẩu đã được mã hóa mà không thực hiện bất kỳ thuật toán mã hóa nào, nó chỉ phục vụ mục đích thử nghiệm cơ chế authentication thôi.
        return NoOpPasswordEncoder.getInstance();
    }
}
