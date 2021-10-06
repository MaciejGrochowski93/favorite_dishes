package maciej.grochowski.favorite_dishes.meal;

import lombok.AllArgsConstructor;
import maciej.grochowski.favorite_dishes.gourmet.GourmetService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/myMeals/{gourmetId}")
    public String viewGourmetsMeals(@PathVariable int gourmetId, Model model) {
        List<Meal> mealsOfGourmet = mealService.getMealsOfGourmet(gourmetId);
        model.addAttribute("mealsList", mealsOfGourmet);
        return "meal_page";
    }

    @GetMapping("/myMeals/{gourmetId}/addMeal/{mealId}")
    public String addMealToGourmetList(@PathVariable int gourmetId,
                                       @PathVariable int mealId,
                                       @Param("rating") MealRating rating) {
        gourmetService.addMealToGourmetList(gourmetId, mealId, rating);
        return null;
    }

    @GetMapping("/createMeal")
    public String createMeal(Model model) {
        MealDTO DTO = new MealDTO();
        model.addAttribute("DTO", DTO);
        return "add_meal";
    }

    @PostMapping("/createMeal")
    public String createMeal(@ModelAttribute("DTO") @Valid MealDTO DTO,
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

    private Optional<String> getPreviousPageByRequest(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }
}
