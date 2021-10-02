package maciej.grochowski.favorite_dishes.meal;

enum MealTaste {

    SWEET("Sweet"),
    SOUR("Sour"),
    BITTER("Bitter"),
    SALTY("Salty"),
    UMAMI("Umami");

    private final String displayValue;

    MealTaste(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}

