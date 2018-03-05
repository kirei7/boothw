package com.epam.rd.boothw.repository;

import com.epam.rd.boothw.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
