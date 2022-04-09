package com.sda.lucianmoisa.bookstore.controller;

import com.sda.lucianmoisa.bookstore.controller.dto.BookDto;
import com.sda.lucianmoisa.bookstore.model.Book;
import com.sda.lucianmoisa.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //bindingResult ne zice dac avem erori
    @PostMapping("/addBook")
    public String addBook(@Valid @ModelAttribute(name = "book"/*am luat aceasta valoare de la th:obkect din html ul corespunzator (createBook) */) BookDto bookDto, BindingResult result, Model model) throws ParseException {
        if (result.hasErrors()) {
            return "createBook";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        bookService.save(bookDto.getIsbn(), bookDto.getTitle(), bookDto.getDescription(), formatter.parse(bookDto.getReleaseDate()));
        List<Book> bookList = bookService.findAll();

        return "redirect:/"; //redirectam la pagina principala
        //model.addAttribute("bookList", bookList); //ne ajuta sa reactualizam datele (sa i dam noile date)
        //in pagina  in care se returneaza userul
        //return "index";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable(name = "id") Integer id) {
        bookService.deleteBookById(id);
        return "redirect:/";
    }

    @PostMapping("/updateBook/{id}")
    public String updateBook(@PathVariable(name = "id") Integer id, @Valid BookDto bookDto, BindingResult result, Model model) throws ParseException {
        if(result.hasErrors()){
            model.addAttribute("book",bookDto);
            return "/updateBook";
        }

        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());
        book.setIsbn(bookDto.getIsbn());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        book.setReleaseDate(formatter.parse(bookDto.getReleaseDate()));
        book.setId(bookDto.getId());

        bookService.update(book);
        return "redirect:/";
    }
}
