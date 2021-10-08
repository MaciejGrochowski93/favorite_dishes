package maciej.grochowski.favorite_dishes.service;

import lombok.AllArgsConstructor;
import maciej.grochowski.favorite_dishes.DTO.MealDTO;
import maciej.grochowski.favorite_dishes.entity.Meal;
import maciej.grochowski.favorite_dishes.exception.MealAlreadyExistsException;
import maciej.grochowski.favorite_dishes.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Service
public class MealService {

    private final MealRepository mealRepository;

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
