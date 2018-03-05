package com.epam.rd.boothw.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="author")
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Singular
    @OneToMany(mappedBy = "author")
    private Set<Book> books;

    public Author(String name) {
        this.name = name;
    }

    public static AuthorBuilder builder() {
        return new AuthorBuilder();
    }

    public static class AuthorBuilder {
        private Author target = new Author();
        private AuthorBuilder() {
            target.setBooks(new HashSet<>());
        }
        public AuthorBuilder withName(String name) {
            target.setName(name);
            return this;
        }
        public AuthorBuilder withBooks(Set<Book> books) {
            target.setBooks(books);
            return this;
        }
        public AuthorBuilder withBook(Book book) {
            target.getBooks().add(book);
            return this;
        }
        public Author build() {
            return target;
        }
    }
}
