package maciej.grochowski.favorite_dishes.gourmet;

import lombok.AllArgsConstructor;
import maciej.grochowski.favorite_dishes.registration.GourmetRegisterDTO;
import maciej.grochowski.favorite_dishes.registration.UserAlreadyExistsException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.LinkedHashSet;

@Service
@AllArgsConstructor
class GourmetService {

    private final GourmetRepository gourmetRepository;

    public void addGourmetToModel(Model model) {
        GourmetRegisterDTO gourmetDTO = new GourmetRegisterDTO();
        model.addAttribute("gourmet", gourmetDTO);
    }

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
                .gourmetName(gourmetDTO.getDTOName())
                .gourmetAge(gourmetDTO.getGourmetDTOAge())
                .gourmetEmail(gourmetDTO.getDTOEmail())
                .mealsSet(new LinkedHashSet<>())
                .build();
    }

    private boolean emailExists(String email) {
        return gourmetRepository.findGourmetByGourmetEmail(email).isPresent();
    }
}