package com.epam.rd.boothw.config;

import com.epam.rd.boothw.config.security.SecurityConfig;
import com.epam.rd.boothw.dto.*;
import com.epam.rd.boothw.entity.Author;
import com.epam.rd.boothw.entity.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ValidationConfig.class})
public class AppConfig {
    @Bean
    public DtoMapper<BookDto, Book> bookDtoMapper() {
        return new BookMapper();
    }
    @Bean
    public DtoMapper<AuthorDto, Author> authorDtoMapper() {
        return new AuthorMapper((BookMapper) bookDtoMapper());
    }

}
