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
import pl.kowalskiadam.designrun.app.user.Athlete;
import pl.kowalskiadam.designrun.app.user.AthleteRepository;
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
    private final AthleteRepository athleteRepository;


    public TrainingController(TrainingRepository trainingRepository, CoachRepository coachRepository, PlanRepository planRepository, WeekRepository weekRepository, DayRepository dayRepository, TrainingTypeRepository trainingTypeRepository, AthleteRepository athleteRepository) {
        this.trainingRepository = trainingRepository;
        this.coachRepository = coachRepository;
        this.planRepository = planRepository;
        this.weekRepository = weekRepository;
        this.dayRepository = dayRepository;
        this.trainingTypeRepository = trainingTypeRepository;
        this.athleteRepository = athleteRepository;
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


    @GetMapping("/giveFeedback")
    @Transactional
    public String giveFeedback(@PathVariable Long id, Model model){
        Training training = trainingRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Coach coach = checkCoachSecurity();
        Plan plan = planRepository.findById(training.getDay().getWeek().getPlan().getId()).orElseThrow(IllegalArgumentException::new);
        if (coach == null || !plan.getCoach().getId().equals(coach.getId())){
            return "redirect:/login";
        } else {
            model.addAttribute("training", training);
            model.addAttribute("plan", plan);
            return "planCoach/giveFeedback";
        }
    }

    @PostMapping("/giveFeedback")
    @Transactional
    public String saveFeedback(@PathVariable Long id, @ModelAttribute Training training){
        Training trainingToChange = trainingRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Coach coach = checkCoachSecurity();
        Plan plan = planRepository.findById(trainingToChange.getDay().getWeek().getPlan().getId()).orElseThrow(IllegalArgumentException::new);
        if (coach == null || !plan.getCoach().getId().equals(coach.getId())){
            return "redirect:/login";
        } else {
            trainingToChange.setCoachFeedback(training.getCoachFeedback());
            trainingRepository.save(trainingToChange);
            return "redirect: /training/{id}/giveFeedback";
        }
    }


    @PostMapping("/details")
    public String createNewMethod(@ModelAttribute @Valid Training training, BindingResult bindingResult, @PathVariable Long id){
        if (bindingResult.hasErrors()){
            return "planCoach/trainingDetails";
        }
        Long trainingId = training.getDay().getWeek().getPlan().getId();
        training.generateShortCut();
        trainingRepository.save(training);
        return "redirect:/plan/"+trainingId+"/coach/allTrainingsByWeeks";
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




    @GetMapping("/delete")
    public String delete(@PathVariable Long id){
        Training training = trainingRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Coach coach = checkCoachSecurity();
        Plan plan = planRepository.findById(training.getDay().getWeek().getPlan().getId()).orElseThrow(IllegalArgumentException::new);
        if (coach == null || !plan.getCoach().getId().equals(coach.getId())){
            return "redirect:/login";
        } else {
            trainingRepository.delete(training);
            return "redirect:/plan/"+ plan.getId()+"/coach/allTrainingsByWeeks";
        }
    }

    @GetMapping("/athleteView")
    @Transactional
    public String athleteView(@PathVariable Long id, Model model){
        Training training = trainingRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Athlete athlete = checkAthleteSecurity();
        Plan plan = planRepository.findById(training.getDay().getWeek().getPlan().getId()).orElseThrow(IllegalArgumentException::new);
        if (athlete == null || !plan.getAthlete().getId().equals(athlete.getId())){
            return "redirect:/login";
        } else {
            model.addAttribute("training", training);
            model.addAttribute("plan", plan);
            return "planAthlete/trainingDetails";
        }
    }

    private Coach checkCoachSecurity() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String login = ((UserDetails) principal).getUsername();
            return coachRepository.findByLogin(login);
        } else return null;
    }

    private Athlete checkAthleteSecurity(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            String login = ((UserDetails) principal).getUsername();
            return athleteRepository.findByLogin(login);
        }
        else return null;
    }
}
