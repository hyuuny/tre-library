package com.hyuuny.trelibrary.books.application;

import com.hyuuny.trelibrary.books.domain.Book;
import com.hyuuny.trelibrary.core.utils.SearchStrategy;

import java.time.LocalDateTime;
import java.util.List;

public class BookDto {

    public record DetailResponse(
            Long id,
            String title,
            String subTitle,
            String author,
            String published,
            String isbn,
            String imageUrl,
            LocalDateTime createdAt,
            LocalDateTime lastModifiedAt
    ) {
        public DetailResponse(Book entity) {
            this(
                    entity.getId(),
                    entity.getTitle(),
                    entity.getSubTitle(),
                    entity.getAuthor(),
                    entity.getPublished(),
                    entity.getIsbn(),
                    entity.getImageUrl(),
                    entity.getCreatedAt(),
                    entity.getLastModifiedAt()
            );
        }
    }

    public record BookResponse(
            Long id,
            String title,
            String subtitle,
            String image,
            String author,
            String isbn,
            String published
    ) {
        public BookResponse(Book entity) {
            this(
                    entity.getId(),
                    entity.getTitle(),
                    entity.getSubTitle(),
                    entity.getImageUrl(),
                    entity.getAuthor(),
                    entity.getIsbn(),
                    entity.getPublished()
            );
        }
    }

    public record PageInfo(
            int currentPage,
            int pageSize,
            int totalPages,
            long totalElements
    ) {
    }

    public record SearchMetadata(
            long executionTime, // ms 단위
            SearchStrategy strategy
    ) {
    }

    public record SearchResultDto(
            String searchQuery,
            PageInfo pageInfo,
            List<BookResponse> books,
            SearchMetadata searchMetadata
    ) {
    }
}


