package com.example.ontap4.config;

import com.example.ontap4.repository.BookSetup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepoConfig {
    @Bean
    public BookSetup bookDao(){
        return new BookSetup("book.csv");
    }
}
