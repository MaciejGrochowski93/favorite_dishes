package maciej.grochowski.favorite_dishes.meal;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Service
public class MealService {

    private final MealRepository mealRepository;

    public List<Meal> getMealsOfGourmet(int gourmetId) {
        return mealRepository.findMealsOfGourmet(gourmetId);
    }

    public List<Meal> findAllMealsSortedByName() {
        List<Meal> listOfMeals = mealRepository.findAll();
        Comparator<Meal> compareByMealName = Comparator.comparing(Meal::getMealName);
        listOfMeals.sort(compareByMealName);
        return listOfMeals;
    }

    public void addMealFromDTO(MealDTO DTO) {
        Meal meal = makeMealFromDTO(DTO);
        String mealName = meal.getMealName();
        if (mealExists(mealName)) {
            throw new MealAlreadyExistsException(String.format(("Meal %s already exists"), mealName));
        }
        mealRepository.save(meal);
    }

    private Meal makeMealFromDTO(MealDTO DTO) {
        return Meal.builder()
                .mealName(DTO.getMealName())
                .mealCountry(DTO.getMealCountry())
                .mealTaste(DTO.getMealTaste())
                .build();
    }

    private boolean mealExists(String name) {
        return mealRepository.findByMealName(name).isPresent();
    }
}
