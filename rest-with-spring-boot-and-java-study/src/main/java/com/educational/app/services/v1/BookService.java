package com.educational.app.services.v1;

import com.educational.app.controllers.v1.BookController;
import com.educational.app.data.dto.v1.BookDTO;
import com.educational.app.data.dto.v1.PersonDTO;
import com.educational.app.exceptions.RequiredObjectIsNullException;
import com.educational.app.exceptions.ResourceNotFoundException;
import com.educational.app.model.Book;
import com.educational.app.model.Person;
import com.educational.app.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.educational.app.mappers.ObjectMapper.parseObject;
import static com.educational.app.mappers.ObjectMapper.parseObjectsList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    private Logger logger = LoggerFactory.getLogger(BookService.class.getName());

    @Autowired
    private BookRepository repository;

    public BookDTO findOneBook(Long id){
        logger.info("finding some book");

        var book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("book not found on database."));

        var dto = parseObject(book, BookDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    public List<BookDTO> findAllBooks(){
        var books = repository.findAll();

        var dtoList = parseObjectsList(books, BookDTO.class);
        for (BookDTO book : dtoList) {
            addHateoasLinks(book);
        }
        return dtoList;
    }

    public BookDTO createBook (BookDTO newBook){
        logger.info("putting a book on database");

        if (newBook == null){
            throw new RequiredObjectIsNullException();
        }

        var entity = parseObject(newBook, Book.class);
        var dto = parseObject(repository.save(entity),BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO updateBook (BookDTO bookData){
        logger.info("updating a person");

        if (bookData == null){
            throw new RequiredObjectIsNullException();
        }

        var entity = repository.findById(bookData.getId()).orElseThrow(() -> new ResourceNotFoundException("book not found on database."));
        entity.setTitle(bookData.getTitle());
        entity.setAuthor(bookData.getAuthor());
        entity.setPrice(bookData.getPrice());
        entity.setLaunchDate(bookData.getLaunchDate());

        var dto = parseObject(repository.save(entity),BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void deleteBook (Long id){
        logger.info("deleting a book");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found."));
        repository.delete(entity);
    }

    private static void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findOne(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

}
