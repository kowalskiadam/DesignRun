package pl.kowalskiadam.designrun.app.method;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kowalskiadam.designrun.app.user.Coach;
import pl.kowalskiadam.designrun.app.user.CoachRepository;

@Controller
@RequestMapping("/traningType/{id}")
public class TrainingTypeController {

    private final MethodRepository methodRepository;
    private final TrainingTypeRepository trainingTypeRepository;
    private final CoachRepository coachRepository;

    public TrainingTypeController(MethodRepository methodRepository, TrainingTypeRepository trainingTypeRepository, CoachRepository coachRepository) {
        this.methodRepository = methodRepository;
        this.trainingTypeRepository = trainingTypeRepository;
        this.coachRepository = coachRepository;
    }

    @GetMapping(value = "/details")
    public String showUpdateForm(Model model, @PathVariable Long id) {
        Coach coach = checkCoachSecurity();
        TrainingType trainingType = trainingTypeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if (coach == null || !trainingType.getMethod().getOwner().getId().equals(coach.getId())){
            return "redirect:/login";
        } else {
            model.addAttribute("trainingType", trainingType);
            return "method/traningTypeDetails";
        }
    }

    @PostMapping(value = "/details")
    public String executeUpdate(@ModelAttribute TrainingType trainingType, @PathVariable Long id) {
        TrainingType oldTraningType = trainingTypeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        oldTraningType.setName(trainingType.getName());
        oldTraningType.setShortCut(trainingType.getShortCut());
        oldTraningType.setDescription(trainingType.getDescription());
        oldTraningType.setMaxDistance(trainingType.getMaxDistance());
        oldTraningType.setMinDistance(trainingType.getMinDistance());
        trainingTypeRepository.save(oldTraningType);
        Long methodId = oldTraningType.getMethod().getId();
        return "redirect:/method/" + methodId + "/details";
    }

    private Coach checkCoachSecurity() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String login = ((UserDetails) principal).getUsername();
            return coachRepository.findByLogin(login);
        } else return null;
    }
}