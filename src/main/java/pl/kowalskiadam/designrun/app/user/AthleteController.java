package pl.kowalskiadam.designrun.app.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kowalskiadam.designrun.app.plan.Plan;
import pl.kowalskiadam.designrun.app.plan.PlanRepository;

import java.util.List;

@Controller
@RequestMapping("/athlete/")
public class AthleteController {

    private final CoachRepository coachRepository;
    private final AthleteRepository athleteRepository;
    private final PlanRepository planRepository;

    public AthleteController(CoachRepository coachRepository, AthleteRepository athleteRepository, PlanRepository planRepository){
        this.coachRepository = coachRepository;
        this.athleteRepository = athleteRepository;
        this.planRepository = planRepository;
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

    @GetMapping("/planList")
    public String showPlanList(Model model){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            String login = ((UserDetails) principal).getUsername();
            Athlete athlete = athleteRepository.findByLogin(login);
            List<Plan> plans = planRepository.getByAthleteId(athlete.getId());
            model.addAttribute("plans", plans);
            return "athlete/planList";
        }
        else return ("main/login");
    }

}
