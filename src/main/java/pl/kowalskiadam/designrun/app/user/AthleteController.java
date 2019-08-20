package pl.kowalskiadam.designrun.app.user;

import org.hibernate.Hibernate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kowalskiadam.designrun.app.plan.Day;
import pl.kowalskiadam.designrun.app.plan.Plan;
import pl.kowalskiadam.designrun.app.plan.PlanRepository;
import pl.kowalskiadam.designrun.app.plan.Week;

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

    private Athlete checkAthleteSecurity(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            String login = ((UserDetails) principal).getUsername();
            return athleteRepository.findByLogin(login);
        }
        else return null;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model){

        Athlete athlete = checkAthleteSecurity();
        if (athlete == null){
            return "redirect:/login";
        } else {
            model.addAttribute("athlete", athlete);
            return "athlete/dashboard";
        }
    }

    @GetMapping("/planList")
    public String showPlanList(Model model){

        Athlete athlete = checkAthleteSecurity();
        if (athlete == null){
            return "redirect:/login";
        } else {
            List<Plan> plans = planRepository.getByAthleteId(athlete.getId());
            model.addAttribute("plans", plans);
            return "athlete/planList";
        }
    }

    @GetMapping("/advanced")
    public String showAdvanced(){
        Athlete athlete = checkAthleteSecurity();
        if (athlete == null){
            return "redirect:/login";
        } else {
            return "athlete/advanced";

        }
    }

    @GetMapping("/coachList")
    public String showCoachList(Model model){

        Athlete athlete = checkAthleteSecurity();
        if (athlete == null){
            return "redirect:/login";
        } else {
            Long id = athlete.getId();
            List<Coach> coaches = coachRepository.getCoachesByAthlete(id);
            List<Coach> potentialCoaches = coachRepository.getPotentialCoachesByAthlete(id);
            model.addAttribute("athleteId", id);
            model.addAttribute("coaches", coaches);
            model.addAttribute("potentialCoaches", potentialCoaches);
            return "athlete/coachList";
        }
    }

    @GetMapping("/acceptInvitation/{coachId}")
    public String confirmInvitation(@PathVariable Long coachId){

        Athlete athlete = checkAthleteSecurity();
        if (athlete == null){
            return "redirect:/login";
        } else {
            Long id = athlete.getId();
            List<Athlete> potentialAthlets = athleteRepository.getPotentialAthleteByCoaches(coachId);
            removeAthlete(potentialAthlets, id);
            List<Athlete> athletes = athleteRepository.getAthleteByCoaches(coachId);
            athletes.add(athlete);
            Coach coach = coachRepository.findById(coachId).orElseThrow(IllegalArgumentException::new);
            coach.setPotentialAthletes(potentialAthlets);
            coach.setAthletes(athletes);
            coachRepository.save(coach);
            return "redirect:/athlete/coachList";
        }


/*        Athlete athlete = athleteRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        List<Coach> potentialCoaches = athlete.getPotentialCoaches();
        removeCoach(potentialCoaches, coachId);
        athlete.setPotentialCoaches(potentialCoaches);
        List<Coach> coaches = athlete.getCoaches();
        Coach coach = coachRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        coaches.add(coach);
        athlete.setCoaches(coaches);
        athleteRepository.save(athlete);
*/
    }

    @GetMapping("/rejectInvitation/{coachId}")
    public String rejectInvitation(@PathVariable Long coachId){

        Athlete athlete = checkAthleteSecurity();
        if (athlete == null){
            return "redirect:/login";
        } else {
            Long id = athlete.getId();
            Coach coach = coachRepository.findById(coachId).orElseThrow(IllegalArgumentException::new);
            List<Athlete> potentialAthlets = athleteRepository.getPotentialAthleteByCoaches(coachId);
            removeAthlete(potentialAthlets, id);
            coach.setPotentialAthletes(potentialAthlets);
            coachRepository.save(coach);
            return "redirect:/athlete/coachList";
        }



/*        Athlete athlete = athleteRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        List<Coach> potentialCoaches = athlete.getPotentialCoaches();
        removeCoach(potentialCoaches, coachId);
        athlete.setPotentialCoaches(potentialCoaches);
        athleteRepository.save(athlete);
*/

    }


    @GetMapping("/removeCoach/{coachId}")
    public String removeCoach(@PathVariable Long coachId){


        Athlete athlete = checkAthleteSecurity();
        if (athlete == null){
            return "redirect:/login";
        } else {
            Long id = athlete.getId();
            Coach coach = coachRepository.findById(coachId).orElseThrow(IllegalArgumentException::new);

            List<Athlete> athletes = athleteRepository.getAthleteByCoaches(coachId);

            removeAthlete(athletes, id);

            coach.setAthletes(athletes);

            coachRepository.save(coach);
            return "redirect:/athlete/coachList";
        }



/*        List<Coach> coaches = new ArrayList<>();
        coaches = coachRepository.getCoachesByAthlete(id);
        removeCoach(coaches, coachId);
        Athlete athlete = athleteRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        athlete.setCoaches(coaches);
        athleteRepository.save(athlete);*/

    }

    private void removeAthlete(List<Athlete> athletes, Long athleteId){
        int position = -1;
        for (int i = 0; i < athletes.size(); i++){
            if (athletes.get(i).getId().equals(athleteId)){
                position = i;
            }
        }
        athletes.remove(position);
        // Coach coach = coachRepository.findById(coachId).orElseThrow(IllegalArgumentException::new);

    }

}
