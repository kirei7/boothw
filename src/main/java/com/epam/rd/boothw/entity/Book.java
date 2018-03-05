package com.epam.rd.boothw.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = true)
    private Author author;

    public Book(String title) {
        this.title = title;
    }

    public static BookBuilder builder() {
        return new BookBuilder();
    }

    public static class BookBuilder {
        private Book target = new Book();
        public BookBuilder withTitle(String title) {
            target.setTitle(title);
            return this;
        }
        public BookBuilder withAuthor(Author author) {
            target.setAuthor(author);
            return this;
        }
        public Book build() {
            return target;
        }
    }
}

