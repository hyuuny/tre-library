package com.hyuuny.trelibrary.books.presentation;

import com.hyuuny.trelibrary.books.application.BookDto;
import com.hyuuny.trelibrary.books.application.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@RestController
public class BookRestController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookDto.Response> getBookById(@PathVariable Long id) {
        BookDto.Response response = bookService.getBook(id);
        return ResponseEntity.ok(response);
    }
}
