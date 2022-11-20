package ch.zhaw.secondhandcloths.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginDTO {
    private String email;
    private String passwort;
}
