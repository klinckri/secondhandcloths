package ch.zhaw.secondhandcloths.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class KaufDTO {
    private String personId;
    private String kartennummer;
    private String datum;
    private int cvv;
    private List<String> inserate = new ArrayList<>();
}
