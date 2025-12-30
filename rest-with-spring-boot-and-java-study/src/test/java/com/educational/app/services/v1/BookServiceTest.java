package com.educational.app.services.v1;

import com.educational.app.data.dto.v1.BookDTO;
import com.educational.app.exceptions.RequiredObjectIsNullException;
import com.educational.app.model.Book;
import com.educational.app.repository.BookRepository;
import com.educational.app.unitytests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    MockBook input;

    @InjectMocks
    BookService service;

    @Mock
    BookRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Book person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));

        var result = service.findOneBook(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/books/v1/1")
                        && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("POST")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("PUT")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/books/v1/1")
                        && link.getType().equals("DELETE")));

        assertEquals("Title Test1", result.getTitle());
        assertEquals("Author Test1",result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertEquals(1.0,result.getPrice());

    }

    @Test
    void create() {
        Book person = input.mockEntity(1);
        Book persisted = person;
        persisted.setId(1L);
        BookDTO dto = input.mockDTO(1);

        when(repository.save(person)).thenReturn(persisted);

        var result = service.createBook(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/books/v1/1")
                        && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("POST")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("PUT")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/books/v1/1")
                        && link.getType().equals("DELETE")));

        assertEquals("Title Test1", result.getTitle());
        assertEquals("Author Test1",result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertEquals(1.0,result.getPrice());
    }

    @Test
    void createWithNullBook() {
        Exception ex = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    service.createBook(null);
                });
        String expectedMessage = "It is not allowed to persist a null object.";
        String actualMessage = ex.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {
        Book books = input.mockEntity(1);
        Book persisted = books;
        persisted.setId(1L);

        var dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(books));
        when(repository.save(books)).thenReturn(persisted);

        var result = service.updateBook(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/books/v1/1")
                        && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("POST")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("PUT")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/books/v1/1")
                        && link.getType().equals("DELETE")));

        assertEquals("Title Test1", result.getTitle());
        assertEquals("Author Test1",result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertEquals(1.0,result.getPrice());
    }
    @Test
    void updateWithNullBook() {
        Exception ex = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    service.updateBook(null);
                });
        String expectedMessage = "It is not allowed to persist a null object.";
        String actualMessage = ex.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete() {
        Book books = input.mockEntity(1);
        books.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(books));

        service.deleteBook(1L);
        verify(repository,times(1)).findById(anyLong());
        verify(repository,times(1)).delete(any(Book.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll() {
        List<Book> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<BookDTO> bookCollection = service.findAllBooks();

        assertNotNull(bookCollection);

        assertEquals(14,bookCollection.size());

        var bookOne = bookCollection.get(1);

        assertNotNull(bookOne);

        assertNotNull(bookOne.getId());

        assertEquals("Title Test1", bookOne.getTitle());
        assertEquals("Author Test1",bookOne.getAuthor());
        assertNotNull(bookOne.getLaunchDate());
        assertEquals(1.0,bookOne.getPrice());

        assertNotNull(bookOne.getLinks());

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/books/v1/1")
                        && link.getType().equals("GET")));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("GET")));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("POST")));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("PUT")));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/books/v1/1")
                        && link.getType().equals("DELETE")));


        var bookEight = bookCollection.get(8);

        assertNotNull(bookEight);
        assertNotNull(bookEight.getId());

        assertEquals("Title Test8", bookEight.getTitle());
        assertEquals("Author Test8",bookEight.getAuthor());
        assertNotNull(bookEight.getLaunchDate());
        assertEquals(8.0,bookEight.getPrice());

        assertNotNull(bookEight.getLinks());

        assertNotNull(bookEight.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/book/v1/8")
                        && link.getType().equals("GET")));

        assertNotNull(bookEight.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("GET")));

        assertNotNull(bookEight.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("POST")));

        assertNotNull(bookEight.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("PUT")));

        assertNotNull(bookEight.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/book/v1/8")
                        && link.getType().equals("DELETE")));


        var bookTwelve = bookCollection.get(12);

        assertNotNull(bookTwelve);
        assertNotNull(bookTwelve.getId());

        assertEquals("Title Test12", bookTwelve.getTitle());
        assertEquals("Author Test12",bookTwelve.getAuthor());
        assertNotNull(bookTwelve.getLaunchDate());
        assertEquals(12.0,bookTwelve.getPrice());

        assertNotNull(bookTwelve.getLinks());

        assertNotNull(bookTwelve.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/books/v1/12")
                        && link.getType().equals("GET")));

        assertNotNull(bookTwelve.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("GET")));

        assertNotNull(bookTwelve.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("POST")));

        assertNotNull(bookTwelve.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("PUT")));

        assertNotNull(bookTwelve.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/books/v1/12")
                        && link.getType().equals("DELETE")));

    }
}