package com.epam.rd.boothw.controller;

import com.epam.rd.boothw.service.AuthorService;
import com.epam.rd.boothw.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }
    @RequestMapping("/{id}")
    public Author getAuthor(@PathVariable("id") Long id) {
        return authorService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Author createAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

}
