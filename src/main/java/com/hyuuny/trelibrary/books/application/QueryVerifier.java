package com.hyuuny.trelibrary.books.application;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class QueryVerifier {

    public void validateQuery(String q) {
        if (!StringUtils.hasText(q)) throw new IllegalArgumentException("검색어는 필수값 입니다.");

        int orCount = countOccurrences(q, '|');
        int notCount = countOccurrences(q, '-');

        if (orCount > 1 || notCount > 1) throw new IllegalArgumentException("검색어는 '|' 또는 '-' 연산자를 각각 1개만 포함할 수 있습니다.");
        if (orCount > 0 && notCount > 0) throw new IllegalArgumentException("검색어는 '|' 와 '-' 연산자를 동시에 사용할 수 없습니다.");
    }

    private int countOccurrences(String str, char ch) {
        return (int) str.chars().filter(c -> c == ch).count();
    }
}

