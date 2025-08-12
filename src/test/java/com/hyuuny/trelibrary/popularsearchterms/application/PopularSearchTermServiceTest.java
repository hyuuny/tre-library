package com.hyuuny.trelibrary.popularsearchterms.application;

import com.hyuuny.trelibrary.common.BaseTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PopularSearchTermServiceTest extends BaseTest {

    @Autowired
    private PopularSearchTermService popularSearchTermService;

    @DisplayName("인기검색어 상위 10개 목록을 조회할 수 있다")
    @Test
    void readTop10() {
        Set<String> top10Terms = Set.of(
                "Java",
                "Spring",
                "React",
                "Docker",
                "Kubernetes",
                "MongoDB",
                "Kafka",
                "AWS",
                "Microservices",
                "TypeScript"
        );
        List<PopularSearchTermDto.Response> responses = popularSearchTermService.readTop10();
        assertThat(responses).hasSize(10);
        responses.forEach(response -> assertThat(top10Terms).contains(response.term()));
    }
}