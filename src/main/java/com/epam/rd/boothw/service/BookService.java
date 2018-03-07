package com.epam.rd.boothw.service;

import com.epam.rd.boothw.entity.Author;
import com.epam.rd.boothw.entity.Book;
import com.epam.rd.boothw.repository.BookRepository;
import com.epam.rd.boothw.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;
    private AuthorService authorService;
    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService){
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }
    public Book createFromForm(BookDto bookDto) {
        Author author = authorService.findByName(bookDto.getAuthorName());
        if (author == null) {
            author = new Author(bookDto.getAuthorName());
            }
        Book book = Book.builder()
                .withTitle(bookDto.getTitle())
                .withAuthor(author)
                .build();
        author.addBook(book);
        authorService.save(author);
        return bookRepository.save(book);
    }
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    public List<Book> findByAuthorId(Long id) {
        return new ArrayList<>(authorService.findById(id).getBooks());
    }
    public Book findById(Long id) {
        //TODO: how to handle 'null'??
        return bookRepository.findById(id).get();
    }

    public Book addBookToAuthor(Long authorId, Book book) {
        Author author = getAuthor(authorId);
        author.addBook(book);
        book.setAuthor(author);
        authorService.save(author);
        return book;
    }

    private Author getAuthor(Long id) {
        return authorService.findById(id);
    }
}
