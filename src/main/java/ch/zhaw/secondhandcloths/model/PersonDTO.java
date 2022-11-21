package ch.zhaw.secondhandcloths.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PersonDTO {
    private String vorname;
    private String nachname;
    private String email;
    private String strasse;
    private int plz;
    private String ort;
    private String passwort;

}
