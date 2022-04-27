package com.demo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import com.demo.model.Person;

public interface IPersonService {
    Person savePerson(@Valid Person person);

    Person updatePerson(Long id,@Valid Person person);

    String deletePerson(Long id);

    List<Person> searchByNamePersonages(String name);

    Page<Person> readAllPerson(int pageNo, int pageSize, String sortBy, String ordered);
}
