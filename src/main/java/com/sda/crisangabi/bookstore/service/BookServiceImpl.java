package com.sda.crisangabi.bookstore.service;

import com.sda.crisangabi.bookstore.model.Book;
import com.sda.crisangabi.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired//aici este spring-ul implicat, nu noi: el va injecta bookRepository
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().forEach((book -> bookList.add(book)));
        return bookList;
    }

    @Override
    public Book findBookById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Override
    @Transactional
    public void deleteBookById(Integer id) {
        bookRepository.deleteBookById(id);
    }

    @Override
    public void save(String isbn, String title, String description, Date releaseDate) {
        Book book = new Book(isbn, title, description, releaseDate);
        bookRepository.save(book);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findBookByIsbn(isbn);
    }

    @Override
    public Book findBookByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }

    @Override
    public List<Book> findAllByReleaseDateAfter(Date date) {
        return bookRepository.findAllByReleaseDateAfter(date);
    }

    @Override
    public List<Book> findAllBooksByTitleLike(String keyword) {
        return bookRepository.findAllByTitleLike(keyword);
    }


}
