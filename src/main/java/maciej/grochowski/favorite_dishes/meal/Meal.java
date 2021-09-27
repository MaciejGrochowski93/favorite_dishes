package maciej.grochowski.favorite_dishes.meal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import maciej.grochowski.favorite_dishes.gourmet.Gourmet;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Integer mealId;

    private String mealName;

    private String mealOriginCountry;

    private MealTaste mealTaste;

    @Nullable
    private MealEnjoy mealEnjoy;

    @ManyToMany
    private Set<Gourmet> gourmetSet;
}
