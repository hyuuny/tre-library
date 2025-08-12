package com.hyuuny.trelibrary.books.application;

import com.hyuuny.trelibrary.books.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookService {

    private final BookReader reader;

    public BookDto.DetailResponse getBook(final Long id) {
        Book existingBook = reader.readBook(id);
        return new BookDto.DetailResponse(existingBook);
    }

    public Page<BookDto.BookResponse> searchByKeyword(String keyword, Pageable pageable) {
        return reader.searchByKeyword(keyword, pageable);
    }

    public BookDto.SearchResultDto searchByComplexQuery(String q, Pageable pageable) {
        return reader.searchByComplexQuery(q, pageable);
    }
}
