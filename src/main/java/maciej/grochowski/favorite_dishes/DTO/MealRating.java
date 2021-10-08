package maciej.grochowski.favorite_dishes.DTO;

public enum MealRating {

    LIKED("liked"),
    DISLIKED("disliked");

    private final String displayValue;

    MealRating(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
