package ch.zhaw.secondhandcloths.model;

import java.util.ArrayList;
import java.util.List;

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
@Document("kauf")
public class Kauf {
    @Id
    private String id;
    @NonNull
    private Person personId;
    @NonNull
    private List<Inserat> inseratId = new ArrayList<>();

    public Kauf(Person personId, List<Inserat> inserate) {
        this.personId = personId;
        this.inseratId = inserate;
    }
}
