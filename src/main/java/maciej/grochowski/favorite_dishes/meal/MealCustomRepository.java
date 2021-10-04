package maciej.grochowski.favorite_dishes.meal;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MealCustomRepository {

    Meal findMealById(int id);

    List<Meal> findMealsOfGourmet(int gourmetId);
}
