package maciej.grochowski.favorite_dishes.meal;

public class MealAlreadyExistsException extends RuntimeException {

    public MealAlreadyExistsException(String message) {
        super(message);
    }
}
