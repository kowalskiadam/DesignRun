package pl.kowalskiadam.designrun.app.plan;

import org.hibernate.Hibernate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kowalskiadam.designrun.app.user.Coach;
import pl.kowalskiadam.designrun.app.user.CoachRepository;

import java.util.List;

@Controller
@RequestMapping("/plan/{id}/coach")
public class PlanCoachController {

    private final TrainingRepository trainingRepository;
    private final CoachRepository coachRepository;
    private final PlanRepository planRepository;
    private final WeekRepository weekRepository;
    private final DayRepository dayRepository;

    public PlanCoachController(TrainingRepository trainingRepository, CoachRepository coachRepository, PlanRepository planRepository, WeekRepository weekRepository, DayRepository dayRepository) {
        this.trainingRepository = trainingRepository;
        this.coachRepository = coachRepository;
        this.planRepository = planRepository;
        this.weekRepository = weekRepository;
        this.dayRepository = dayRepository;
    }

    @GetMapping("/details")
    public String details(@PathVariable Long id, Model model){

        Coach coach = checkCoachSecurity();
        Plan plan = planRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if (coach == null || !plan.getCoach().getId().equals(coach.getId())){
            return "redirect:/login";
        } else {
            model.addAttribute("plan", plan);
            return "planCoach/details";
        }
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

    @GetMapping("/allTrainingsByWeeks")
    @Transactional
    public String showAllTrainingsByWeeks(@PathVariable Long id, Model model){

        Coach coach = checkCoachSecurity();
        Plan plan = planRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        if (coach == null || !plan.getCoach().getId().equals(coach.getId())){
            return "redirect:/login";
        } else {
            List<Week> weeks = weekRepository.findByPlanId(id);
            for (Week week : weeks){
                Hibernate.initialize(week.getDays());
                for (Day day : week.getDays()){
                    Hibernate.initialize(day.getTrainings());
                }
            }
            model.addAttribute("weeks", weeks);
            return "planCoach/allTrainingsByWeeks";
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
