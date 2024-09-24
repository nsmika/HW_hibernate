package org.example.controller;

import org.example.Person;
import org.example.PersonId;
import org.example.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // Получить список людей по городу
    @GetMapping("/by-city")
    public List<Person> getPersonsByCity(@RequestParam("city_of_living") String city) {
        return personRepository.findByCityOfLiving(city);
    }

    // Получить список людей младше определенного возраста, отсортированных по возрасту
    @GetMapping("/by-age")
    public List<Person> getPersonsByAge(@RequestParam("age") int age) {
        return personRepository.findByAgeLessThanOrderByAgeAsc(age);
    }

    // Получить информацию о человеке по имени и фамилии
    @GetMapping("/by-name-surname")
    public Optional<Person> getPersonByNameAndSurname(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        return personRepository.findByNameAndSurname(name, surname);
    }

    // Получить список всех людей
    @GetMapping
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    // Добавить нового человека
    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    // Обновить информацию о человеке по имени, фамилии и возрасту
    @PutMapping("/{name}/{surname}/{age}")
    public Person updatePerson(@PathVariable("name") String name,
                               @PathVariable("surname") String surname,
                               @PathVariable("age") int age,
                               @RequestBody Person personDetails) {
        PersonId personId = new PersonId(name, surname, age);
        return personRepository.findById(personId).map(person -> {
            person.setPhoneNumber(personDetails.getPhoneNumber());
            person.setCityOfLiving(personDetails.getCityOfLiving());
            return personRepository.save(person);
        }).orElseThrow(() -> new RuntimeException("Person not found"));
    }

    // Удалить человека по имени, фамилии и возрасту
    @DeleteMapping("/{name}/{surname}/{age}")
    public void deletePerson(@PathVariable("name") String name,
                             @PathVariable("surname") String surname,
                             @PathVariable("age") int age) {
        PersonId personId = new PersonId(name, surname, age);
        personRepository.deleteById(personId);
    }
}