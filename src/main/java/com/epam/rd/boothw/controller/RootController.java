package com.epam.rd.boothw.controller;

import com.epam.rd.boothw.dto.AuthorDto;
import com.epam.rd.boothw.dto.BookDto;
import com.epam.rd.boothw.dto.DtoMapper;
import com.epam.rd.boothw.entity.Author;
import com.epam.rd.boothw.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("")
public class RootController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RootController.class);

    private AuthorService authorService;
    private DtoMapper<AuthorDto, Author> authorMapper;

    @Autowired
    public RootController(AuthorService authorService, DtoMapper<AuthorDto, Author> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @RequestMapping
    public String welcomePage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return "index";
    }

    @RequestMapping("/catalogue")
    public String cataloguePage(Map<String, Object> model) {
        LOGGER.info("Catalogue page requested.");
        model.put("authors", authorService.findAll());
        model.put("newAuthor", new AuthorDto());
        return "authors-list";
    }

    @RequestMapping("/author/{id}")
    public String authorPage(@PathVariable Long id, Map<String, Object> model) {
        model.put("newBook", new BookDto());
        model.put(
                "author",
                authorMapper.dtoFromObject(authorService.findById(id))
        );
        return "author";
    }
}
