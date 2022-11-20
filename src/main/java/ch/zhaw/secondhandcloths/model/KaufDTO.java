package ch.zhaw.secondhandcloths.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class KaufDTO {
    private String kartennummer;
    private String datum;
    private int cvv;
    private List<Inserat> inserate = new ArrayList<>();
}
