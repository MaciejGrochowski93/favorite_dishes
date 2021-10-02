package maciej.grochowski.favorite_dishes.meal;

enum MealRating {

    LIKED("Liked"),
    DISLIKED("Disliked");

    private final String displayValue;

    MealRating(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
