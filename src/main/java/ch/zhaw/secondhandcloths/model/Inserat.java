package ch.zhaw.secondhandcloths.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("inserat")
public class Inserat {
    @Id
    private String id;
    private String titel;
    private String beschreibung;
    private double preis;
    private String iban;
    private String personId;
}
