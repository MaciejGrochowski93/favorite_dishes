package maciej.grochowski.favorite_dishes.meal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Integer>, MealCustomRepository {

    Optional<Meal> findByMealName(String name);
}
