package com.hyuuny.trelibrary.books.presentation;

import com.hyuuny.trelibrary.books.application.BookDto;
import com.hyuuny.trelibrary.books.application.BookService;
import com.hyuuny.trelibrary.core.response.SimplePage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@RestController
public class BookRestController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookDto.DetailResponse> getBookById(@PathVariable Long id) {
        BookDto.DetailResponse response = bookService.getBook(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<SimplePage<BookDto.BookResponse>> searchByKeyword(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        SimplePage<BookDto.BookResponse> response = bookService.searchByKeyword(keyword, PageRequest.of(page, size));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<BookDto.SearchResultDto> searchByComplexQuery(
            @RequestParam String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        BookDto.SearchResultDto result = bookService.searchByComplexQuery(q, PageRequest.of(page, size));
        return ResponseEntity.ok(result);
    }
}
