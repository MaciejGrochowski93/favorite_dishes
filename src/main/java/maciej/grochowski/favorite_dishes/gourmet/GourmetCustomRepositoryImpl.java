package maciej.grochowski.favorite_dishes.gourmet;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GourmetCustomRepositoryImpl implements GourmetCustomRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Gourmet findGourmetById(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Gourmet> criteriaQuery = builder.createQuery(Gourmet.class);
        Root<Gourmet> root = criteriaQuery.from(Gourmet.class);

        Predicate gourmetIdPredicate = builder.equal(root.get("gourmetId"), id);
        criteriaQuery.where(gourmetIdPredicate);

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
