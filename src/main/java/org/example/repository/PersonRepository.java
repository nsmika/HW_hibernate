package org.example.repository;

import jakarta.persistence.Table;
import org.example.Person;
import org.example.PersonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//@Table(name = "persons", schema = "netology")
public interface PersonRepository extends JpaRepository<Person, PersonId> {

    List<Person> findByCityOfLiving(String city);

    List<Person> findByAgeLessThanOrderByAgeAsc(int age);

    Optional<Person> findByNameAndSurname(String name, String surname);
}

