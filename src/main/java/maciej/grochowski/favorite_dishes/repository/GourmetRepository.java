package maciej.grochowski.favorite_dishes.repository;

import maciej.grochowski.favorite_dishes.entity.Gourmet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GourmetRepository extends JpaRepository<Gourmet, Integer> {

    Optional<Gourmet> findGourmetByEmail(String email);

    Gourmet findGourmetByGourmetId(Integer id);
}
