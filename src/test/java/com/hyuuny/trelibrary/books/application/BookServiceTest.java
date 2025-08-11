package com.hyuuny.trelibrary.books.application;

import com.hyuuny.trelibrary.BaseTest;
import com.hyuuny.trelibrary.books.domain.Book;
import com.hyuuny.trelibrary.books.domain.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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

        BookDto.Response response = bookService.getBook(savedBook.getId());

        assertThat(response.getId()).isEqualTo(savedBook.getId());
        assertThat(response.getTitle()).isEqualTo(savedBook.getTitle());
        assertThat(response.getSubTitle()).isEqualTo(savedBook.getSubTitle());
        assertThat(response.getAuthor()).isEqualTo(savedBook.getAuthor());
        assertThat(response.getPublished()).isEqualTo(savedBook.getPublished());
        assertThat(response.getIsbn()).isEqualTo(savedBook.getIsbn());
        assertThat(response.getImageUrl()).isEqualTo(savedBook.getImageUrl());
        assertThat(response.getCreatedAt()).isEqualTo(savedBook.getCreatedAt());
        assertThat(response.getLastModifiedAt()).isEqualTo(savedBook.getLastModifiedAt());
    }

    @DisplayName("도서 아이디로 도서를 상세 조회할 때 도서가 없으면 예외가 발생한다")
    @Test
    void getBook_NotFound_ThrowsException() {
        Long nonExistentId = 9999L;

        assertThatThrownBy(() -> bookService.getBook(nonExistentId))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Book notFound id:" + nonExistentId);
    }
}