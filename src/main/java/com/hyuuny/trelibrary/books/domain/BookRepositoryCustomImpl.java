package com.hyuuny.trelibrary.books.domain;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static com.hyuuny.trelibrary.books.domain.QBook.book;

@Repository
@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Book> searchByKeyword(String keyword, Pageable pageable) {
        BooleanExpression predicate = containsKeyword(keyword);

        List<Book> content = queryFactory.selectFrom(book)
                .where(book.title.containsIgnoreCase(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        long total = Objects.requireNonNullElse(
                queryFactory
                        .select(Wildcard.count)
                        .from(book)
                        .where(predicate)
                        .fetchOne(), 0L);

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<Book> searchByComplexQuery(String q, Pageable pageable) {
        BooleanExpression predicate = buildSearchPredicate(q);

        List<Book> content = queryFactory.selectFrom(book)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = Objects.requireNonNullElse(
                queryFactory
                        .select(Wildcard.count)
                        .from(book)
                        .where(predicate)
                        .fetchOne(), 0L);

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression buildSearchPredicate(String keyword) {
        if (keyword.contains("|")) {
            String[] parts = keyword.split("\\|");
            return containsKeyword(parts[0]).or(containsKeyword(parts[1]));
        } else if (keyword.contains("-")) {
            String[] parts = keyword.split("-");
            return containsKeyword(parts[0]).and(containsKeyword(parts[1]).not());
        } else {
            return containsKeyword(keyword);
        }
    }

    private BooleanExpression containsKeyword(String value) {
        return book.title.containsIgnoreCase(value)
                .or(book.subTitle.containsIgnoreCase(value));
    }
}

