package pl.kowalskiadam.designrun.app.secure;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.kowalskiadam.designrun.app.user.Athlete;
import pl.kowalskiadam.designrun.app.user.Coach;
import pl.kowalskiadam.designrun.app.user.User;

@Controller
@SessionAttributes("loggedInUser")
public class LoginController {

    //ok
    @RequestMapping("")
    public String loginPage() {
        return "main/login";
    }


    @RequestMapping("/login-success")
    public String loginSuccess(Model model) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());
        User loggedInUser = ((UserPrincipal) authentication.getPrincipal()).getUserDetails();
        model.addAttribute("loggedInUser", loggedInUser);
        System.out.println(loggedInUser.getClass().toString());
        if (loggedInUser instanceof Coach){
            return "coach/dashboard";
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

}
