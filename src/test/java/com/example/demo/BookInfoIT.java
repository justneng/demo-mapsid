package com.example.demo;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.domain.BookInfo;
import com.example.demo.domain.Genre;
import com.example.demo.repo.BookRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

@DataJpaTest
public class BookInfoIT {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    @Rollback(value = false)
    public void test() {
        Book book = theLordOfTheRings();
        em.merge(book);
    }
    private Book theLordOfTheRings() {

        //no optional check cebause the author with id 4 never be null (just dummy btw.)
        Author jrrTolkien = new Author();
        jrrTolkien.setId(4);
        jrrTolkien.setName("J.R.R. TOLKIEN");

        Book theLordOfTheRings = new Book();

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

}
