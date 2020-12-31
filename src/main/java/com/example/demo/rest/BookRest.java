package com.example.demo.rest;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.domain.BookInfo;
import com.example.demo.domain.Genre;
import com.example.demo.repo.AuthorRepo;
import com.example.demo.repo.BookRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/book")
public class BookRest {

    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;

    public BookRest(BookRepo bookRepo, AuthorRepo authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    @PostMapping
    public List<Book> dummyAddNewBooks() {

        List<Book> books = new ArrayList<>();
        books.add(theLordOfTheRings());
        books.add(aGameOfThrones());
        return bookRepo.saveAll(books);
    }

    private Book theLordOfTheRings() {

        //no optional check cebause the author with id 4 never be null (just dummy btw.)
        Author jrrTolkien = authorRepo.findById(4).get();

        Book theLordOfTheRings = new Book();
        theLordOfTheRings.setIsbn("9780563528883");
        theLordOfTheRings.setTitle("The Lord of the Rings: The Trilogy");

        Genre genre = new Genre();
        genre.setDescription("Novel, High fantasy, Fantasy Fiction, Chivalric romance, Adventure fiction, War story, Heroic fantasy");

        BookInfo bookInfo = new BookInfo();
        bookInfo.setBook(theLordOfTheRings);
        bookInfo.setAuthor(jrrTolkien);
        bookInfo.setGenre(genre);

        Set<BookInfo> bookInfos = new HashSet<>();
        bookInfos.add(bookInfo);
        theLordOfTheRings.setBookInfos(bookInfos);

        return theLordOfTheRings;
    }

    private Book aGameOfThrones() {

        //set existing author
        Author georgeRRMartin = new Author();
        georgeRRMartin.setId(1);
        georgeRRMartin.setName("GEORGE R.R. MARTIN");

        Book aGameOfThrones = new Book();
        aGameOfThrones.setIsbn("0-553-10354-7");
        aGameOfThrones.setTitle("A Game of Thrones");

        Genre genre = new Genre();
        genre.setDescription("Political novel, epic fantasy");

        BookInfo bookInfo = new BookInfo();
        bookInfo.setBook(aGameOfThrones);
        bookInfo.setAuthor(georgeRRMartin);
        bookInfo.setGenre(genre);

        Set<BookInfo> bookInfos = new HashSet<>();
        bookInfos.add(bookInfo);
        aGameOfThrones.setBookInfos(bookInfos);

        return aGameOfThrones;
    }
}
