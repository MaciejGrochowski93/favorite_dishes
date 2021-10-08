package maciej.grochowski.favorite_dishes.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import maciej.grochowski.favorite_dishes.DTO.MealRating;
import maciej.grochowski.favorite_dishes.DTO.MealTaste;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Integer mealId;

    private String mealName;

    private String mealCountry;

    private MealTaste mealTaste;

    private MealRating mealRating;

    @OneToMany(mappedBy = "meal")
    List<GourmetRating> ratingList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal)) return false;
        Meal meal = (Meal) o;
        return getMealName().equals(meal.getMealName()) && getMealTaste() == meal.getMealTaste();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMealName(), getMealTaste());
    }
}
