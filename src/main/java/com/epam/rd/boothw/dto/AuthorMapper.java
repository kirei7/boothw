package com.epam.rd.boothw.dto;

import com.epam.rd.boothw.entity.Author;
import com.epam.rd.boothw.entity.Book;

import java.util.Set;

public class AuthorMapper implements DtoMapper<AuthorDto, Author> {

    @Override
    public AuthorDto dtoFromObject(Author object) {
        Set set = object.getBooks();
        String[] authorBooks = object.getBooks()
                .stream()
                .map(Book::getTitle)
                .toArray(String[]::new);
        return new AuthorDto(object.getName(),authorBooks);
    }

    @Override
    public Author objectFromDto(AuthorDto dto) {
        return new Author(dto.getName());
    }
}
