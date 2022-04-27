package com.demo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.model.Person;
import com.demo.repository.PersonRepository;
import com.demo.service.IPersonService;

@Service
public class PersonServiceImpl implements IPersonService{

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person savePerson(@Valid Person person) {
        // TODO Auto-generated method stub
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(Long id, @Valid Person person) {
        // TODO Auto-generated method stub
        Optional<Person> existPerson = personRepository.findById(id);
        if (existPerson.isPresent()) {
            Person personUpdate = personRepository.findById(id).get();
            personUpdate.setName(person.getName());
            personUpdate.setSurname(person.getSurname());
            personUpdate.setIdentification(person.getIdentification());
            personUpdate.setEmployeed(person.isEmployeed());
            return personRepository.save(personUpdate);
        } else {
            return null;
        }
    }

    @Override
    public String deletePerson(Long id) {
        // TODO Auto-generated method stub
        Optional<Person> existPerson = personRepository.findById(id);
        if (existPerson.isPresent()) {
            personRepository.deleteById(existPerson.get().getPersonId());
            return "DELETED SUCCESSFULLY";
        }
        return null;
    }

    @Override
    public List<Person> searchByNamePersonages(String name) {
        // TODO Auto-generated method stub
        List<Person> persons = personRepository.findByNameContainingIgnoreCase(name);
        return persons;
    }

    @Override
    public Page<Person> readAllPerson(int pageNo, int pageSize, String sortBy, String ordered) {
        // TODO Auto-generated method stubc
        if (ordered.equalsIgnoreCase("asc")) {
            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
            Page<Person> pagePerson = personRepository.findAll(pageable);
            return pagePerson;
        } else {
            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
            Page<Person> pagePerson = personRepository.findAll(pageable);
            return pagePerson;
        }
    }

}
