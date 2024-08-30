package com.kgisl.springBoot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kgisl.springBoot.entity.Person;
import com.kgisl.springBoot.service.PersonService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RequestMapping("/person")
@RestController
public class PersonController {
    @Autowired
    PersonService personService;

	 public PersonController(PersonService personService) {
        this.personService=personService;
    }	

	@GetMapping
	public ResponseEntity<List<Person>> getAllPersons(){
		List<Person> persons = personService.getAllPersons();
		return  new ResponseEntity<>(persons,HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Person> createPerson(Person person){
		Person createdPerson = personService.createPerson(person);
		return new ResponseEntity<>(createdPerson,HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable Long id){
		Person person = personService.getPersonById(id);
		return new ResponseEntity<>(person, person!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        Person person = personService.updatePerson(id, updatedPerson);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePerson(@PathVariable Long id){
		personService.deletePerson(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


	
}
