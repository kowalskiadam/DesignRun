package pl.kowalskiadam.designrun.app.user;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kowalskiadam.designrun.app.method.Method;
import pl.kowalskiadam.designrun.app.method.MethodRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/coach/{id}")
public class CoachController {

    private final CoachRepository coachRepository;
    private final MethodRepository methodRepository;
    private final AthleteRepository athleteRepository;

    public CoachController(CoachRepository coachRepository, MethodRepository methodRepository, AthleteRepository athleteRepository){
        this.coachRepository = coachRepository;
        this.methodRepository = methodRepository;
        this.athleteRepository = athleteRepository;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, @PathVariable Long id){
        Coach coach = coachRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        model.addAttribute("coach", coach);
        return "coach/dashboard";
    }

    @GetMapping("/methodsList")
    public String showMethodList(Model model, @PathVariable Long id){
        List<Method> coachMethods = methodRepository.getByOwnerIdAvailable(id);
        model.addAttribute("coachMethods", coachMethods);
        return "coach/methodsList";
    }

    @GetMapping("/addMethod")
    public String addNewMethod(Model model){
        model.addAttribute("method", new Method());
        return "method/addMethod";
    }

    @PostMapping("/addMethod")
    public String createNewMethod(@ModelAttribute Method method, @PathVariable Long id){
        method.setOwner(coachRepository.findById(id).orElseThrow(IllegalArgumentException::new));
        method.setHide(false);
        methodRepository.save(method);
        return "redirect:methodsList";
    }

    @GetMapping("/advanced")
    public String showAdvanced(){
        return "coach/advanced";
    }

    @GetMapping("/hideMethodsList")
    public String showHideMethodList(Model model, @PathVariable Long id){
        List<Method> coachHideMethods = methodRepository.getByOwnerIdHide(id);
        model.addAttribute("coachHideMethods", coachHideMethods);
        return "coach/hideMethodsList";
    }

    @GetMapping("/athleteList")
    public String showAthletes(Model model, @PathVariable Long id){
        List<Athlete> athletes = athleteRepository.getAthleteByCoaches(id);
        List<Athlete> potentialAthletes = athleteRepository.getPotentialAthleteByCoaches(id);
        model.addAttribute("athletes", athletes);
        model.addAttribute("potentialAthletes", potentialAthletes);
        model.addAttribute("coachId", id);
        return "coach/athleteList";
    }

    @PostMapping("/athleteList")
    public String findAthete(@RequestParam  String loginToFind, @PathVariable Long id, Model model){

        boolean athleteFound = false;

        String message = "";

        try{
            Athlete athlete = athleteRepository.findByLogin(loginToFind);
            athleteFound = true;
            Long athleteId = athlete.getId();

            List<Athlete> athletes = athleteRepository.getAthleteByCoaches(id);
            List<Athlete> potentialAthletes = athleteRepository.getPotentialAthleteByCoaches(id);

            boolean athleteOnList = false;

            for (int i = 0; i < athletes.size(); i++){
                if (athletes.get(i).getId().equals(athleteId)){
                    athleteOnList = true;
                    message = "Athlet is on the list. You can't add he/she second time";
                }
            }

            boolean athletOnPotentiaList = false;

            if (!athleteOnList){
                for (int i = 0; i < potentialAthletes.size(); i++){
                    if (potentialAthletes.get(i).getId().equals(athleteId)){
                        athletOnPotentiaList = true;
                        message = "Athlet is on the potential list. Wait patiently for he / she response";
                    }
                }
            }

            if (!athleteOnList & !athletOnPotentiaList){
                Coach coach = coachRepository.findById(id).orElseThrow(IllegalArgumentException::new);
                potentialAthletes.add(athlete);
                coach.setPotentialAthletes(potentialAthletes);
                coachRepository.save(coach);
                message = "Request was sended. Wait patiently for he / she response";
            }


/*            System.out.println("on list " + athleteOnList);
            System.out.println("on potential list " + athletOnPotentiaList);*/


        }catch (Exception e){
            message = "Login is wrong or athlete blocks new invitations";
        }

        System.out.println("found " + athleteFound);

        model.addAttribute("message", message);


        return "coach/addAthleteResult";

    }

        @GetMapping("/remove/{athleteId}")
    public String removeAthlete(@PathVariable Long id, @PathVariable Long athleteId){

        List<Athlete> athletes = athleteRepository.getAthleteByCoaches(id);
        removeAthlete(athletes, athleteId, id);
        Coach coach = coachRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        coach.setAthletes(athletes);
        coachRepository.save(coach);
        return "redirect:/coach/"+id+"/athleteList";
    }

    @GetMapping("/cancelInvitation/{athleteId}")
    public String cancelInvitation(@PathVariable Long id, @PathVariable Long athleteId){

        List<Athlete> potentialAthletes = athleteRepository.getPotentialAthleteByCoaches(id);
        removeAthlete(potentialAthletes, athleteId, id);
        Coach coach = coachRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        coach.setPotentialAthletes(potentialAthletes);
        coachRepository.save(coach);
        return "redirect:/coach/"+id+"/athleteList";
    }

     void removeAthlete(List<Athlete> athletes, Long athleteId, Long coachId){
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
