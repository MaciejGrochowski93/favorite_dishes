package maciej.grochowski.favorite_dishes.gourmet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import maciej.grochowski.favorite_dishes.meal.Meal;
import maciej.grochowski.favorite_dishes.meal.MealRating;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class GourmetRating {

    @EmbeddedId
    GourmetRatingKey id;

    @ManyToOne
    @MapsId("gourmetId")
    @JoinColumn(name = "gourmet_id")
    Gourmet gourmet;

    @ManyToOne
    @MapsId("mealId")
    @JoinColumn(name = "meal_id")
    Meal meal;

    MealRating mealRating;
}
