package com.hyuuny.trelibrary.popularsearchterms.domain;

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
@Table(name = "popular_search_terms")
public class PopularSearchTerm extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String term;

    @Column(nullable = false)
    private Long count = 0L;
}
