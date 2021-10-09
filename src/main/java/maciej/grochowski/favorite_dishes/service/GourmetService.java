package maciej.grochowski.favorite_dishes.service;

import lombok.AllArgsConstructor;
import maciej.grochowski.favorite_dishes.DTO.GourmetRegisterDTO;
import maciej.grochowski.favorite_dishes.DTO.MealRating;
import maciej.grochowski.favorite_dishes.entity.Gourmet;
import maciej.grochowski.favorite_dishes.entity.GourmetRating;
import maciej.grochowski.favorite_dishes.entity.Meal;
import maciej.grochowski.favorite_dishes.exception.UserAlreadyExistsException;
import maciej.grochowski.favorite_dishes.exception.UserDoesNotExist;
import maciej.grochowski.favorite_dishes.repository.GourmetRepository;
import maciej.grochowski.favorite_dishes.repository.MealRepository;
import maciej.grochowski.favorite_dishes.security.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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

    public void addMealToGourmetList(int mealId, String rating) {
        Gourmet gourmet = getPrincipal();
        List<Meal> mealsOfGourmet = findMealsByGourmetId(gourmet.getGourmetId());

        Meal meal = mealRepository.findMealById(mealId);
        MealRating gourmetsRateOfMeal;
        gourmetsRateOfMeal = rating.equals("liked") ? MealRating.LIKED : MealRating.DISLIKED;

        if (!mealsOfGourmet.contains(meal)) {
            Meal ratedMeal = Meal.builder()
                    .mealName(meal.getMealName())
                    .mealTaste(meal.getMealTaste())
                    .mealCountry(meal.getMealCountry())
                    .mealRating(gourmetsRateOfMeal)
                    .build();
            mealsOfGourmet.add(ratedMeal);
        }
    }

    public Gourmet getPrincipal() {
        String email = getPrincipalEmail();
        return gourmetRepository.findGourmetByEmail(email)
                .orElseThrow(() -> new UserDoesNotExist(String.format("Email %s not found", email)));
    }

    public String getPrincipalEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof MyUserDetails) {
            email = ((MyUserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        return email;
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
        String unsafePassword = gourmetDTO.getPassword();
        String encodedPassword = bCryptPasswordEncoder.encode(unsafePassword);

        return Gourmet.builder()
                .name(gourmetDTO.getName())
                .birthDate(gourmetDTO.getBirthDate())
                .email(gourmetDTO.getEmail())
                .password(encodedPassword)
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