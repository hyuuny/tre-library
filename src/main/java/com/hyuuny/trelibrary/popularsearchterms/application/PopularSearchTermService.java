package com.hyuuny.trelibrary.popularsearchterms.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PopularSearchTermService {

    private final PopularSearchTermReader popularSearchTermReader;

    public List<PopularSearchTermDto.Response> readTop10() {
        return popularSearchTermReader.readTop10();
    }
}
