package com.hyuuny.trelibrary.core.utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SearchStrategy {

    OR_OPERATION("OR_OPERATION"),
    NOT_OPERATION("NOT_OPERATION"),
    SIMPLE_SEARCH("SIMPLE_SEARCH");

    private final String title;

    public static SearchStrategy toStrategy(String q) {
        if (q.contains("|")) {
            return OR_OPERATION;
        } else if (q.contains("-")) {
            return NOT_OPERATION;
        } else {
            return SIMPLE_SEARCH;
        }
    }
}

