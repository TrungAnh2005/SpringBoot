package com.example.ontap4.controller;

import com.example.ontap4.model.Book;
import com.example.ontap4.repository.BookSetup;
import com.example.ontap4.request.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    public BookSetup bookSetup;

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("book", bookSetup.getAll());
        return "allbookds";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("book", new Book());
        return "form";
    }

    @GetMapping("/{id}")
    public String getByID(@PathVariable("id") int id, Model model) {
        Optional<Book> book = bookSetup.get(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
        }
        return "book";
    }

    @PostMapping("/save")
    public String save(Book book, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "form";
        }
        if (book.getId() > 0) {
            bookSetup.update(book);
        } else {
            bookSetup.add(book);
        }
        return "redirect:/book";
    }

    @GetMapping("/delete/{id}")
    public String deleteByID(@PathVariable("id") int id) {
        bookSetup.deleteByID(id);
        return "redirect:/book";
    }

    @GetMapping("/edit/{id}")
    public String editBookId(@PathVariable("id") int id, Model model) {
        Optional<Book> book = bookSetup.get(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
        }
        return "form";
    }

    @GetMapping("/search")
    public String searchForm(Model model) {
        model.addAttribute("searchrequest", new SearchRequest());
        return "search";
    }

    @PostMapping("/search")
    public String searchByKeyword(@ModelAttribute SearchRequest request, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasFieldErrors()) {
            model.addAttribute("book", bookSetup.searchByKeyword(request.getKeyword()));
        }
        return "allbooks";
    }
}
