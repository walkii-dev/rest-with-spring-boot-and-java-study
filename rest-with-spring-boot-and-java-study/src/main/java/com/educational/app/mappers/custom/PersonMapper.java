package com.educational.app.mappers.custom;

import com.educational.app.data.dto.v2.PersonDTOV2;
import com.educational.app.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonDTOV2 convertEntityToDTO (Person person){
        var dto = new PersonDTOV2();
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setAddress(person.getAddress());
        dto.setBirthDay(new Date());
        dto.setGender(person.getGender());
        return dto;
    }

    public Person convertDTOtoEntity (PersonDTOV2 person){
        var entity = new Person();
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
    //  entity.setBirthDay(new Date());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return entity;
    }
}
