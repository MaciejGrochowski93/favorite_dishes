package maciej.grochowski.favorite_dishes.exception;

public class MealAlreadyExistsException extends RuntimeException {

    public MealAlreadyExistsException(String message) {
        super(message);
    }
}
