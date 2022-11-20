package ch.zhaw.secondhandcloths.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("person")
public class Person {

    @Id
    private String id;
    private String vorname;
    private String nachname;
    private String email;
    private String strasse;
    private int plz;
    private String ort;
    private String passwort;

}
