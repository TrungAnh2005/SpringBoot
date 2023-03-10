package com.example.ontap.model;
//Đây là class POJO có nhiệm vụ lưu trữ dữ liệu của đối tượng Product và không làm nhiệm vụ gì khác.
// Vì thế class POJO chỉ chứa các trường, constructor, setter, getter, tostring...
//Nếu class chứa các loại method để cấu hình thì nó không phải là class POJO
public class Product {
    private String name;
    private int price;

    // getter và setter là hai phương thức được sử dụng để truy cập và cập nhật trạng thái (state) của một đối tượng.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
