package com.epam.rd.boothw.controller;

import com.epam.rd.boothw.dto.DtoMapper;
import com.epam.rd.boothw.entity.Book;
import com.epam.rd.boothw.service.BookService;
import com.epam.rd.boothw.dto.BookDto;
import com.epam.rd.boothw.util.validation.BookDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class BookController {

    private BookService bookService;
    private DtoMapper<BookDto, Book> bookMapper;
    private BookDtoValidator validator;

    @Autowired
    public BookController(BookService bookService, DtoMapper<BookDto, Book> bookMapper, BookDtoValidator validator) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.validator = validator;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
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
    public BookDto addBookToAuthor(@PathVariable Long id, @RequestBody @Validated BookDto bookDto) {
        Book added = bookService.addBookToAuthor(id, bookMapper.objectFromDto(bookDto));
        return bookMapper.dtoFromObject(added);
    }
}
