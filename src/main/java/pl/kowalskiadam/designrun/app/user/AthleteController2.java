package pl.kowalskiadam.designrun.app.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kowalskiadam.designrun.app.secure.UserPrincipal;

import java.util.List;

@Controller
@RequestMapping("/athlete/")
public class AthleteController2 {

    private final CoachRepository coachRepository;
    private final AthleteRepository athleteRepository;

    public AthleteController2(CoachRepository coachRepository, AthleteRepository athleteRepository){
        this.coachRepository = coachRepository;
        this.athleteRepository = athleteRepository;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            String login = ((UserDetails) principal).getUsername();
            Athlete athlete = athleteRepository.findByLogin(login);
            model.addAttribute("athlete", athlete);
            return "athlete/dashboard";
        }
        else return ("main/login");

    }

}
