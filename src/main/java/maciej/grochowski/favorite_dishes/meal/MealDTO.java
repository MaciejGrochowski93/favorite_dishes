package maciej.grochowski.favorite_dishes.meal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import maciej.grochowski.favorite_dishes.gourmet.Gourmet;
import org.springframework.lang.Nullable;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealDTO {

    private String mealName;

    private String mealOriginCountry;

    private MealTaste mealTaste;

    @Nullable
    private MealRating mealRating;

    private Set<Gourmet> gourmetSet;
}
