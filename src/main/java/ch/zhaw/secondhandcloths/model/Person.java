package ch.zhaw.secondhandcloths.model;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Document("person")
public class Person {

    @Id
    private String id;
    @NonNull
    private String vorname;
    @NonNull
    private String nachname;
    @NonNull
    private String email;
    @NonNull
    private String strasse;
    @NonNull
    private Integer plz;
    @NonNull
    private String ort;
    @NonNull
    private String passwort;
    private List<Inserat> warenkorb = new ArrayList<>();

}
