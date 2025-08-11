package com.hyuuny.trelibrary.books.application;

import com.hyuuny.trelibrary.books.domain.Book;
import com.hyuuny.trelibrary.books.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class BookReader {

    private final BookRepository bookRepository;

    public Book readBook(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Book notFound id:" + id)
        );
    }
}
