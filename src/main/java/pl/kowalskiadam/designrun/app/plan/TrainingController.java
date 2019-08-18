package pl.kowalskiadam.designrun.app.plan;

import org.hibernate.Hibernate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.kowalskiadam.designrun.app.method.Method;
import pl.kowalskiadam.designrun.app.method.TrainingType;
import pl.kowalskiadam.designrun.app.method.TrainingTypeRepository;
import pl.kowalskiadam.designrun.app.user.Coach;
import pl.kowalskiadam.designrun.app.user.CoachRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/training/{id}")
public class TrainingController {

    private final TrainingRepository trainingRepository;
    private final CoachRepository coachRepository;
    private final PlanRepository planRepository;
    private final WeekRepository weekRepository;
    private final DayRepository dayRepository;
    private final TrainingTypeRepository trainingTypeRepository;

    public TrainingController(TrainingRepository trainingRepository, CoachRepository coachRepository, PlanRepository planRepository, WeekRepository weekRepository, DayRepository dayRepository, TrainingTypeRepository trainingTypeRepository) {
        this.trainingRepository = trainingRepository;
        this.coachRepository = coachRepository;
        this.planRepository = planRepository;
        this.weekRepository = weekRepository;
        this.dayRepository = dayRepository;
        this.trainingTypeRepository = trainingTypeRepository;
    }

    @GetMapping("/details")
    @Transactional
    public String details(@PathVariable Long id, Model model){
        Training training = trainingRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Coach coach = checkCoachSecurity();
        Plan plan = planRepository.findById(training.getDay().getWeek().getPlan().getId()).orElseThrow(IllegalArgumentException::new);
        if (coach == null || !plan.getCoach().getId().equals(coach.getId())){
            return "redirect:/login";
        } else {
            List<TrainingType> trainingTypes = trainingTypeRepository.getByMethodId(plan.getMethod().getId());
            model.addAttribute("trainingTypes", trainingTypes);
            model.addAttribute("training", training);
            model.addAttribute("days", populeteDays(training.getDay()));
            return "planCoach/trainingDetails";
        }
    }

    @PostMapping("/details")
    public String createNewMethod(@ModelAttribute @Valid Training training, BindingResult bindingResult, @PathVariable Long id){
        if (bindingResult.hasErrors()){
            return "planCoach/trainingDetails";
        }
        Long methodId = training.getDay().getWeek().getPlan().getId();
        training.generateShortCut();
        trainingRepository.save(training);
        return "redirect:/plan/"+methodId+"/coach/allTrainingsByWeeks";
    }

    private List<Day> populeteDays(Day trainingDay){
        Plan plan = planRepository.findById(trainingDay.getWeek().getPlan().getId()).orElseThrow(IllegalArgumentException::new);
        List<Day> allDays = new ArrayList<>();
        List<Week> weeks = weekRepository.findByPlanId(plan.getId());
        for (Week week : weeks){
            Hibernate.initialize(week.getDays());
            for (Day day : week.getDays()){
                Hibernate.initialize(day.getTrainings());
                allDays.add(day);
            }
        }
        int trainingDayPosition = allDays.indexOf(trainingDay);

        int previousDays;
        if (trainingDayPosition<14){
            previousDays = 0;
        } else {
            previousDays = trainingDayPosition - 14;
        }

        int nextDays;
        if (trainingDayPosition + 14 > allDays.size()){
            nextDays = allDays.size();
        } else {
            nextDays = trainingDayPosition + 14;
        }

        List<Day> days  = allDays.subList(previousDays, nextDays);
        return days;
    }

    private Coach checkCoachSecurity() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String login = ((UserDetails) principal).getUsername();
            return coachRepository.findByLogin(login);
        } else return null;
    }
}
