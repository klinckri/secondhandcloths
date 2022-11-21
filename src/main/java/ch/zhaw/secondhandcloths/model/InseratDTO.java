package ch.zhaw.secondhandcloths.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class InseratDTO {
    private String titel;
    private String beschreibung;
    private double preis;
    private String iban;
    private String personId;
}
