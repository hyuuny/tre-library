package com.hyuuny.trelibrary.books.application;

import com.hyuuny.trelibrary.books.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookService {

    private final BookReader reader;

    public BookDto.Response getBook(final Long id) {
        Book existingBook = reader.readBook(id);
        return new BookDto.Response(existingBook);
    }
}
