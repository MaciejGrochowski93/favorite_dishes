package maciej.grochowski.favorite_dishes.gourmet;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import maciej.grochowski.favorite_dishes.meal.Meal;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@Entity
public class Gourmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gourmet_id")
    private Integer gourmetId;

    private String gourmetName;

    private String gourmetEmail;

    private Integer gourmetAge;

    @ManyToMany
    private Set<Meal> mealsSet;
}
