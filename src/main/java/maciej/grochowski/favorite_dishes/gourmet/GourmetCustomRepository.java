package maciej.grochowski.favorite_dishes.gourmet;

import org.springframework.stereotype.Component;

@Component
interface GourmetCustomRepository {

    Gourmet findGourmetById(int id);
}
