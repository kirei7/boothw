package com.epam.rd.boothw;

import com.epam.rd.boothw.entity.Author;
import com.epam.rd.boothw.entity.Book;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class EntityBuilderTest {

    @Test
    public void bookEntityBuilderCreatesValidEntity() {
        String authorName = "J.R.R. Tolkien";
        String bookTitle = "The Lord of the Rings";
        Book book = Book.builder()
                .withTitle(authorName)
                .withAuthor(new Author(authorName))
                .build();
        assertEquals(authorName, book.getAuthor().getName());
        assertEquals(bookTitle, book.getTitle());
    }

    @Test
    public void authorEntityWorking() {
        Book book1 = new Book("Hard to be a God");
        Book book2 = new Book("Roadside Picnic");
        String authorName = "The Strugatsky brothers";
        Author author1 = Author.builder()
                .withName(authorName)
                .withBook(book1)
                .withBook(book2)
                .build();

        Set<Book> books = new HashSet<>();
        books.add(book1);
        books.add(book2);

        Author author2 = Author.builder()
                .withName(authorName)
                .withBooks(books)
                .build();

        assertEquals(authorName, author1.getName());
        assertEquals(author1.getName(), author2.getName());

        assertEquals(author1.getBooks(), author2.getBooks());
    }
}
