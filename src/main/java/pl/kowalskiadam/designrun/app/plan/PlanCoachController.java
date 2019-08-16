package pl.kowalskiadam.designrun.app.plan;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kowalskiadam.designrun.app.method.Method;
import pl.kowalskiadam.designrun.app.method.TrainingType;
import pl.kowalskiadam.designrun.app.user.Coach;
import pl.kowalskiadam.designrun.app.user.CoachRepository;

import java.util.List;

@Controller
@RequestMapping("/plan/{id}/coach")
public class PlanCoachController {

    private final TrainingRepository trainingRepository;
    private final CoachRepository coachRepository;
    private final PlanRepository planRepository;

    public PlanCoachController(TrainingRepository trainingRepository, CoachRepository coachRepository, PlanRepository planRepository) {
        this.trainingRepository = trainingRepository;
        this.coachRepository = coachRepository;
        this.planRepository = planRepository;
    }

    @GetMapping("/allTrainings")
    public String showAllTrainings(@PathVariable Long id, Model model){

        Coach coach = checkCoachSecurity();
        Plan plan = planRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if (coach == null || !plan.getCoach().getId().equals(coach.getId())){
            return "redirect:/login";
        } else {
            List<Training> trainings = trainingRepository.getByPlanId(id);
            model.addAttribute("trainings", trainings);
            return "planCoach/allTrainings";
        }
    }

    private Coach checkCoachSecurity(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            String login = ((UserDetails) principal).getUsername();
            return coachRepository.findByLogin(login);
        }
        else return null;
    }

}
