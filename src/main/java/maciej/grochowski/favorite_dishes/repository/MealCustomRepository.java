package maciej.grochowski.favorite_dishes.repository;

import maciej.grochowski.favorite_dishes.entity.Meal;
import org.springframework.stereotype.Component;

@Component
public interface MealCustomRepository {

    Meal findMealById(int id);
}
