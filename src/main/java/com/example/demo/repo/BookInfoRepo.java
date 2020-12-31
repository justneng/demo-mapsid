package com.example.demo.repo;

import com.example.demo.domain.BookInfo;
import com.example.demo.domain.BookInfoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookInfoRepo extends JpaRepository<BookInfo, BookInfoId> {
}
