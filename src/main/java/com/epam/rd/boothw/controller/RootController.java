package com.epam.rd.boothw.controller;

import com.epam.rd.boothw.dto.AuthorDto;
import com.epam.rd.boothw.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("")
public class RootController {

    private AuthorService authorService;

    @Autowired
    public RootController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping
    public String welcomePage(Map<String, Object> model) {
        model.put("authors", authorService.findAll());
        model.put("newAuthor", new AuthorDto());
        return "index";
    }
}
