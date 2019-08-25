package pl.kowalskiadam.designrun.app.plan;

import org.hibernate.Hibernate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kowalskiadam.designrun.app.method.TrainingTypeRepository;
import pl.kowalskiadam.designrun.app.user.Athlete;
import pl.kowalskiadam.designrun.app.user.AthleteRepository;
import pl.kowalskiadam.designrun.app.user.Coach;
import pl.kowalskiadam.designrun.app.user.CoachRepository;

import java.util.List;

@Controller
@RequestMapping("/plan/{id}/athlete")
public class PlanAthleteController {

    private final TrainingRepository trainingRepository;
    private final CoachRepository coachRepository;
    private final PlanRepository planRepository;
    private final WeekRepository weekRepository;
    private final DayRepository dayRepository;
    private final TrainingTypeRepository trainingTypeRepository;
    private final AthleteRepository athleteRepository;

    public PlanAthleteController(TrainingRepository trainingRepository, CoachRepository coachRepository, PlanRepository planRepository, WeekRepository weekRepository, DayRepository dayRepository, TrainingTypeRepository trainingTypeRepository, AthleteRepository athleteRepository) {
        this.trainingRepository = trainingRepository;
        this.coachRepository = coachRepository;
        this.planRepository = planRepository;
        this.weekRepository = weekRepository;
        this.dayRepository = dayRepository;
        this.trainingTypeRepository = trainingTypeRepository;
        this.athleteRepository = athleteRepository;
    }

    @Transactional
    @GetMapping("/allTrainingsByWeeks")
    public String showAllTrainings(@PathVariable Long id, Model model){

        Athlete athlete = checkAthleteSecurity();
        Plan plan = planRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if (athlete == null || !plan.getAthlete().getId().equals(athlete.getId())){
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
            return "planAthlete/allTrainingsByWeeks";
        }
    }

    @GetMapping("/training/{trainingId}/editComment")
    public String editComment(@PathVariable Long id, @PathVariable Long trainingId, Model model){

        Athlete athlete = checkAthleteSecurity();
        Plan plan = planRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Training training = trainingRepository.findById(trainingId).orElseThrow(IllegalArgumentException::new);
        if (athlete == null || !plan.getAthlete().getId().equals(athlete.getId())){
            return "redirect:/login";
        } else {
            model.addAttribute("training", training);
            return "athlete/editComment";
        }
    }

    @PostMapping("/training/{trainingId}/editComment")
    public String updateCommit(@PathVariable Long id, @PathVariable Long trainingId, @ModelAttribute Training training){

        Athlete athlete = checkAthleteSecurity();
        Plan plan = planRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if (athlete == null || !plan.getAthlete().getId().equals(athlete.getId())){
            return "redirect:/login";
        } else {
            Training updatedTraining = trainingRepository.findById(trainingId).orElseThrow(IllegalArgumentException::new);
            updatedTraining.setAthleteComment(training.getAthleteComment());
            trainingRepository.save(updatedTraining);
            return "redirect: /training/{trainingId}/athleteView";
        }
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
