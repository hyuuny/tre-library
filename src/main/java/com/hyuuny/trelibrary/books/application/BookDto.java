package com.hyuuny.trelibrary.books.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hyuuny.trelibrary.books.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class BookDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Response {

        private Long id;

        private String title;

        private String subTitle;

        private String author;

        private String publisher;

        private String isbn;

        private String imageUrl;

        private LocalDateTime createdAt;

        private LocalDateTime lastModifiedAt;

        public Response(Book entity) {
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.subTitle = entity.getSubTitle();
            this.author = entity.getAuthor();
            this.publisher = entity.getPublisher();
            this.isbn = entity.getIsbn();
            this.imageUrl = entity.getImageUrl();
            this.createdAt = entity.getCreatedAt();
            this.lastModifiedAt = entity.getLastModifiedAt();
        }
    }

}
