package com.educational.app.services;

import com.educational.app.exceptions.ResourceNotFoundException;
import com.educational.app.model.Person;
import com.educational.app.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll(){
        return repository.findAll();
    }

    public Person create (Person person){
        logger.info("creating a person");
        return repository.save(person);
    }

    public Person update (Person person){
        logger.info("updating a person");
        var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("person not found."));
        entity.setFirstName("abobra");
        entity.setLastName("abobra");
        entity.setAddress("Rua da abóbra");
        entity.setGender("male");

        return repository.save(entity);
    }

    public void delete (Long id){
      logger.info("deleting a person");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("person not found."));
        repository.delete(entity);
    }



    public Person findById(Long id){
        logger.info("finding one person");

         return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("person not found."));

    }
}
