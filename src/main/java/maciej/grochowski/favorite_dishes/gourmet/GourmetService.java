package maciej.grochowski.favorite_dishes.gourmet;

import lombok.AllArgsConstructor;
import maciej.grochowski.favorite_dishes.registration.GourmetRegisterDTO;
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
}