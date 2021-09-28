package maciej.grochowski.favorite_dishes.registration;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE}) // describes where i can use the annotations
@Retention(RetentionPolicy.RUNTIME) // specifies whether the annotation metadata can be accessed within runtime
@Constraint(validatedBy = EmailValidator.class) // defines the class that is going to validate the field
@Documented // specifies that the annotation will show up in the Javadoc for the annotated object
@interface ValidEmail {

    String message() default "Invalid email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {}; // object or the entity which is being carried, binds a method parameter to the payload of a message
}