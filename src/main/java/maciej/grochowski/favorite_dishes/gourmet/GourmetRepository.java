package maciej.grochowski.favorite_dishes.gourmet;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GourmetRepository extends JpaRepository<Gourmet, Integer> {

    Optional<Gourmet> findGourmetByGourmetEmail(String email);
}
