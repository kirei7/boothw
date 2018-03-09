package com.epam.rd.boothw.controller;

import com.epam.rd.boothw.dto.AuthorDto;
import com.epam.rd.boothw.dto.DtoMapper;
import com.epam.rd.boothw.service.AuthorService;
import com.epam.rd.boothw.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;
    private DtoMapper<AuthorDto, Author> authorMapper;

    @Autowired
    public AuthorController(AuthorService authorService, DtoMapper<AuthorDto, Author> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @RequestMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService.findAll().stream().map(authorMapper::dtoFromObject).collect(Collectors.toList());
    }
    @RequestMapping("/{id}")
    public AuthorDto getAuthor(@PathVariable("id") Long id) {
        return authorMapper.dtoFromObject(authorService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public AuthorDto createAuthor(@RequestBody Author author) {
        return authorMapper.dtoFromObject(authorService.save(author));
    }

}
