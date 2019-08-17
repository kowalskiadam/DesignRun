package pl.kowalskiadam.designrun.app.method;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.kowalskiadam.designrun.app.user.Coach;
import pl.kowalskiadam.designrun.app.user.CoachRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/method/{id}")
public class MethodController {

    private final MethodRepository methodRepository;
    private final TrainingTypeRepository trainingTypeRepository;
    private final CoachRepository coachRepository;

    public MethodController(MethodRepository methodRepository, TrainingTypeRepository trainingTypeRepository, CoachRepository coachRepository) {
        this.methodRepository = methodRepository;
        this.trainingTypeRepository = trainingTypeRepository;
        this.coachRepository = coachRepository;
    }


    //to test
    @GetMapping(value = "/hide")
    public String hide(@PathVariable Long id) {
        Coach coach = checkCoachSecurity();
        Method method = methodRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if (coach == null || !method.getOwner().getId().equals(coach.getId())){
            return "redirect:/login";
        } else {
            method.setHide(true);
            methodRepository.save(method);
            return "redirect:/coach/methodsList";
        }
    }

    //to test
    @GetMapping(value = "/unhide")
    public String unhide(@PathVariable Long id) {
        Coach coach = checkCoachSecurity();
        Method method = methodRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if (coach == null || !method.getOwner().getId().equals(coach.getId())){
            return "redirect:/login";
        } else {
            method.setHide(false);
            methodRepository.save(method);
            return "redirect:/coach/methodsList";
        }
    }

    //to test
    @GetMapping(value = "/details")
    public String showUpdateForm(Model model, @PathVariable Long id) {
        Coach coach = checkCoachSecurity();
        Method method = methodRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if (coach == null || !method.getOwner().getId().equals(coach.getId())){
            return "redirect:/login";
        } else {
            model.addAttribute("method", method);
            List<TrainingType> trainingTypes = trainingTypeRepository.getByMethodId(id);
            model.addAttribute("trainingTypes", trainingTypes);
            System.out.println(trainingTypes.toString());
            return "method/details";
        }
    }

    //to test
    @PostMapping(value="/details")
    public String executeUpdate(@ModelAttribute @Valid Method method, BindingResult bindingResult, @PathVariable Long id){
        if (bindingResult.hasErrors()){
            return "method/details";
        }
        Method oldMethod = methodRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        oldMethod.setName(method.getName());
        oldMethod.setShortDescription(method.getShortDescription());
        methodRepository.save(oldMethod);
        Long ownerId = oldMethod.getOwner().getId();
        return "redirect:/coach/methodsList";
    }

    //to test
    @GetMapping(value = "/details/addTrainingType")
    public String showAddTraningType(Model model, @PathVariable Long id) {

        Coach coach = checkCoachSecurity();
        Method method = methodRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if (coach == null || !method.getOwner().getId().equals(coach.getId())){
            return "redirect:/login";
        } else {
            model.addAttribute("trainingType", new TrainingType());
            return "method/addTrainingType";
        }
    }

    //to test
    @PostMapping(value = "/details/addTrainingType")
    public String executeAddTraningType(@ModelAttribute @Valid TrainingType trainingType, BindingResult bindingResult, @PathVariable Long id){
        if (bindingResult.hasErrors()){
            return "method/addTrainingType";
        }
        Method method = methodRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        trainingType.setMethod(method);
        trainingType.setHide(false);
        trainingTypeRepository.save(trainingType);
        return "redirect:/method/"+id+"/details";

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
