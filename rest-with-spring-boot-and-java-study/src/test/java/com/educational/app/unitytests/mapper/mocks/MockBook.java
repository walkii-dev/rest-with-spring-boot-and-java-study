package com.educational.app.unitytests.mapper.mocks;

import com.educational.app.data.dto.v1.BookDTO;
import com.educational.app.model.Book;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockBook {
    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookDTO mockDTO() {
        return mockDTO(0);
    }

    public List<Book> mockEntityList() {
        List<Book> bookCollection = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            bookCollection.add(mockEntity(i));
        }
        return bookCollection;
    }

    public List<BookDTO> mockDTOList() {
        List<BookDTO> bookCollection = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            bookCollection.add(mockDTO(i));
        }
        return bookCollection;
    }

    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setTitle("Title Test"+number);
        book.setAuthor("Author Test"+number);
        book.setPrice(number.doubleValue());
        book.setLaunchDate(LocalDateTime.now());
        return book;
    }

    public BookDTO mockDTO(Integer number) {
        BookDTO book = new BookDTO();
        book.setTitle("Title Test"+number);
        book.setAuthor("Author Test"+number);
        book.setPrice(number.doubleValue());
        book.setLaunchDate(LocalDateTime.now());
        return book;
    }
}
