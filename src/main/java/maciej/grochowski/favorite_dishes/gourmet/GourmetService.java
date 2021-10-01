package maciej.grochowski.favorite_dishes.gourmet;

import lombok.AllArgsConstructor;
import maciej.grochowski.favorite_dishes.registration.GourmetRegisterDTO;
import maciej.grochowski.favorite_dishes.registration.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashSet;

@AllArgsConstructor
@Service
class GourmetService {

    private final GourmetRepository gourmetRepository;

    public void registerGourmet(GourmetRegisterDTO DTO) {
        String providedEmail = DTO.getDTOEmail();
        if (emailExists(providedEmail)) {
            throw new UserAlreadyExistsException(String.format("Email %s is already registered.", providedEmail));
        }
        Gourmet gourmet = createGourmetFromDTOs(DTO);
        gourmetRepository.save(gourmet);
    }

    private Gourmet createGourmetFromDTOs(GourmetRegisterDTO gourmetDTO) {
        return Gourmet.builder()
                .name(gourmetDTO.getDTOName())
                .birthDate(gourmetDTO.getDTOBirthDate())
                .email(gourmetDTO.getDTOEmail())
                .password(gourmetDTO.getDTOPassword())
                .mealsSet(new LinkedHashSet<>())
                .roles(Arrays.asList("ROLE_USER"))
                .build();
    }

    private boolean emailExists(String email) {
        return gourmetRepository.findGourmetByEmail(email).isPresent();
    }
}