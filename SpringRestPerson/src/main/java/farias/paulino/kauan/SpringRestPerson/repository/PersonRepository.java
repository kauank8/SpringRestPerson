package farias.paulino.kauan.SpringRestPerson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import farias.paulino.kauan.SpringRestPerson.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>  {

}
