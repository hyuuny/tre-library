package com.hyuuny.trelibrary.popularsearchterms.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PopularSearchTermRepository extends JpaRepository<PopularSearchTerm, Long> {
    List<PopularSearchTerm> findTop10ByOrderByCountDesc();
}
