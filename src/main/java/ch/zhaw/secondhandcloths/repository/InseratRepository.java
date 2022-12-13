package ch.zhaw.secondhandcloths.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import ch.zhaw.secondhandcloths.model.Inserat;
import ch.zhaw.secondhandcloths.model.InseratStateEnum;
import ch.zhaw.secondhandcloths.model.KategorieEnum;
import ch.zhaw.secondhandcloths.model.Person;

public interface InseratRepository  extends MongoRepository<Inserat,String>{
    List<Inserat> findByKategorie(KategorieEnum kategorie);
    List<Inserat> findByPersonId(Person personId);
    @Query("select * from Inserat where kaeuferId = ?1 AND inseratState = ?2")
    List<Inserat> findByKaeuferIdAndInseratState(String kaeuferId, InseratStateEnum inseratState);
    List<Inserat> findByInseratState(InseratStateEnum inseratState);
}
