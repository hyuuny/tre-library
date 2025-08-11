package com.hyuuny.trelibrary.books.domain;

import com.hyuuny.trelibrary.core.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String subTitle;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String published;

    @Column(nullable = false)
    private String isbn;

    private String imageUrl;
}

