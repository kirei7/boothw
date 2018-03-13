package com.epam.rd.boothw.dto;

import com.epam.rd.boothw.entity.Author;

import java.util.Set;
import java.util.stream.Collectors;

public class AuthorMapper implements DtoMapper<AuthorDto, Author> {

    private BookMapper bookMapper;

    public AuthorMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }


    @Override
    public AuthorDto dtoFromObject(Author object) {
        Set set = object.getBooks();
        Set<BookDto> authorBooks = object.getBooks()
                .stream()
                .map(book -> bookMapper.dtoFromObject(book))
                .collect(Collectors.toSet());
        return new AuthorDto(object.getId(), object.getName(),authorBooks);
    }

    @Override
    public Author objectFromDto(AuthorDto dto) {
        return new Author(dto.getName());
    }
}
