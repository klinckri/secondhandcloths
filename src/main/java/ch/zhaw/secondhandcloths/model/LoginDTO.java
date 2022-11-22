package ch.zhaw.secondhandcloths.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginDTO {
    private String email;
    private String passwort;
}
