package com.example.ontap.controller;

import com.example.ontap.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class APIController {
    //Phương thức  này được gọi khi có một request HTTP GET được gửi đến đường dẫn /products
    @GetMapping(value = "/products")
    //phương thức xử lý request HTTP GET đến đường dẫn /products . Phương thức này trả về một danh sách các đối tượng Product
    public List<Product> getProducts(){
        List<Product> result = new ArrayList<>();//tạo một đối tượng List rỗng kiểu product để lưu trữ danh sách sản phẩm
        result.add(new Product("Coffee Machine", 150));//Thêm một đối tượng product mới vào danh sách sản phẩm với name:"Coffee machinne" và giá 150
        result.add(new Product("Apple Watch", 250));
        result.add(new Product("Enik Book Reader", 350));
        return result;// trả về danh sách sản phẩm đã được tạo và cập nhật
    }


}
