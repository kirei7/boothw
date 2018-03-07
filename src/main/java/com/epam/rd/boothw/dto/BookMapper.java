package com.epam.rd.boothw.dto;

import com.epam.rd.boothw.entity.Book;

public class BookMapper {
    public static Book bookFromDto(BookDto bookDto) {
        return new Book(bookDto.getTitle(), null);
    }
    public static BookDto dtoFromBook(Book book) {
        return new BookDto(
                book.getTitle(),
                book.getAuthor() == null ? null : book.getAuthor().getName());
    }
}
