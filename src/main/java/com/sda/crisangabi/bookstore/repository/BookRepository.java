package com.sda.crisangabi.bookstore.repository;

import com.sda.crisangabi.bookstore.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    Book findBookByIsbn(String isbn);

    void deleteBookById(Integer id);

    List<Book> findAllByReleaseDateAfter(Date date);

    Book findBookByTitle(String title);

    List<Book> findAllByReleaseDateBefore(Date date);

    List<Book> findAllByTitleLike(String keyword);
}
