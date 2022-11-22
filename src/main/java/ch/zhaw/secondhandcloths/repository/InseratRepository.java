package ch.zhaw.secondhandcloths.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.secondhandcloths.model.Inserat;
import ch.zhaw.secondhandcloths.model.KategorieEnum;

public interface InseratRepository  extends MongoRepository<Inserat,String>{
    List<Inserat> findByKategorie(KategorieEnum kategorie);
}
