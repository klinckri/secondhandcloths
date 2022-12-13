package ch.zhaw.secondhandcloths.security;

import java.util.List;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

import ch.zhaw.secondhandcloths.model.Person;
import ch.zhaw.secondhandcloths.repository.PersonRepository;

public class UserValidator implements OAuth2TokenValidator<Jwt> {

    PersonRepository personRepository;

    public UserValidator(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        OAuth2Error error = new OAuth2Error("invalid_token", "The required email is missing", null);

        String userEmail = jwt.getClaimAsString("email");
        if (userEmail != null && !userEmail.equals("")) {
            if (!personRepository.findByEmail(userEmail).isPresent()) {
                personRepository.save(new Person("", "", userEmail, "", 0, "", ""));
            }
            return OAuth2TokenValidatorResult.success();
        }
        return OAuth2TokenValidatorResult.failure(error);
    }

    public static boolean userHasRole(Jwt jwt, String requiredRole) {
        if (jwt != null) {
            List<String> userRole = jwt.getClaimAsStringList("user_roles");
            return userRole.stream().filter(x -> x.equals(requiredRole)).count() == 1;
        }
        return false;
    }
}
