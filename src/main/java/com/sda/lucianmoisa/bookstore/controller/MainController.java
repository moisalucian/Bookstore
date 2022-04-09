package com.sda.lucianmoisa.bookstore.controller;

import com.sda.lucianmoisa.bookstore.controller.dto.BookDto;
import com.sda.lucianmoisa.bookstore.controller.dto.UserRegistrationDto;
import com.sda.lucianmoisa.bookstore.model.Book;
import com.sda.lucianmoisa.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

//se va ocupa cu redirectarea paginilor
@Controller
public class MainController {

    private BookService bookService;

    @Autowired
    public MainController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String getFirstPage(Model model) {
        populateHomePage(model);
        return "index";
    }

    @GetMapping("/home-admin")
    public String homePageAdmin(Model model) {
        populateHomePage(model);
        return "home-admin";//este legat la numele html-ului corespunzator(homeAdmin)
    }

    @GetMapping("/createBook")
    public String createBook(Model model) {
        model.addAttribute("book", new Book());
        return "createBook";
    }

    @GetMapping("/updateBook/{id}")
    public String updateBook(@PathVariable(name = "id") Integer id, Model model) {
        Book book = bookService.findBookById(id);

        BookDto bookDto = new BookDto();
        bookDto.setIsbn(book.getIsbn());
        bookDto.setDescription((book.getDescription()));
        bookDto.setTitle(book.getTitle());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        bookDto.setReleaseDate(formatter.format(book.getReleaseDate()));
        bookDto.setId(book.getId());

        model.addAttribute("book", bookDto);
        return "updateBook";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/registration")//intre ghilimele trebuie sa specificam
    //valoarea parametrului lui th:action (="${registration}") din html-ul corespunzator
    public String registration(Model model) {
        model.addAttribute("user", new UserRegistrationDto());//intre ghilimele trebuie sa specificam
        //valoarea parametrului lui th:object (="${user}") din html-ul corespunzator
        return "register";
    }

    @GetMapping("/create-admin")
    public String createAdmin(Model model){
        model.addAttribute("user",new UserRegistrationDto());
        return "create-admin";
    }

    private void populateHomePage(Model model) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        List<BookDto> bookList = bookService.findAll()
                .stream()
                .map(book -> new BookDto(
                        book.getDescription(),
                        book.getTitle(),
                        formatter.format(book.getReleaseDate()),
                        book.getId(),
                        book.getIsbn()))
                .collect(Collectors.toList());

        model.addAttribute("bookList", bookList);
    }
}
