package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "BOOK_INFO")
@Entity
public class BookInfo implements Serializable {

    @EmbeddedId
    private BookInfoId id = new BookInfoId();

    @JsonBackReference
    @ManyToOne
    @MapsId("isbn")
    private Book book;

    @ManyToOne
    @MapsId("authorId")
    private Author author;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;

    public BookInfoId getId() {
        return id;
    }

    public void setId(BookInfoId id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookInfo that = (BookInfo) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
