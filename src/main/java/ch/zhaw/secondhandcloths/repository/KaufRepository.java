package ch.zhaw.secondhandcloths.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.secondhandcloths.model.Kauf;

public interface KaufRepository extends MongoRepository<Kauf,String>{
    
}
