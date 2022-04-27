package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Person;
import com.demo.service.IPersonService;

@RestController
@RequestMapping("/api/v1")
public class PersonController {

    @Autowired
    private IPersonService iPersonService;

    @GetMapping("/persons")
    public ResponseEntity<Page<Person>> getAllPersons(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "sortBy", defaultValue = "personId") String sortBy,
            @RequestParam(name = "ordered", defaultValue = "asc") String ordered) {
        Page<Person> persons = iPersonService.readAllPerson(page, size, sortBy, ordered);
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping(path = "/persons", params = "name")
    public ResponseEntity<List<Person>> searchByName(@RequestParam(value = "name") String name){
        List<Person> persons = iPersonService.searchByNamePersonages(name);
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @PostMapping("/persons")
    public ResponseEntity<Person> savePerson(@RequestBody Person person){
        Person personSaved = iPersonService.savePerson(person);
        return new ResponseEntity<>(personSaved, HttpStatus.CREATED);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable(name = "id") Long id, @RequestBody Person person){
        Person personUpdated = iPersonService.savePerson(person);
        return new ResponseEntity<>(personUpdated, HttpStatus.CREATED);
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable(name = "id") Long id){
        String message = iPersonService.deletePerson(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
