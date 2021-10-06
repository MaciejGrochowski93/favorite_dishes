package maciej.grochowski.favorite_dishes.gourmet;

import lombok.AllArgsConstructor;
import maciej.grochowski.favorite_dishes.meal.Meal;
import maciej.grochowski.favorite_dishes.meal.MealRating;
import maciej.grochowski.favorite_dishes.meal.MealRepository;
import maciej.grochowski.favorite_dishes.registration.GourmetRegisterDTO;
import maciej.grochowski.favorite_dishes.registration.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class GourmetService {

    private final GourmetRepository gourmetRepository;
    private final MealRepository mealRepository;

    public void addMealToGourmetList(int gourmetId, int mealId, MealRating rating) {
        List<Meal> mealsOfGourmet = findMealsByGourmetId(gourmetId);
        Meal meal = mealRepository.findMealById(mealId);
        if (!mealsOfGourmet.contains(meal)) {
            Meal ratedMeal = Meal.builder()
                    .mealName(meal.getMealName())
                    .mealTaste(meal.getMealTaste())
                    .mealCountry(meal.getMealCountry())
                    .mealRating(rating)
                    .build();
            mealsOfGourmet.add(ratedMeal);
        }
    }

    public List<Meal> findMealsByGourmetId(int id) {
        return mealRepository.findMealsOfGourmet(id);
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
                .mealsList(new ArrayList<>())
                .roles(Arrays.asList("ROLE_USER"))
                .build();
    }

    private boolean emailExists(String email) {
        return gourmetRepository.findGourmetByEmail(email).isPresent();
    }

    public List<Gourmet> findAllGourmetsSortByName() {
        List<Gourmet> gourmets = gourmetRepository.findAll();
        Comparator<Gourmet> compareByName = Comparator.comparing(Gourmet::getName);
        gourmets.sort(compareByName);
        return gourmets;
    }
}