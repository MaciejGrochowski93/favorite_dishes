package maciej.grochowski.favorite_dishes.registration;

public class UserDoesNotExist extends RuntimeException{

    public UserDoesNotExist(String message) {
        super(message);
    }
}
