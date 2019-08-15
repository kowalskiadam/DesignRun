package pl.kowalskiadam.designrun.app.mainpage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.kowalskiadam.designrun.app.user.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class MainPageController {

    private final CoachRepository coachRepository;
    private final AthleteRepository athleteRepository;

    public MainPageController(CoachRepository coachRepository, AthleteRepository athleteRepository) {
        this.coachRepository = coachRepository;
        this.athleteRepository = athleteRepository;
    }

/*    @GetMapping()
    public String showStartPage(){
        return "main/start";
    }*/

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showHomePage(HttpSession session) {
        return "main/main";
    }

    @GetMapping("/loginCoach")
    public String showCoachLoginPage(){
        return "main/loginCoach";
    }

    @PostMapping("/loginCoach")
    public String successfulCoachLogin(@RequestParam Long id){
        try {
            coachRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            return ("redirect:coach/" + id + "/dashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "main/loginCoach";
    }

    @GetMapping("/addCoach")
    public String showCoachLoginPage(Model model){
        model.addAttribute("coach", new Coach());
        return "main/addCoach";
    }

    @PostMapping("/addCoach")
    public String coachCreated(@ModelAttribute @Valid Coach coach, BindingResult result){
        if (result.hasErrors()){
            return "main/addCoach";
        }
        coachRepository.save(coach);
        return ("redirect:coach/" + coach.getId() + "/dashboard");
    }

    @GetMapping("/loginAthlete")
    public String showAthleteLoginPage(){
        return "main/loginCoach";
    }

    @PostMapping("/loginAthlete")
    public String successfulAthleteLogin(@RequestParam Long id){
        try {
            athleteRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            return ("redirect:athlete/" + id + "/dashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "main/loginAthlete";
    }

    @GetMapping("/addAthlete")
    public String showAthleteoginPage(Model model){
        model.addAttribute("athlete", new Athlete());
        return "main/addAthlete";
    }

    @PostMapping("/addAthlete")
    public String athleteCreated(@ModelAttribute @Valid Athlete athlete, BindingResult result){
        if (result.hasErrors()){
            return "main/addAthlete";
        }
        athleteRepository.save(athlete);
        return ("redirect:athlete/" + athlete.getId() + "/dashboard");
    }



}
