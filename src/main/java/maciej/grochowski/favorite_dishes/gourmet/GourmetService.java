package maciej.grochowski.favorite_dishes.gourmet;

import lombok.AllArgsConstructor;
import maciej.grochowski.favorite_dishes.meal.Meal;
import maciej.grochowski.favorite_dishes.meal.MealRating;
import maciej.grochowski.favorite_dishes.registration.GourmetRegisterDTO;
import maciej.grochowski.favorite_dishes.registration.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
class GourmetService {

    private final GourmetRepository gourmetRepository;

    public Set<Meal> getMealsSetOfGourmet(int gourmetId) {
        Gourmet gourmetById = gourmetRepository.findGourmetById(gourmetId);
        return gourmetById.getMealsSet();
    }

    public void addMealToGourmetSet(int gourmetId, Meal meal, MealRating rating) {
        Optional<Gourmet> gourmetById = gourmetRepository.findById(gourmetId);
        gourmetById.ifPresent(gourmet -> {
            meal.setMealRating(rating);
            gourmet.getMealsSet().add(meal);
        });
    }

    public void registerGourmet(GourmetRegisterDTO DTO) {
        String providedEmail = DTO.getEmail();
        if (emailExists(providedEmail)) {
            throw new UserAlreadyExistsException(String.format("Email %s is already registered.", providedEmail));
        }
        Gourmet gourmet = createGourmetFromDTOs(DTO);
        gourmetRepository.save(gourmet);
    }

    private Gourmet createGourmetFromDTOs(GourmetRegisterDTO gourmetDTO) {
        return Gourmet.builder()
                .name(gourmetDTO.getName())
                .birthDate(gourmetDTO.getBirthDate())
                .email(gourmetDTO.getEmail())
                .password(gourmetDTO.getPassword())
                .mealsSet(new LinkedHashSet<>())
                .roles(Arrays.asList("ROLE_USER"))
                .build();
    }

    private boolean emailExists(String email) {
        return gourmetRepository.findGourmetByEmail(email).isPresent();
    }
}