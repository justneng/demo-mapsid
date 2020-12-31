package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Table(name = "BOOK")
@Entity
public class Book implements Serializable {

    @Id
    private String isbn;

    @Column
    private String title;

    @JsonManagedReference
    @OneToMany(mappedBy = "book", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<BookInfo> bookInfos;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<BookInfo> getBookInfos() {

        if(bookInfos == null) {
            return Collections.EMPTY_SET;
        }
        return bookInfos;
    }

    public void setBookInfos(Set<BookInfo> bookInfos) {
        this.bookInfos = bookInfos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
