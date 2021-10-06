package maciej.grochowski.favorite_dishes.meal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import maciej.grochowski.favorite_dishes.gourmet.Gourmet;
import maciej.grochowski.favorite_dishes.gourmet.GourmetRating;

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

    @ManyToMany(mappedBy = "mealsList")
    private List<Gourmet> gourmetList;

    @OneToMany(mappedBy = "meal")
    List<GourmetRating> gourmetRating;

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
