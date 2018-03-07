package com.epam.rd.boothw.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"title", "author"})
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", nullable = true)
    private Author author;

    public Book(String title, Author author) {
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

