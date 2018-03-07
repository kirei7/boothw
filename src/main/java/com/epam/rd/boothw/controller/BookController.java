package com.epam.rd.boothw.controller;

import com.epam.rd.boothw.dto.BookMapper;
import com.epam.rd.boothw.entity.Book;
import com.epam.rd.boothw.service.AuthorService;
import com.epam.rd.boothw.service.BookService;
import com.epam.rd.boothw.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class BookController {

    private BookService bookService;
    private AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @RequestMapping("/books")
    public List<BookDto> getAllBooks() {
        List<Book>bookService.findAll()
        return ;
    }

    @RequestMapping("/authors/{id}/books")
    public List<Book> getAuthorBooks(@PathVariable Long id) {
        return bookService.findByAuthorId(id);
    }

    @RequestMapping(path = "/authors/{id}/books", method = RequestMethod.POST)
    public Book addBookToAuthor(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return bookService.addBookToAuthor(id, BookMapper.bookFromDto(bookDto));
    }
}
