package ch.zhaw.secondhandcloths.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.secondhandcloths.model.Person;

public interface PersonRepository extends MongoRepository<Person,String>{
    Optional<Person> findByEmail(String email);
}
