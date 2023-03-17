package com.example.ontap4.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Locale;


public class Book {
    //id sẽ không được chuyển đổi thành trường JSON khi đối tượng Book được chuyển đổi thành JSON
    @JsonIgnore
    int id;

    String title;
    String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Phương thức  trả về giá trị boolean để cho biết có hay không chuỗi "keyword"
    //xuất hiện trong thuộc tính "title" hoặc "description" của đối tượng đang xét
    public boolean matchWithKeyword(String keyword) {
        String keywordLowerCase = keyword.toLowerCase();//phương thức toLowerCase() biến đổi chuỗi "keyword" thành chữ thường và lưu vào  biến keywordLowerCase
        //contains()  kiểm tra xem chuỗi "keywordLowerCase" có xuất hiện trong thuộc tính "title" hoặc "description" không , có trả về true không false
        return(title.toLowerCase().contains(keywordLowerCase)) || description.toLowerCase().contains(keywordLowerCase);
        //Việc sử dụng chữ thường để so sánh giúp phương thức không phân biệt chữ hoa và chữ thường trong việc tìm kiếm
    }
}
