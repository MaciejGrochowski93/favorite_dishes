package maciej.grochowski.favorite_dishes.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class GourmetRatingKey implements Serializable {

    @Column(name = "gourmet_id")
    Integer gourmetId;

    @Column(name = "meal_id")
    Integer mealId;
}
