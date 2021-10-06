package maciej.grochowski.favorite_dishes.meal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import maciej.grochowski.favorite_dishes.gourmet.Gourmet;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.persistence.ElementCollection;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealDTO {

    @NotBlank
    @Length(min = 1, max = 40, message = "Meal name must consist of 1 to 40 letters.")
    private String mealName;

    @NotBlank
    @Length(min = 1, max = 40, message = "Country name must consist of 1 to 40 letters.")
    private String mealCountry;

    private MealTaste mealTaste;

    @Nullable
    private MealRating mealRating;

    @ElementCollection(targetClass = Gourmet.class)
    private List<Gourmet> gourmetList;
}
