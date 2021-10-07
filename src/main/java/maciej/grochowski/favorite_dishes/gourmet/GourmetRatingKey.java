package maciej.grochowski.favorite_dishes.gourmet;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
class GourmetRatingKey implements Serializable {

    @Column(name = "gourmet_id")
    Integer gourmetId;

    @Column(name = "meal_id")
    Integer mealId;
}
