package com.educational.app.services.v2;

import com.educational.app.data.dto.v1.PersonDTO;
import com.educational.app.data.dto.v2.PersonDTOV2;
import com.educational.app.exceptions.ResourceNotFoundException;
import com.educational.app.mappers.custom.PersonMapper;
import com.educational.app.model.Person;
import com.educational.app.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static com.educational.app.mappers.ObjectMapper.parseObject;
import static com.educational.app.mappers.ObjectMapper.parseObjectsList;

@Service
public class PersonServiceV2 {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServiceV2.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    public List<PersonDTO> findAll(){
        return parseObjectsList(repository.findAll(), PersonDTO.class);
    }

    public PersonDTOV2 createv2 (PersonDTOV2 person){
        logger.info("creating a person V2!");
        var entity = converter.convertDTOtoEntity(person);
        return converter.convertEntityToDTO(repository.save(entity));
    }

    public PersonDTO update (PersonDTO person){
        logger.info("updating a person");
        var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("person not found."));
        entity.setFirstName("abobra");
        entity.setLastName("abobra");
        entity.setAddress("Rua da abóbra");
        entity.setGender("male");

        return parseObject(repository.save(entity),PersonDTO.class);
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

         return parseObject(entity, PersonDTO.class);
    }
}
