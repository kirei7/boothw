package com.epam.rd.boothw.dto;

import com.epam.rd.boothw.entity.Book;

public class BookMapper implements DtoMapper<BookDto, Book>{

    @Override
    public BookDto dtoFromObject(Book object) {
        return new BookDto(
                object.getTitle(),
                object.getAuthor() == null ? null : object.getAuthor().getName());
    }

    @Override
    public Book objectFromDto(BookDto dto) {
        return new Book(dto.getTitle(), null);
    }
}
