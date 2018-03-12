package com.epam.rd.boothw.config;

import com.epam.rd.boothw.util.validation.AuthorDtoValidator;
import com.epam.rd.boothw.util.validation.BookDtoValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ValidationConfig {

    @Value("${entity.author.pattern}")
    private String authorNamePattern;
    @Value("${entity.book.pattern}")
    private String bookTitlePattern;


    @Bean
    public AuthorDtoValidator authorDtoValidator() {
        return new AuthorDtoValidator(authorNamePattern);
    }
    @Bean
    public BookDtoValidator bookDtoValidator() {
        return new BookDtoValidator(bookTitlePattern);
    }

}
