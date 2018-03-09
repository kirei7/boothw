package com.epam.rd.boothw.controller;

import com.epam.rd.boothw.dto.BookMapper;
import com.epam.rd.boothw.dto.DtoMapper;
import com.epam.rd.boothw.entity.Book;
import com.epam.rd.boothw.service.BookService;
import com.epam.rd.boothw.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class BookController {

    private BookService bookService;
    private DtoMapper<BookDto, Book> bookMapper;

    @Autowired
    public BookController(BookService bookService, DtoMapper<BookDto, Book> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @RequestMapping("/books")
    public List<BookDto> getAllBooks() {
        List<Book> books = bookService.findAll();
        return books.stream().map(bookMapper::dtoFromObject).collect(Collectors.toList());
    }

    @RequestMapping("/authors/{id}/books")
    public List<BookDto> getAuthorBooks(@PathVariable Long id) {
        return bookService.findByAuthorId(id).stream().map(bookMapper::dtoFromObject).collect(Collectors.toList());
    }

    @RequestMapping(path = "/authors/{id}/books", method = RequestMethod.POST)
    public BookDto addBookToAuthor(@PathVariable Long id, @RequestBody BookDto bookDto) {
        Book added = bookService.addBookToAuthor(id, bookMapper.objectFromDto(bookDto));
        return bookMapper.dtoFromObject(added);
    }
}
