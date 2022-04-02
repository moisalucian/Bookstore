package com.sda.crisangabi.bookstore.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO -> data transfer object
 */
public class BaseBookDto {

    private Integer id;

    @Size(min = 8)
    @NotEmpty
    private String description;

    @NotEmpty
    @Size(min = 3)
    private String title;

    @NotNull
    private String releaseDate;

    public BaseBookDto(String description, String title, String releaseDate, Integer id) {
        this.description = description;
        this.title = title;
        this.releaseDate = releaseDate;
        this.id = id;
    }

    public BaseBookDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
