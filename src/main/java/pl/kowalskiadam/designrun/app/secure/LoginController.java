package pl.kowalskiadam.designrun.app.secure;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.kowalskiadam.designrun.app.user.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("loggedInUser")
public class LoginController {

    private final CoachRepository coachRepository;
    private final UserRepository userRepository;

    public LoginController(CoachController coachController, CoachRepository coachRepository, UserRepository userRepository) {
        this.coachRepository = coachRepository;
        this.userRepository = userRepository;
    }


    //ok
    @RequestMapping("")
    public String loginPage1() {
        return "main/login";
    }

    @RequestMapping("/login")
    public String loginPage2() {
        return "main/login";
    }

    //ok
    @RequestMapping("/login-success")
    public String loginSuccess(Model model) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());
        User loggedInUser = ((UserPrincipal) authentication.getPrincipal()).getUserDetails();
        model.addAttribute("loggedInUser", loggedInUser);
        if (loggedInUser instanceof Coach){
            return "redirect: coach/dashboard";
        } else if (loggedInUser instanceof Athlete){
            return "redirect: athlete/dashboard";
        } else {
            return "main/login";
        }
    }

    private void validatePrinciple(Object principal) {
    }

    @RequestMapping("/logout-success")
    public String logoutPage() {
        return "logout";
    }

    @RequestMapping("/newCoach")
    public String showCreateNewCoach(Model model){
        model.addAttribute("coach", new Coach());
        String message = "";
        model.addAttribute("message", message);
        return "main/newCoach";
    }

    @PostMapping("/newCoach")
    public String createNewCoach(@ModelAttribute @Valid Coach coach, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
        return "main/newCoach";
    }
        List<User> users = userRepository.findAll();
        for (User user : users){
            if (user.getLogin().equals(coach.getLogin())){
                model.addAttribute("message", "Login exist. Create coach with new login");
                return "main/newCoach";
            }
        }
        coach.setPassword(BCrypt.hashpw(coach.getPassword(), BCrypt.gensalt()));
        coachRepository.save(coach);
        return "redirect: login";

}
}