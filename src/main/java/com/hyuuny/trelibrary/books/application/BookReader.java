package com.hyuuny.trelibrary.books.application;

import com.hyuuny.trelibrary.books.domain.Book;
import com.hyuuny.trelibrary.books.domain.BookRepository;
import com.hyuuny.trelibrary.core.response.SimplePage;
import com.hyuuny.trelibrary.core.utils.SearchStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class BookReader {

    private final BookRepository bookRepository;
    private final QueryVerifier queryVerifier;

    public Book readBook(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Book notFound id:" + id)
        );
    }

    public SimplePage<BookDto.BookResponse> searchByKeyword(String keyword, Pageable pageable) {
        Page<Book> page = StringUtils.hasText(keyword) ?
                bookRepository.searchByKeyword(keyword, pageable) :
                bookRepository.searchByKeyword("", pageable);

        List<BookDto.BookResponse> books = page.getContent().stream()
                .map(BookDto.BookResponse::new)
                .toList();

        return new SimplePage<>(books, page);
    }

    public BookDto.SearchResultDto searchByComplexQuery(String q, Pageable pageable) {
        queryVerifier.validateQuery(q);

        long start = System.currentTimeMillis();
        SearchStrategy strategy = SearchStrategy.toStrategy(q);

        Page<Book> page = bookRepository.searchByComplexQuery(q, pageable);
        long executionTime = System.currentTimeMillis() - start;

        List<BookDto.BookResponse> books = page.getContent().stream()
                .map(BookDto.BookResponse::new)
                .toList();

        BookDto.PageInfo pageInfo = new BookDto.PageInfo(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements()
        );

        BookDto.SearchMetadata metadata = new BookDto.SearchMetadata(executionTime, strategy);

        return new BookDto.SearchResultDto(q, pageInfo, books, metadata);
    }
}
