package maciej.grochowski.favorite_dishes.service;

import lombok.AllArgsConstructor;
import maciej.grochowski.favorite_dishes.DTO.GourmetRegisterDTO;
import maciej.grochowski.favorite_dishes.DTO.MealRating;
import maciej.grochowski.favorite_dishes.entity.Gourmet;
import maciej.grochowski.favorite_dishes.entity.GourmetRating;
import maciej.grochowski.favorite_dishes.entity.Meal;
import maciej.grochowski.favorite_dishes.exception.UserAlreadyExistsException;
import maciej.grochowski.favorite_dishes.repository.GourmetRepository;
import maciej.grochowski.favorite_dishes.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Service
public class GourmetService {

    private final GourmetRepository gourmetRepository;
    private final MealRepository mealRepository;

    public void removeMealFromGourmetLikeList(int gourmetId, int mealId) {
        Gourmet gourmet = gourmetRepository.findGourmetByGourmetId(gourmetId);
        Meal meal = mealRepository.findMealById(mealId);
        List<GourmetRating> ratingList = gourmet.getRatingList();
        ratingList.forEach(rating -> {
            if (rating.getMeal().equals(meal)) {
                ratingList.remove(rating);
            }
        });
    }

    public void addMealToGourmetList(int gourmetId, int mealId, MealRating rating) {
        List<Meal> mealsOfGourmet = findMealsByGourmetId(gourmetId);
        Meal meal = mealRepository.findMealById(mealId);
        rating = rating.getDisplayValue().equals("liked") ? MealRating.LIKED : MealRating.DISLIKED;
        if (!mealsOfGourmet.contains(meal)) {
            Meal ratedMeal = Meal.builder()
                    .mealName(meal.getMealName())
                    .mealTaste(meal.getMealTaste())
                    .mealCountry(meal.getMealCountry())
                    .mealRating(rating)
                    .build();
            mealsOfGourmet.add(ratedMeal);
        }
    }

    public List<Meal> findMealsByGourmetId(int id) {
        Gourmet gourmetById = gourmetRepository.findGourmetByGourmetId(id);
        List<GourmetRating> ratingList = gourmetById.getRatingList();
        return convertRatingsIntoMealList(ratingList);
    }

    public List<Meal> convertRatingsIntoMealList(List<GourmetRating> gourmetRatingList) {
        List<Meal> mealList = new ArrayList<>();
        gourmetRatingList.forEach(rating -> mealList.add(rating.getMeal()));
        return mealList;
    }

    public void registerGourmet(GourmetRegisterDTO DTO) {
        String providedEmail = DTO.getEmail();
        if (emailExists(providedEmail)) {
            throw new UserAlreadyExistsException(String.format("Email %s is already registered.", providedEmail));
        }
        Gourmet gourmet = createGourmetFromDTOs(DTO);
        gourmetRepository.save(gourmet);
    }

    private Gourmet createGourmetFromDTOs(GourmetRegisterDTO gourmetDTO) {
        return Gourmet.builder()
                .name(gourmetDTO.getName())
                .birthDate(gourmetDTO.getBirthDate())
                .email(gourmetDTO.getEmail())
                .password(gourmetDTO.getPassword())
                .ratingList(new ArrayList<>())
                .roles(Arrays.asList("ROLE_USER"))
                .build();
    }

    private boolean emailExists(String email) {
        return gourmetRepository.findGourmetByEmail(email).isPresent();
    }

    public List<Gourmet> findAllGourmetsSortByName() {
        List<Gourmet> gourmets = gourmetRepository.findAll();
        Comparator<Gourmet> compareByName = Comparator.comparing(Gourmet::getName);
        gourmets.sort(compareByName);
        return gourmets;
    }

    public List<GourmetRating> getRatingsByGourmetId(int gourmetId) {
        Gourmet gourmet = gourmetRepository.findGourmetByGourmetId(gourmetId);
        return gourmet.getRatingList();
    }
}