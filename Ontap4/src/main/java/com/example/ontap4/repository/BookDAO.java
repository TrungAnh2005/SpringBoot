package com.example.ontap4.repository;

import com.example.ontap4.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Abstract class giống class bình thường nhưng có thêm các abstract method,
// có thể dùng luôn hoặc để cho các class khác extend.
// Tức là một thằng đẻ ra phương thức và thuộc tính để nó dùng và để cho người khác dùng,
// vì thế nó cũng có thể kế thừa class và implements interface.
public abstract class BookDAO<T> {
    protected List<T> collection = new ArrayList<>();

    public abstract void readCSV(String csvFile);

    public abstract List<T> getAll();

    public abstract Optional<T> get(int id);

    public abstract void add(T t);

    public abstract void update(T t);

    public abstract void delete(T t);

    public abstract void deleteByID(int id);

    public abstract List<T> searchByKeyword(String keyword);
}
