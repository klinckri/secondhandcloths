package ch.zhaw.secondhandcloths.model;

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
@RequiredArgsConstructor
@Document("person")
public class Person {

    @Id
    private String id;
    @NonNull
    private String email;
    private List<Inserat> warenkorb = new ArrayList<>();

}
