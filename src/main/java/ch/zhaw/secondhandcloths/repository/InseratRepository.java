package ch.zhaw.secondhandcloths.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.secondhandcloths.model.Inserat;

public interface InseratRepository  extends MongoRepository<Inserat,String>{
    
}
