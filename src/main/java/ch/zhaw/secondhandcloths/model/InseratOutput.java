package ch.zhaw.secondhandcloths.model;

import java.io.File;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InseratOutput {
    private String id;
    private String titel;
    private String beschreibung;
    private Double preis;
    private String iban;
    private KategorieEnum kategorie;
    private Person personId;
    private File file;
}
