package pl.kowalskiadam.designrun.app.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/athlete/{id}")
public class AthleteController {

    private final CoachRepository coachRepository;
    private final AthleteRepository athleteRepository;

    public AthleteController(CoachRepository coachRepository, AthleteRepository athleteRepository){
        this.coachRepository = coachRepository;
        this.athleteRepository = athleteRepository;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, @PathVariable Long id){
        Athlete athlete = athleteRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        model.addAttribute("athlete", athlete);
        return "athlete/dashboard";
    }

    @GetMapping("/advanced")
    public String showAdvanced(){
        return "athlete/advanced";
    }

    @GetMapping("/coachList")
    public String showCoachList(Model model, @PathVariable Long id){
        List<Coach> coaches = coachRepository.getCoachesByAthlete(id);
        List<Coach> potentialCoaches = coachRepository.getPotentialCoachesByAthlete(id);
        model.addAttribute("athleteId", id);
        model.addAttribute("coaches", coaches);
        model.addAttribute("potentialCoaches", potentialCoaches);
        return "athlete/coachList";
    }

    @GetMapping("/acceptInvitation/{coachId}")
    public String confirmInvitation(@PathVariable Long id, @PathVariable Long coachId){



        List<Athlete> potentialAthlets = athleteRepository.getPotentialAthleteByCoaches(coachId);
        removeAthlete(potentialAthlets, id);
        List<Athlete> athletes = athleteRepository.getAthleteByCoaches(coachId);
        Athlete athlete = athleteRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        athletes.add(athlete);
        Coach coach = coachRepository.findById(coachId).orElseThrow(IllegalArgumentException::new);
        coach.setPotentialAthletes(potentialAthlets);
        coach.setAthletes(athletes);
        coachRepository.save(coach);


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
        return "redirect:/athlete/"+id+"/coachList";
    }

    @GetMapping("/rejectInvitation/{coachId}")
    public String rejectInvitation(@PathVariable Long id, @PathVariable Long coachId){

        Coach coach = coachRepository.findById(coachId).orElseThrow(IllegalArgumentException::new);

        List<Athlete> potentialAthlets = athleteRepository.getPotentialAthleteByCoaches(coachId);

        removeAthlete(potentialAthlets, id);

        coach.setPotentialAthletes(potentialAthlets);

        coachRepository.save(coach);


/*        Athlete athlete = athleteRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        List<Coach> potentialCoaches = athlete.getPotentialCoaches();
        removeCoach(potentialCoaches, coachId);
        athlete.setPotentialCoaches(potentialCoaches);

        athleteRepository.save(athlete);
*/
        return "redirect:/athlete/"+id+"/coachList";
    }


    @GetMapping("/removeCoach/{coachId}")
    public String removeCoach(@PathVariable Long id, @PathVariable Long coachId){

        Coach coach = coachRepository.findById(coachId).orElseThrow(IllegalArgumentException::new);

        List<Athlete> athletes = athleteRepository.getAthleteByCoaches(coachId);

        removeAthlete(athletes, id);

        coach.setAthletes(athletes);

        coachRepository.save(coach);
/*        List<Coach> coaches = new ArrayList<>();
        coaches = coachRepository.getCoachesByAthlete(id);
        removeCoach(coaches, coachId);
        Athlete athlete = athleteRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        athlete.setCoaches(coaches);
        athleteRepository.save(athlete);*/

        return "redirect:/athlete/"+id+"/coachList";
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

/*    public void removeCoach(List<Coach> coaches, Long coachId){
        int position = -1;
        for (int i = 0; i < coaches.size(); i++){
            if (coaches.get(i).getId().equals(coachId)){
                position = i;
            }
        }
        coaches.remove(position);
    }*/



}
