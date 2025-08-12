package com.hyuuny.trelibrary.books.application;

import com.hyuuny.trelibrary.books.domain.Book;
import com.hyuuny.trelibrary.books.domain.BookRepository;
import com.hyuuny.trelibrary.common.BaseTest;
import com.hyuuny.trelibrary.core.response.SimplePage;
import com.hyuuny.trelibrary.core.utils.SearchStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookServiceTest extends BaseTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @DisplayName("도서 아이디로 도서를 상세 조회할 수 있다")
    @Test
    void getBook() {
        Book book = new Book(
                "Practical MongoDB",
                "Architecting, Developing, and Administering MongoDB",
                "Sharon Tracey",
                "2016-03-01",
                "9781484206485",
                "https://itbook.store/img/books/9781484206485.png"
        );
        Book savedBook = bookRepository.save(book);

        BookDto.DetailResponse response = bookService.getBook(savedBook.getId());

        assertThat(response.id()).isEqualTo(savedBook.getId());
        assertThat(response.title()).isEqualTo(savedBook.getTitle());
        assertThat(response.subTitle()).isEqualTo(savedBook.getSubTitle());
        assertThat(response.author()).isEqualTo(savedBook.getAuthor());
        assertThat(response.published()).isEqualTo(savedBook.getPublished());
        assertThat(response.isbn()).isEqualTo(savedBook.getIsbn());
        assertThat(response.imageUrl()).isEqualTo(savedBook.getImageUrl());
        assertThat(response.createdAt()).isEqualTo(savedBook.getCreatedAt());
        assertThat(response.lastModifiedAt()).isEqualTo(savedBook.getLastModifiedAt());
    }

    @DisplayName("도서 아이디로 도서를 상세 조회할 때 도서가 없으면 예외가 발생한다")
    @Test
    void getBook_NotFound_ThrowsException() {
        Long nonExistentId = 9999L;

        assertThatThrownBy(() -> bookService.getBook(nonExistentId))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Book notFound id:" + nonExistentId);
    }

    @DisplayName("title에 keyword가 포함된 도서 목록을 조회할 수 있다")
    @Test
    void searchBooksByTitle() {
        String keyword = "React";
        SimplePage<BookDto.BookResponse> responses = bookService.searchByKeyword(keyword, PageRequest.of(0, 20));
        assertThat(responses.totalElements()).isEqualTo(7);
    }

    @DisplayName("title에 keyword가 포함된 도서가 없으면 빈 목록이 조회된다")
    @Test
    void searchBooksByTitle_zero() {
        String keyword = "abc";
        SimplePage<BookDto.BookResponse> responses = bookService.searchByKeyword(keyword, PageRequest.of(0, 20));
        assertThat(responses.totalElements()).isEqualTo(0);
    }

    @DisplayName("title 또는 subTitle에 q가 포함된 도서 목록을 조회할 수 있다")
    @Test
    void searchComplexQueryBooksByTitleOrSubTitle() {
        int editionCount = 13;
        int javaCount = 11;
        String q = "Edition|Java";

        BookDto.SearchResultDto searchResultDto = bookService.searchByComplexQuery(q, PageRequest.of(0, 20));
        assertThat(searchResultDto.searchQuery()).isEqualTo(q);
        assertThat(searchResultDto.pageInfo().currentPage()).isEqualTo(1);
        assertThat(searchResultDto.pageInfo().pageSize()).isEqualTo(20);
        assertThat(searchResultDto.pageInfo().totalPages()).isEqualTo(2);
        assertThat(searchResultDto.pageInfo().totalElements()).isEqualTo(editionCount + javaCount);
        assertThat(searchResultDto.searchMetadata().strategy()).isEqualTo(SearchStrategy.OR_OPERATION);
    }

    @DisplayName("title 또는 subTitle에 Edition이 포함되고, Java가 제외된 도서 목록을 조회할 수 있다")
    @Test
    void searchComplexQueryBooksByTitleNot() {
        int editionCount = 13;
        String q = "Edition-Java";

        BookDto.SearchResultDto searchResultDto = bookService.searchByComplexQuery(q, PageRequest.of(0, 20));
        assertThat(searchResultDto.searchQuery()).isEqualTo(q);
        assertThat(searchResultDto.pageInfo().currentPage()).isEqualTo(1);
        assertThat(searchResultDto.pageInfo().pageSize()).isEqualTo(20);
        assertThat(searchResultDto.pageInfo().totalPages()).isEqualTo(1);
        assertThat(searchResultDto.pageInfo().totalElements()).isEqualTo(editionCount);
        assertThat(searchResultDto.searchMetadata().strategy()).isEqualTo(SearchStrategy.NOT_OPERATION);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("q가 null이거나 빈 값이면 예외가 발생한다")
    void searchComplexQuery_requireText_ThrowsException(String q) {
        assertThatThrownBy(() -> bookService.searchByComplexQuery(q, PageRequest.of(0, 20)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("검색어는 필수값 입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Edition|Java|React", "Edition-Java-React"})
    @DisplayName("검색어에 | 또는 - 가 하나 이상이면 예외가 발생한다")
    void searchComplexQuery_over_ThrowsException(String q) {
        assertThatThrownBy(() -> bookService.searchByComplexQuery(q, PageRequest.of(0, 20)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("검색어는 '|' 또는 '-' 연산자를 각각 1개만 포함할 수 있습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Edition-Java|React", "Edition|Java-React"})
    @DisplayName("검색어에 |와 - 가 같이 사용되면 예외가 발생한다")
    void searchComplexQuery_with_ThrowsException(String q) {
        assertThatThrownBy(() -> bookService.searchByComplexQuery(q, PageRequest.of(0, 20)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("검색어는 '|' 와 '-' 연산자를 동시에 사용할 수 없습니다.");
    }
}