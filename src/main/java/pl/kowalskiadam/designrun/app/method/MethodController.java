package pl.kowalskiadam.designrun.app.method;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/method/{id}")
public class MethodController {

    private final MethodRepository methodRepository;
    private final TrainingTypeRepository trainingTypeRepository;

    public MethodController(MethodRepository methodRepository, TrainingTypeRepository trainingTypeRepository) {
        this.methodRepository = methodRepository;
        this.trainingTypeRepository = trainingTypeRepository;
    }

    @GetMapping(value = "/hide")
    public String hide(@PathVariable Long id) {
        Method method = methodRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        method.setHide(true);
        methodRepository.save(method);
        Long ownerId = method.getOwner().getId();
        return "redirect:/coach/"+ownerId+"/methodsList";
    }

    @GetMapping(value = "/unhide")
    public String unhide(@PathVariable Long id) {
        Method method = methodRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        method.setHide(false);
        methodRepository.save(method);
        Long ownerId = method.getOwner().getId();
        return "redirect:/coach/"+ownerId+"/methodsList";
    }

    @GetMapping(value = "/details")
    public String showUpdateForm(Model model, @PathVariable Long id) {
        Method method = methodRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        model.addAttribute("method", method);



        List<TrainingType> trainingTypes = trainingTypeRepository.getByMethodId(id);
        model.addAttribute("trainingTypes", trainingTypes);

        System.out.println(trainingTypes.toString());

        return "method/details";
    }

    @PostMapping(value="/details")
    public String executeUpdate(@ModelAttribute Method method, @PathVariable Long id){
        Method oldMethod = methodRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        oldMethod.setName(method.getName());
        oldMethod.setDescription(method.getDescription());
        methodRepository.save(oldMethod);
        Long ownerId = oldMethod.getOwner().getId();
        return "redirect:/coach/"+ownerId+"/methodsList";
    }

    @GetMapping(value = "/details/addTraningType")
    public String showAddTraningType(Model model, @PathVariable Long id) {

        model.addAttribute("traningType", new TrainingType());
        return "method/addTraningType";
    }

    @PostMapping(value = "/details/addTraningType")
    public String executeAddTraningType(@ModelAttribute TrainingType trainingType, @PathVariable Long id){
        Method method = methodRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        trainingType.setMethod(method);
        trainingType.setHide(false);
        trainingTypeRepository.save(trainingType);
        return "redirect:/method/"+id+"/details";

    }



}
