package com.hyuuny.trelibrary.core.response;

import org.springframework.data.domain.Page;

import java.util.List;

public record SimplePage<T>(
        List<T> content,
        int pageNumber,
        int size,
        long totalElements,
        int totalPages,
        boolean last
) {
    public SimplePage(List<T> content, Page<?> page) {
        this(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}


