package com.example.ontap4.repository;

import com.example.ontap4.model.Book;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookSetup extends BookDAO<Book> {

    //Constructor có tham số là một chuỗi đại diện cho đường dẫn tới tệp tin CSV,
    // và nó gọi phương thức readCSV để đọc dữ liệu từ tệp tin CSV đó
    public BookSetup(String csvFile) {
        this.readCSV(csvFile);
    }

    //phương thức dùng để đọc tập tin CSV và thêm dữ liệu từ file vào  đối tượng Book
    @Override
    public void readCSV(String csvFile) {
        try {
            //Sử dụng đường dẫn của tệp CSV và tạo đối tượng File từ đó
            File file = ResourceUtils.getFile("classpath:static/" + csvFile);
           // Tạo đối tượng CsvMapper để đọc các dòng trong tệp CSV
            CsvMapper mapper = new CsvMapper();
            //Tạo đối tượng CsvSchema để chỉ định tiêu đề cột và ký tự phân cách cho tệp CSV
            CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator('|');
            //Sử dụng đối tượng CsvMapper và CsvSchema để tạo đối tượng ObjectReader cho lớp Book
            ObjectReader oReader = mapper.readerFor(Book.class).with(schema);
            //Tạo đối tượng Reader và FileReader để đọc tệp CSV
            Reader reader = new FileReader(file);
            //Sử dụng ObjectReader để đọc các giá trị từ tệp CSV và tạo một đối tượng MappingIterator<Book>
            MappingIterator<Book> mi = oReader.readValues(reader);
            // while để lặp lại việc đọc từng đối tượng sách từ MappingIterator và thêm chúng vào danh sách các đối tượng sách được lưu trữ trong đối tượng này
            while (mi.hasNext()) {
                Book book = mi.next();
                this.add(book);
            }
            //Nếu có lỗi xảy ra trong quá trình đọc tệp CSV, thông báo lỗi sẽ được in ra màn hình
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Book> getAll() {
        return collection;
    }

    @Override
    public Optional<Book> get(int id) {
        return collection.stream().filter(u -> u.getId() == id).findFirst();
    }

    @Override
    public void add(Book book) {
        int id;
        if (collection.isEmpty()) {
            id = 1;
        } else {
            Book lastBook = collection.get(collection.size() - 1);
            id = lastBook.getId() - 1;
        }
        book.setId(id);
        collection.add(book);
    }

    @Override
    public void update(Book book) {
        get(book.getId()).ifPresent(existBook -> {
            existBook.setTitle(book.getTitle());
            existBook.setDescription(book.getDescription());
        });
    }

    @Override
    public void delete(Book book) {
        deleteByID(book.getId());
    }

    @Override
    public void deleteByID(int id) {
        get(id).ifPresent(exitstbook -> collection.remove(exitstbook));
    }

    @Override
    public List<Book> searchByKeyword(String keyword) {
        return collection
                .stream()
                .filter(book -> book.matchWithKeyword(keyword))
                .collect(Collectors.toList());
    }
}
