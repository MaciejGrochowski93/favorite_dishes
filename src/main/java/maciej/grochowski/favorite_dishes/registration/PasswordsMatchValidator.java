package maciej.grochowski.favorite_dishes.registration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        GourmetRegisterDTO DTO = (GourmetRegisterDTO) object;
        return DTO.getDTOPassword().equals(DTO.getDTOMatchingPassword());
    }
}
