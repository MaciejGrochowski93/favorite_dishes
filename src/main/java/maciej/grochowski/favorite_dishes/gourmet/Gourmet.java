package maciej.grochowski.favorite_dishes.gourmet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import maciej.grochowski.favorite_dishes.meal.Meal;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Gourmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gourmet_id")
    private Integer gourmetId;

    private String name;

    private String email;

    private String password;

    private LocalDate birthDate;

    @ElementCollection(targetClass = String.class)
    private List<String> roles;

    @OneToMany(mappedBy = "gourmet")
    List<GourmetRating> ratingList;
}