package com.hyuuny.trelibrary.books.presentation;

import com.hyuuny.trelibrary.books.domain.Book;
import com.hyuuny.trelibrary.books.domain.BookRepository;
import com.hyuuny.trelibrary.common.BaseTest;
import com.hyuuny.trelibrary.core.utils.SearchStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @DisplayName("title에 keyword가 포함된 도서 목록을 조회할 수 있다")
    @Test
    void searchBooksByTitle() throws Exception {
        String keyword = "React";

        this.mockMvc.perform(get("/api/v1/books")
                        .param("keyword", keyword)
                        .param("page", "0")
                        .param("size", "20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").exists())
                .andExpect(jsonPath("$.content[0].title").value("Learning React"))
                .andExpect(jsonPath("$.content[0].subtitle").value("Functional Web Development with React and Redux"))
                .andExpect(jsonPath("$.content[0].image").value("https://itbook.store/img/books/9781491954621.png"))
                .andExpect(jsonPath("$.content[0].author").value("Alex Banks, Eve Porcello"))
                .andExpect(jsonPath("$.content[0].isbn").value("9781491900864"))
                .andExpect(jsonPath("$.content[0].published").value("2016-03-02"))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(7))
                .andExpect(jsonPath("$.pageNumber").value(0))
                .andExpect(jsonPath("$.size").value(20))
                .andExpect(jsonPath("$.last").value(true))
        ;
    }

    @DisplayName("title에 keyword가 포함된 도서가 없으면 빈 목록이 조회된다")
    @Test
    void searchBooksByTitle_zero() throws Exception {
        String keyword = "abc";

        this.mockMvc.perform(get("/api/v1/books")
                        .param("keyword", keyword)
                        .param("page", "0")
                        .param("size", "20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages").value(0))
                .andExpect(jsonPath("$.totalElements").value(0))
                .andExpect(jsonPath("$.pageNumber").value(0))
                .andExpect(jsonPath("$.size").value(20))
                .andExpect(jsonPath("$.last").value(true))
        ;
    }

    @DisplayName("title 또는 subTitle에 q가 포함된 도서 목록을 조회할 수 있다")
    @Test
    void searchComplexQueryBooksByTitleOrSubTitle() throws Exception {
        int editionCount = 13;
        int javaCount = 11;
        String q = "Edition|Java";

        this.mockMvc.perform(get("/api/v1/books/search")
                        .param("q", q)
                        .param("page", "0")
                        .param("size", "20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.books[0].id").exists())
                .andExpect(jsonPath("$.books[0].title").value("The Definitive Guide to MongoDB, 3rd Edition"))
                .andExpect(jsonPath("$.books[0].subtitle").value("A complete guide to dealing with Big Data using MongoDB"))
                .andExpect(jsonPath("$.books[0].image").value("https://itbook.store/img/books/9781484211830.png"))
                .andExpect(jsonPath("$.books[0].author").value("David Hows"))
                .andExpect(jsonPath("$.books[0].isbn").value("9781484211830"))
                .andExpect(jsonPath("$.books[0].published").value("2016-03-01"))
                .andExpect(jsonPath("$.pageInfo.currentPage").value(1))
                .andExpect(jsonPath("$.pageInfo.pageSize").value(20))
                .andExpect(jsonPath("$.pageInfo.totalPages").value(2))
                .andExpect(jsonPath("$.pageInfo.totalElements").value(editionCount + javaCount))
                .andExpect(jsonPath("$.searchMetadata.strategy").value(SearchStrategy.OR_OPERATION.name()))
        ;
    }

    @DisplayName("title 또는 subTitle에 Edition이 포함되고, Java가 제외된 도서 목록을 조회할 수 있다")
    @Test
    void searchComplexQueryBooksByTitleNot() throws Exception {
        int editionCount = 13;
        String q = "Edition-Java";

        this.mockMvc.perform(get("/api/v1/books/search")
                        .param("q", q)
                        .param("page", "0")
                        .param("size", "20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.books[0].id").exists())
                .andExpect(jsonPath("$.books[0].title").value("The Definitive Guide to MongoDB, 3rd Edition"))
                .andExpect(jsonPath("$.books[0].subtitle").value("A complete guide to dealing with Big Data using MongoDB"))
                .andExpect(jsonPath("$.books[0].image").value("https://itbook.store/img/books/9781484211830.png"))
                .andExpect(jsonPath("$.books[0].author").value("David Hows"))
                .andExpect(jsonPath("$.books[0].isbn").value("9781484211830"))
                .andExpect(jsonPath("$.books[0].published").value("2016-03-01"))
                .andExpect(jsonPath("$.pageInfo.currentPage").value(1))
                .andExpect(jsonPath("$.pageInfo.pageSize").value(20))
                .andExpect(jsonPath("$.pageInfo.totalPages").value(1))
                .andExpect(jsonPath("$.pageInfo.totalElements").value(editionCount))
                .andExpect(jsonPath("$.searchMetadata.strategy").value(SearchStrategy.NOT_OPERATION.name()))
        ;
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("q가 null이거나 빈 값이면 예외가 발생한다")
    void searchComplexQuery_requireText_ThrowsException(String q) throws Exception {
        this.mockMvc.perform(get("/api/v1/books/search")
                        .param("q", q)
                        .param("page", "0")
                        .param("size", "20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @ParameterizedTest
    @ValueSource(strings = {"Edition|Java|React", "Edition-Java-React"})
    @DisplayName("검색어에 | 또는 - 가 하나 이상이면 예외가 발생한다")
    void searchComplexQuery_over_ThrowsException(String q) throws Exception {
        this.mockMvc.perform(get("/api/v1/books/search")
                        .param("q", q)
                        .param("page", "0")
                        .param("size", "20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("검색어는 '|' 또는 '-' 연산자를 각각 1개만 포함할 수 있습니다."))
        ;
    }

    @ParameterizedTest
    @ValueSource(strings = {"Edition-Java|React", "Edition|Java-React"})
    @DisplayName("검색어에 |와 - 가 같이 사용되면 예외가 발생한다")
    void searchComplexQuery_with_ThrowsException(String q) throws Exception {
        this.mockMvc.perform(get("/api/v1/books/search")
                        .param("q", q)
                        .param("page", "0")
                        .param("size", "20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("검색어는 '|' 와 '-' 연산자를 동시에 사용할 수 없습니다."))
        ;
    }
}