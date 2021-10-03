package maciej.grochowski.favorite_dishes.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordsMatch
public class GourmetRegisterDTO {

    @NotBlank
    @Length(min = 1, max = 30, message = "Your name must consist of 1 to 30 signs.")
    private String name;

    @NotBlank
    @ProperEmail
    private String email;

    @NotBlank
    @Length(min = 5, max = 30, message = "Your password must consist of 5 to 30 signs.")
    private String password;

    @NotBlank
    @Length(min = 5, max = 30, message = "Your password must consist of 5 to 30 signs.")
    private String matchingPassword;

    @NotNull
    @Past(message = "Cannot provide future date.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
}
