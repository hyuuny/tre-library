package com.hyuuny.trelibrary.popularsearchterms.presentation;

import com.hyuuny.trelibrary.common.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PopularSearchTermRestControllerTest extends BaseTest {

    @DisplayName("인기 검색어 상위 10개 목록을 조회할 수 있다")
    @Test
    void getTop10() throws Exception {
        this.mockMvc.perform(get("/api/v1/popular-search-terms/top10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(10))
        ;
    }
}