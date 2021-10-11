package maciej.grochowski.favorite_dishes.controller;

import lombok.AllArgsConstructor;
import maciej.grochowski.favorite_dishes.DTO.MealDTO;
import maciej.grochowski.favorite_dishes.DTO.MealRating;
import maciej.grochowski.favorite_dishes.entity.GourmetRating;
import maciej.grochowski.favorite_dishes.entity.Meal;
import maciej.grochowski.favorite_dishes.exception.MealAlreadyExistsException;
import maciej.grochowski.favorite_dishes.service.GourmetService;
import maciej.grochowski.favorite_dishes.service.MealService;
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
        List<GourmetRating> gourmetRatings = gourmetService.getRatingsByGourmetId(gourmetId);
        model.addAttribute("gourmetRatings", gourmetRatings);
        return "meal_page";
    }

    @GetMapping("/likeMeal/{mealId}")
    public String addMealToGourmetList(@PathVariable int mealId,
                                       @Param("rating") String rating,
                                       HttpServletRequest request) {
        gourmetService.addMealToGourmetList(mealId, rating);
        return getPreviousPageByRequest(request).orElse("/");
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
