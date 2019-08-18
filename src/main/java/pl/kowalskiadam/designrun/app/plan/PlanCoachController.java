package pl.kowalskiadam.designrun.app.plan;

import org.hibernate.Hibernate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.kowalskiadam.designrun.app.method.TrainingType;
import pl.kowalskiadam.designrun.app.method.TrainingTypeRepository;
import pl.kowalskiadam.designrun.app.user.Coach;
import pl.kowalskiadam.designrun.app.user.CoachRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/plan/{id}/coach")
public class PlanCoachController {

    private final TrainingRepository trainingRepository;
    private final CoachRepository coachRepository;
    private final PlanRepository planRepository;
    private final WeekRepository weekRepository;
    private final DayRepository dayRepository;
    private final TrainingTypeRepository trainingTypeRepository;

    public PlanCoachController(TrainingRepository trainingRepository, CoachRepository coachRepository, PlanRepository planRepository, WeekRepository weekRepository, DayRepository dayRepository, TrainingTypeRepository trainingTypeRepository) {
        this.trainingRepository = trainingRepository;
        this.coachRepository = coachRepository;
        this.planRepository = planRepository;
        this.weekRepository = weekRepository;
        this.dayRepository = dayRepository;
        this.trainingTypeRepository = trainingTypeRepository;
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

    @GetMapping("/addTraining/{date}")
    public String add(@PathVariable Long id, @PathVariable String date, Model model){
        Training training = trainingRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Coach coach = checkCoachSecurity();
        Plan plan = planRepository.findById(training.getDay().getWeek().getPlan().getId()).orElseThrow(IllegalArgumentException::new);
        if (coach == null || !plan.getCoach().getId().equals(coach.getId())){
            return "redirect:/login";
        } else {
            model.addAttribute("training", new Training());
            List<TrainingType> trainingTypes = trainingTypeRepository.getByMethodId(plan.getMethod().getId());
            model.addAttribute("trainingTypes", trainingTypes);
            return "planCoach/addTraining";
        }
    }

    @Transactional
    @PostMapping("/addTraining/{date}")
    public String createNewMethod(@ModelAttribute @Valid Training training, BindingResult bindingResult, @PathVariable Long id, @PathVariable String date){
        if (bindingResult.hasErrors()){
            return "planCoach/trainingDetails";
        }
        training.generateShortCut();
        LocalDate localDate = LocalDate.parse(date);
        Plan plan = planRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        training.setDay(findDay(localDate, plan));
        trainingRepository.save(training);
        return "redirect:/plan/"+id+"/coach/allTrainingsByWeeks";
    }

    public Day findDay (LocalDate localDate, Plan plan){
        List<Week> weeks = weekRepository.findByPlanId(plan.getId());
        for (Week week : weeks){
            Hibernate.initialize(week.getDays());
            for (Day day : week.getDays()){
                Hibernate.initialize(day.getTrainings());
                if (day.getDate().equals(localDate)){
                    return day;
                }
            }
        }
        return null;
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
