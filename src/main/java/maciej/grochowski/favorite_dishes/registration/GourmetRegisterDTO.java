package maciej.grochowski.favorite_dishes.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordsMatch
public class GourmetRegisterDTO {

    @NotBlank
    private String DTOName;

    @NotBlank
    private String DTOEmail;

    @NotBlank
    @ValidEmail
    private String DTOPassword;

    @NotBlank
    @ValidEmail
    private String DTOMatchingPassword;

    @NotBlank
    private Integer gourmetDTOAge;
}
