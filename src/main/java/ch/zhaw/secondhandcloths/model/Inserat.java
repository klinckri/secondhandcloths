package ch.zhaw.secondhandcloths.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Document("inserat")
public class Inserat {
    @Id
    private String id;
    @NonNull
    private String titel;
    @NonNull
    private String beschreibung;
    @NonNull
    private Double preis;
    @NonNull
    private String iban;
    @NonNull
    private KategorieEnum kategorie;
    @NonNull
    private Person personId;
    @NonNull
    private String filename;
}
