package maciej.grochowski.favorite_dishes.exception;

public class UserDoesNotExist extends RuntimeException{

    public UserDoesNotExist(String message) {
        super(message);
    }
}
