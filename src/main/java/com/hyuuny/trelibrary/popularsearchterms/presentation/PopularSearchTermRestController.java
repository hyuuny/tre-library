package com.hyuuny.trelibrary.popularsearchterms.presentation;

import com.hyuuny.trelibrary.popularsearchterms.application.PopularSearchTermDto;
import com.hyuuny.trelibrary.popularsearchterms.application.PopularSearchTermService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/popular-search-terms")
@RestController
public class PopularSearchTermRestController {

    private final PopularSearchTermService popularSearchTermService;

    @GetMapping("/top10")
    public ResponseEntity<List<PopularSearchTermDto.Response>> getTop10() {
        List<PopularSearchTermDto.Response> responses = popularSearchTermService.readTop10();
        return ResponseEntity.ok(responses);
    }
}
