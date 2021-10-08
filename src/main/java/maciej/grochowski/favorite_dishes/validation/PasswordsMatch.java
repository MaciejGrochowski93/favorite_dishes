package maciej.grochowski.favorite_dishes.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE}) // TYPE level annotation makes the whole DTO object perform validation
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordsMatchValidator.class)
@Documented
public @interface PasswordsMatch {

    String message() default "Passwords do not match.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
