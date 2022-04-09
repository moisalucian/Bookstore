package com.sda.lucianmoisa.bookstore.repository;

import com.sda.lucianmoisa.bookstore.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    Book findBookByIsbn(String isbn);

    void deleteBookById(Integer id);

    List<Book> findAllByReleaseDateAfter(Date date);

    Book findBookByTitle(String title);

    List<Book> findAllByReleaseDateBefore(Date date);

    List<Book> findAllByTitleLike(String keyword);
}
