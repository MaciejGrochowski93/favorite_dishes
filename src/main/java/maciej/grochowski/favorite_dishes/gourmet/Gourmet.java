package maciej.grochowski.favorite_dishes.gourmet;

import lombok.Data;
import maciej.grochowski.favorite_dishes.meal.Meal;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Gourmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gourmet_id")
    private Integer gourmetId;

    private String gourmetName;

    private Integer gourmetAge;

    @ManyToMany
    private Set<Meal> meals;
}
