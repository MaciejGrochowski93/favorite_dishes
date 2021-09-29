package maciej.grochowski.favorite_dishes.gourmet;

import lombok.AllArgsConstructor;
import maciej.grochowski.favorite_dishes.registration.GourmetRegisterDTO;
import maciej.grochowski.favorite_dishes.registration.UserAlreadyExistsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/gourmet")
class GourmetController {

    private final GourmetService gourmetService;

    @GetMapping("/registration")
    public String registerGourmetForm(WebRequest request, Model model) {
        gourmetService.addGourmetToModel(model);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerGourmet(@ModelAttribute("gourmet") @Valid GourmetRegisterDTO gourmetDTO,
                                  BindingResult gourmetResult, Model model) {
        if (gourmetResult.hasErrors()) {
            return "registration";
        }

        try {
            gourmetService.registerGourmet(gourmetDTO);
        } catch(UserAlreadyExistsException uae) {
            model.addAttribute("errorMessage", "This account already exists.");
        }
        String providedEmail = gourmetDTO.getDTOEmail();
        model.addAttribute("registerSuccess", String.format("Account %s registered successfully.", providedEmail));
        return "redirect:/gourmet";
    }
}
