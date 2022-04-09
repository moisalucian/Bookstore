package com.sda.lucianmoisa.bookstore.controller.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BookDto extends BaseBookDto {

    @Size(min = 3, max = 30)
    @NotBlank
    @Column(name = "isbn")
    private String isbn;

    public BookDto(String description, String title, String releaseDate, Integer id, String isbn) {
        super(description, title, releaseDate, id);
        this.isbn = isbn;
    }

    public BookDto() {
    }

    public String getIsbn() {
        return isbn;
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}


