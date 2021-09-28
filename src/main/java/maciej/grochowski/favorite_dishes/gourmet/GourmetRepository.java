package maciej.grochowski.favorite_dishes.gourmet;

import org.springframework.data.jpa.repository.JpaRepository;

interface GourmetRepository extends JpaRepository<Gourmet, Integer> {
}
