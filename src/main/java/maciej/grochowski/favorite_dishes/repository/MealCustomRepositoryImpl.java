package maciej.grochowski.favorite_dishes.repository;

import maciej.grochowski.favorite_dishes.entity.Meal;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MealCustomRepositoryImpl implements MealCustomRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Meal findMealById(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Meal> criteriaQuery = builder.createQuery(Meal.class);
        Root<Meal> root = criteriaQuery.from(Meal.class);

        Predicate idPredicate = builder.equal(root.get("mealId"), id);
        criteriaQuery.where(idPredicate);

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
