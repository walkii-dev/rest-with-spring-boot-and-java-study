package com.educational.app.services;

import com.educational.app.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());


    public List<Person> findAll(){
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person create (Person person){
        logger.info("creating a person");
        return person;
    }

    public Person update (Person person){
        logger.info("updating a person");
        return person;
    }

    public void delete (String id){
      logger.info("deleting a person");
    }



    public Person findById(String id){
        logger.info("finding one person");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Lucas");
        person.setLastName("Oliveira");
        person.setAddress("Niterói-RJ");
        person.setGender("male");

        return person;

    }
    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("firstName" + i);
        person.setLastName("lastName" + i);
        person.setAddress("Some place in the earth");
        person.setGender("male");

        return person;
    }
}
