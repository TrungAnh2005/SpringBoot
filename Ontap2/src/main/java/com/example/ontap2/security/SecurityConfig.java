package com.example.ontap2.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

//@Configuration - tạo bean với mục đích dùng để config trong spring
@Configuration
//@EnableWebSecurity - gắn vào class mục đích để custom security
@EnableWebSecurity
//Lớp SecurityConfig1 kế thừa từ WebSecurityConfigurerAdapter, lớp này cung cấp các phương thức để cấu hình bảo mật cho ứng dụng web
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //@Override được sử dụng để ghi đè lại phương thức configure() từ lớp cha WebSecurityConfigurerAdapter
    @Override
    //Phương thức configure(AuthenticationManagerBuilder auth) được sử dụng để cấu hình xác thực
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication() được sử dụng để xác thực trong bộ nhớ,các phương thức withUser, password , authorities chỉ tên người dùng , mật khẩu và quyền
        auth.inMemoryAuthentication().withUser("trung").password("123").authorities("read")
                .and()
                //mã hóa mật khẩu
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    //HttpSecurity - Đây là một class của Spring Security, cung cấp các phương thức để cấu hình bảo mật cho HTTP requests
    protected void configure(HttpSecurity http) throws Exception {
        //cấu hình http hiện ra cái Popup thay cho form xác thực mặc định (cách thức xác thực Basic Authentication)
        http.httpBasic();
        //http.formLogin(); dùng formLogin để đăng nhập, nhưng dùng phương thức POST để truyền dữ liệu
//        http.formLogin();
        //xác thực và phân quyền mọi request đều phải xác thực  trước khi được phép truy cập
        http.authorizeRequests().anyRequest().authenticated();
    }
}
