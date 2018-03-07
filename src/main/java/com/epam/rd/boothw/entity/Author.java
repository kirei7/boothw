package com.epam.rd.boothw.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"name", "books"})
@NoArgsConstructor
@Entity
@Table(name="author")
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();

    public Author(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        books.add(book);
    }
    public boolean removeBook(Book book) {
        return books.remove(book);
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
