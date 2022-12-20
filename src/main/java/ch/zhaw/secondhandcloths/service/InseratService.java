package ch.zhaw.secondhandcloths.service;

import static java.util.Objects.isNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ch.zhaw.secondhandcloths.model.Inserat;
import ch.zhaw.secondhandcloths.model.InseratDTO;
import ch.zhaw.secondhandcloths.model.InseratStateEnum;
import ch.zhaw.secondhandcloths.model.Person;
import ch.zhaw.secondhandcloths.repository.InseratRepository;
import ch.zhaw.secondhandcloths.repository.PersonRepository;

@Service
public class InseratService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private InseratRepository inseratRepository;

    public Inserat createInserat(InseratDTO inseratDTO, String userEmail) throws IOException {

        Person person = personLookup(userEmail);
        MultipartFile file = inseratDTO.getFile();
        if (isNull(file) || isNull(file.getContentType()) || isNull(file.getBytes())) {
            throw new RuntimeException("Invalid file");
        }

        String pictureID = UUID.randomUUID().toString();
        String type = file.getContentType().replace("image/", "");
        Path path = Paths.get("src/main/resources/static/pictures");
        FileUtils.writeByteArrayToFile(new File(path + "/" + pictureID + "." + type), file.getBytes());
        String filename = pictureID + "." + type;
        Inserat inserat = new Inserat(inseratDTO.getTitel(), inseratDTO.getBeschreibung(), inseratDTO.getPreis(),
                inseratDTO.getKategorie(), person,filename);
        return inseratRepository.save(inserat);
        
    }

    private Person personLookup(String email) {
        Optional<Person> person = personRepository.findByEmail(email);
        if (person.isPresent()) {
            return person.get();
        }
        throw new RuntimeException("Cannot find person");
    }

    public boolean validateUserOnInserat(String email, String inseratId) {
        Person person = personLookup(email);
        Optional<Inserat> inserat = inseratRepository.findById(inseratId);
        if(!inserat.isPresent()) {
            throw new RuntimeException("Invalid Inserat");
        }
        return person.getId().equals(inserat.get().getPersonId().getId());
    }

    public void deleteInserat(String inseratId) {
        inseratRepository.deleteById(inseratId);
    }

    public List<Inserat> getAllInserate() {
        return inseratRepository.findByInseratState(InseratStateEnum.INSERIERT);
    }

    public Inserat getInseratById(String inseratId) {
        Optional<Inserat> inserat = inseratRepository.findById(inseratId);
        if(inserat.isPresent()) {
            return inserat.get();
        }
        throw new RuntimeException("Invalid Inserat");
    }

    public List<Inserat> getAllInserateFromPerson(String email) {
        Person person = personLookup(email);
        return inseratRepository.findByPersonId(person);
    }
}
