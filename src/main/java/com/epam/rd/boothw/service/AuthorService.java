package com.epam.rd.boothw.service;

import com.epam.rd.boothw.entity.Author;
import com.epam.rd.boothw.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author save(Author author) {
        return authorRepository.save(author);
    }
    public List<Author> findAll() {
        List list = authorRepository.findAll();
        return list;
    }
    public Author findById(Long id) {
        //TODO: how to handle 'null'??
        return authorRepository.findById(id).get();
    }
    public Author findByName(String authorName) {
        return authorRepository.findByName(authorName);
    }
}
