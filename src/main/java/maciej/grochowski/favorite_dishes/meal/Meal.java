package maciej.grochowski.favorite_dishes.meal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import maciej.grochowski.favorite_dishes.gourmet.Gourmet;

import javax.persistence.*;
import java.util.Set;

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

    private String mealOriginCountry;

    private MealTaste mealTaste;

    private MealRating mealRating;

    @ManyToMany(mappedBy = "mealsSet")
    private Set<Gourmet> gourmetSet;
}
