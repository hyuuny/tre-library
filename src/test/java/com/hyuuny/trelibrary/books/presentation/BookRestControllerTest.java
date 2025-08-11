package com.hyuuny.trelibrary.books.presentation;

import com.hyuuny.trelibrary.BaseTest;
import com.hyuuny.trelibrary.books.domain.Book;
import com.hyuuny.trelibrary.books.domain.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class BookRestControllerTest extends BaseTest {

    @Autowired
    private BookRepository bookRepository;

    @DisplayName("도서 아이디로 도서를 상세 조회할 수 있다")
    @Test
    void getBookById() throws Exception {
        Book book = new Book(
                "Practical MongoDB",
                "Architecting, Developing, and Administering MongoDB",
                "Sharon Tracey",
                "2016-03-01",
                "9781484206485",
                "https://itbook.store/img/books/9781484206485.png"
        );
        Book savedBook = bookRepository.save(book);

        this.mockMvc.perform(get("/api/v1/books/{id}", savedBook.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedBook.getId()))
                .andExpect(jsonPath("$.title").value(savedBook.getTitle()))
                .andExpect(jsonPath("$.subTitle").value(savedBook.getSubTitle()))
                .andExpect(jsonPath("$.author").value(savedBook.getAuthor()))
                .andExpect(jsonPath("$.published").value(savedBook.getPublished()))
                .andExpect(jsonPath("$.isbn").value(savedBook.getIsbn()))
                .andExpect(jsonPath("$.imageUrl").value(savedBook.getImageUrl()))
                .andExpect(jsonPath("$.createdAt").exists())
                .andExpect(jsonPath("$.lastModifiedAt").exists())
        ;
    }

    @DisplayName("도서 아이디로 도서를 상세 조회할 때 도서가 없으면 예외가 발생한다")
    @Test
    void getBook_NotFound_ThrowsException() throws Exception {
        Long nonExistentId = 9999L;

        this.mockMvc.perform(get("/api/v1/books/{id}", nonExistentId))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Book notFound id:" + nonExistentId))
        ;
    }
}