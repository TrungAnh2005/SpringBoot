package com.example.ontap4.security;

public class Authority {
    //trong java hằng số được khai báo bằng từ khóa final
    //Các hằng số này được khai báo là public và static để có thể được sử dụng bên ngoài lớp Authority mà không cần phải tạo một đối tượng Authority
    public static final String READ = "READ";
    public static final String CREATE = "CREATE";
    public static final String DELETE = "DELETE";
    public static final String EDIT = "EDIT";
    public static final String SEARCH = "SEARCH";
    //Phương thức này được sử dụng để đảm bảo rằng không ai có thể tạo ra một đối tượng Authority từ bên ngoài lớp
    private Authority() {
    }
}
