package com.hyuuny.trelibrary.popularsearchterms.application;

import com.hyuuny.trelibrary.popularsearchterms.domain.PopularSearchTermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PopularSearchTermReader {

    private final PopularSearchTermRepository popularSearchTermRepository;

    public List<PopularSearchTermDto.Response> readTop10() {
        return popularSearchTermRepository.findTop10ByOrderByCountDesc().stream()
                .map(PopularSearchTermDto.Response::new)
                .toList();
    }
}
