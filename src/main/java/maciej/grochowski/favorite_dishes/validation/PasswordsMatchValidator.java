package maciej.grochowski.favorite_dishes.validation;

import maciej.grochowski.favorite_dishes.DTO.GourmetRegisterDTO;
import org.springframework.context.annotation.Configuration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Configuration
public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        GourmetRegisterDTO DTO = (GourmetRegisterDTO) object;
        return DTO.getPassword().equals(DTO.getMatchingPassword());
    }
}
