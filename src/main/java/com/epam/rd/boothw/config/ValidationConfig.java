package com.epam.rd.boothw.config;

import com.epam.rd.boothw.util.validation.AuthorValidator;
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
    public AuthorValidator authorDtoValidator() {
        return new AuthorValidator(authorNamePattern);
    }
    @Bean
    public BookDtoValidator bookDtoValidator() {
        return new BookDtoValidator(bookTitlePattern);
    }

}
