package maciej.grochowski.favorite_dishes.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordsMatch
public class GourmetRegisterDTO {

    @NotBlank
    @Length(min = 1, max = 30, message = "Your name must consist of 1 to 30 signs.")
    private String DTOName;

    @NotBlank
    @ProperEmail
    private String DTOEmail;

    @NotBlank
    @Length(min = 5, max = 30, message = "Your password must consist of 5 to 30 signs.")
    private String DTOPassword;

    @NotBlank
    @Length(min = 5, max = 30, message = "Your password must consist of 5 to 30 signs.")
    private String DTOMatchingPassword;

    @NotBlank
    private Integer gourmetDTOAge;
}
