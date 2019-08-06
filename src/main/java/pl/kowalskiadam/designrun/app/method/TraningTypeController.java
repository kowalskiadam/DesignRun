package pl.kowalskiadam.designrun.app.method;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/traningType/{id}")
public class TraningTypeController {

    private final MethodRepository methodRepository;
    private final TrainingTypeRepository trainingTypeRepository;

    public TraningTypeController(MethodRepository methodRepository, TrainingTypeRepository trainingTypeRepository) {
        this.methodRepository = methodRepository;
        this.trainingTypeRepository = trainingTypeRepository;
    }

    @GetMapping(value = "/details")
    public String showUpdateForm(Model model, @PathVariable Long id) {
        TrainingType trainingType = trainingTypeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        model.addAttribute("trainingType", trainingType);
        return "method/traningTypeDetails";
    }

    @PostMapping(value="/details")
    public String executeUpdate(@ModelAttribute TrainingType trainingType, @PathVariable Long id){
        TrainingType oldTraningType = trainingTypeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        oldTraningType.setName(trainingType.getName());
        oldTraningType.setShortCut(trainingType.getShortCut());
        oldTraningType.setDescription(trainingType.getDescription());
        oldTraningType.setMaxDistance(trainingType.getMaxDistance());
        oldTraningType.setMinDistance(trainingType.getMinDistance());
        trainingTypeRepository.save(oldTraningType);
        Long methodId = oldTraningType.getMethod().getId();
        return "redirect:/method/"+methodId+"/details";
    }

}
