package com.educational.app.services.v1;

import com.educational.app.controllers.v1.PersonController;
import com.educational.app.data.dto.v1.PersonDTO;
import com.educational.app.exceptions.RequiredObjectIsNullException;
import com.educational.app.exceptions.ResourceNotFoundException;
import com.educational.app.model.Person;
import com.educational.app.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.educational.app.mappers.ObjectMapper.parseObjectsList;
import static com.educational.app.mappers.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll(){
        var persons =  parseObjectsList(repository.findAll(), PersonDTO.class);
        persons.forEach(PersonService::addHateoasLinks);
        return persons;
    }

    public PersonDTO create (PersonDTO person){
        logger.info("creating a person");

        if (person == null){
            throw new RequiredObjectIsNullException();
        }

        var entity = parseObject(person, Person.class);
        var dto = parseObject(repository.save(entity),PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO update (PersonDTO person){
        logger.info("updating a person");

        if (person == null){
            throw new RequiredObjectIsNullException();
        }

        var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("person not found."));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = parseObject(repository.save(entity),PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete (Long id){
      logger.info("deleting a person");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("person not found."));
        repository.delete(entity);
    }



    public PersonDTO findById(Long id){
        logger.info("finding one person");

         var entity = repository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("person not found."));
        var dto = parseObject(entity, PersonDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    private static void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
