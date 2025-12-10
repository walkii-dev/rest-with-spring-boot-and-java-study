package com.educational.app.controllers.v1;

import com.educational.app.data.dto.v1.PersonDTO;
import com.educational.app.services.v1.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/person/v1")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping(value = "/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE,
                         MediaType.APPLICATION_XML_VALUE,
                         MediaType.APPLICATION_YAML_VALUE })
    public PersonDTO findById(@PathVariable("id") Long id){
        var person = service.findById(id);
        // person.setBirthDay(new Date());
        // person.setSensitiveData("Foo Bar");
        return person;
    }

    @GetMapping(
            produces = { MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE })
    public List<PersonDTO> findAll(){
        return service.findAll();
    }

    @PostMapping(
            produces = { MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE })
    public PersonDTO create (@RequestBody PersonDTO person){
        return service.create(person);

    }
    @PutMapping(
            produces = { MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE })
    public PersonDTO update (@RequestBody PersonDTO person){
        return service.update(person);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
    service.delete(id);
    return ResponseEntity.noContent().build();
    }
}
