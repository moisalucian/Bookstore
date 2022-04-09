package com.sda.lucianmoisa.bookstore.service;

import com.sda.lucianmoisa.bookstore.model.Book;

import java.util.Date;
import java.util.List;

public interface BookService {

    void update(Book book);

    void delete(Book book);

    void deleteBookById(Integer id);

    void save(String isbn, String title, String description, Date releaseDate);

    List<Book> findAll();

    Book findBookById(Integer id);

    Book findBookByIsbn(String isbn);

    Book findBookByTitle(String title);

    List<Book> findAllByReleaseDateAfter(Date date);

    List<Book> findAllBooksByTitleLike(String keyword);
}
