package maciej.grochowski.favorite_dishes.meal;

import lombok.AllArgsConstructor;
import maciej.grochowski.favorite_dishes.gourmet.GourmetService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/meal")
@Controller
public class MealController {

    private final GourmetService gourmetService;
    private final MealService mealService;

    @GetMapping
    public String viewMeals(Model model) {
        List<Meal> allMeals = mealService.findAllMealsSortedByName();
        model.addAttribute("mealsList", allMeals);
        return "meal_page";
    }

    @GetMapping
    public String addMealToGourmetList(@PathVariable int gourmetId, @PathVariable int mealId, @Param("rating") MealRating rating) {
        gourmetService.addMealToGourmetList(gourmetId, mealId, rating);
    }

    @GetMapping("/add")
    public String addMeal(Model model) {
        MealDTO DTO = new MealDTO();
        model.addAttribute("DTO", DTO);
        return "add_meal";
    }

    @PostMapping("/add")
    public String addMeal(@ModelAttribute("DTO") @Valid MealDTO DTO,
                          BindingResult mealResult, Model model) {
        if (mealResult.hasErrors()) {
            return "add_meal";
        }

        try {
            mealService.addMealFromDTO(DTO);
        } catch (MealAlreadyExistsException e) {
            model.addAttribute("mealExistsException", "Cannot add this Meal, as it already exists.");
            return "add_meal";
        }
        return "homepage";
    }
}
