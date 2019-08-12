package pl.kowalskiadam.designrun.app.plan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/plan/{id}/coach")
public class PlanCoachController {

    private final TrainingRepository trainingRepository;

    public PlanCoachController(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @GetMapping("/allTrainings")
    public String showAllTrainings(@PathVariable Long id, Model model){
        List<Training> trainings = trainingRepository.getByPlanId(id);
        model.addAttribute("trainings", trainings);
        return "planCoach/allTrainings";
    }

}
