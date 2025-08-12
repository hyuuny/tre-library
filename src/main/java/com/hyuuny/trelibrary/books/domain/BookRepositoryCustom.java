package com.hyuuny.trelibrary.books.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookRepositoryCustom {
    Page<Book> searchByKeyword(String keyword, Pageable pageable);
    Page<Book> searchByComplexQuery(String q, Pageable pageable);
}
