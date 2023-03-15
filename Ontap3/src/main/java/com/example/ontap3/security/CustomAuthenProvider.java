package com.example.ontap3.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

//@Component đánh dấu class trả về đôi tượng được  Spring quản lý .
@Component
//lớp nhận xử lý thông tin người dùng nhập vào để xác thực
public class CustomAuthenProvider implements AuthenticationProvider {

    //InMemoryUserDetailsManager được tiêm vào class CustomAuthenProvider sử dụng quản lý danh sách người dùng lưu trong  bộ nhớ
    @Autowired
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;
    //mã hóa mật khẩu
    @Autowired
    private PasswordEncoder encoder;

    @Override
    //phương thức để xác thực thông tin đăng nhập của người dùng
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();//lấy ra tên đăng nhập của người dùng
        String rawPassword = String.valueOf(authentication.getCredentials());//lấy ra mật khẩu mật dùng dưới dạng chuỗi kí tự
        try {
            //tìm kiếm trong danh sách người dùng để lấy thông tin của người dùng có tên đăng nhập là username có tồn tại trong hệ thống không
            UserDetails userDetails = inMemoryUserDetailsManager.loadUserByUsername(username);

            if (encoder.matches(rawPassword, userDetails.getPassword())){//So sánh password
                //Nếu người dùng được tìm thấy và mật khẩu được mã hóa đúng với mật khẩu được cung cấp, phương thức sẽ trả về một đối tượng UsernamePasswordAuthenticationToken đại diện cho thông tin xác thực của người dùng
                // Nếu không, phương thức sẽ trả về null
                return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
            }else {
                return null;
            }
            //Nếu tên đăng nhập không tồn tại trong danh sách người dùng, phương thức sẽ ném ra một ngoại lệ UsernameNotFoundException và trả về null
        }catch (UsernameNotFoundException e){
            return null;
        }

    }

    @Override
    // phương thức trả về một giá trị boolean xác định liệu lớp CustomAuthenProvider có hỗ trợ loại xác thực được cung cấp hay không
    //trả về true - hỗ trợ đối tượng lớp đó dc xác thực ở phương thức phía trên.
    //           false - không hỗ trợ
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
