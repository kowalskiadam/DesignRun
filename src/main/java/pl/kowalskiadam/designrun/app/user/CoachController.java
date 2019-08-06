package pl.kowalskiadam.designrun.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kowalskiadam.designrun.app.method.Method;
import pl.kowalskiadam.designrun.app.method.MethodRepository;
import pl.kowalskiadam.designrun.app.plan.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/coach/{id}")
@SessionAttributes("planForm")
public class CoachController {

    private final CoachRepository coachRepository;
    private final MethodRepository methodRepository;
    private final AthleteRepository athleteRepository;
    private final PlanRepository planRepository;
    private final WeekRepository weekRepository;
    private final DayRepository dayRepository;
    private final TrainingRepository trainingRepository;

    @Autowired
    private PlanForm planForm;

    @ModelAttribute("planForm")
    public PlanForm getPlanForm(){
        return planForm;
    }



    public CoachController(CoachRepository coachRepository, MethodRepository methodRepository, AthleteRepository athleteRepository, PlanRepository planRepository, WeekRepository weekRepository, DayRepository dayRepository, TrainingRepository trainingRepository){
        this.coachRepository = coachRepository;
        this.methodRepository = methodRepository;
        this.athleteRepository = athleteRepository;
        this.planRepository = planRepository;
        this.weekRepository = weekRepository;
        this.dayRepository = dayRepository;
        this.trainingRepository = trainingRepository;
    }

    @GetMapping("/addPlan1")
    public String addNewPlan1(@PathVariable Long id, Model model){
        planForm.setCoachAthletes(coachAthletes(id));
        planForm.setMondays(findMondays());
        model.addAttribute("planFrom", planForm);
        findMondays();
        return "coach/addPlan1";
    }

    @PostMapping("/addPlan1")
    public String addNewPlan1complete1(@ModelAttribute PlanForm planForm, @PathVariable Long id){
        return "redirect:/coach/"+id+"/addPlan2";
    }

    @GetMapping("/addPlan2")
    public String addNewPlan2(Model model){
        return "coach/addPlan2";
    }

    @PostMapping("/addPlan2")
    public String addNewPlan1complete2(@ModelAttribute PlanForm planForm, @PathVariable Long id){
        System.out.println(planForm.getName());
        System.out.println(planForm.getWeeksNumber());
        Plan plan = new Plan();
        plan.setName(planForm.getName());
        plan.setWeeksNumber(planForm.getWeeksNumber());
        plan.setAthlete(planForm.getAthlete());
        plan.setCoach(thisCoach(id));
        plan.setStartDay(planForm.getStartDay());
        planRepository.save(plan);
        generatePlan(plan);
        planRepository.save(plan);
        generateDays(plan);
        planRepository.save(plan);
        planForm.populateTrainingsInWeekdays();
        generateTrainings(plan, planForm.getTrainingsInWeekdays());
        planRepository.save(plan);

        return "coach/dashboard";
    }


    @GetMapping("/dashboard")
    public String showDashboard(Model model, @PathVariable Long id){
        Coach coach = coachRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        model.addAttribute("coach", coach);
        return "coach/dashboard";
    }

    @GetMapping("/plansList")
    public String showPlansList(Model model, @PathVariable Long id){
        List<Method> coachMethods = methodRepository.getByOwnerIdAvailable(id);
        model.addAttribute("coachMethods", coachMethods);
        return "coach/plansList";
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
        return "coach/addMethod";
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
    private  List<Athlete> coachAthletes(Long id) {
        List<Athlete> athletes = athleteRepository.getAthleteByCoaches(id);
        return athletes;
    }

    private Coach thisCoach(Long id){
        Coach coach = coachRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return coach;
    }

    private List<LocalDate> findMondays(){
        LocalDate secondMondeyFromNow = null;
        for (int i = 8; i <= 14; i++){
            if (LocalDate.now().minusDays(i).getDayOfWeek() == DayOfWeek.MONDAY){
                secondMondeyFromNow = LocalDate.now().minusDays(i);
            }
        }
        List <LocalDate> mondays = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            mondays.add(secondMondeyFromNow.plusDays(i*7));
        }
        return mondays;
    }

    public void generatePlan(Plan plan){
        for (int i = 0; i < planForm.getWeeksNumber(); i++){
            Week week = new Week();
            week.setOrderInPlan(i+1);
            week.setPlan(plan);
            weekRepository.save(week);
        }
    }

    public void generateDays(Plan plan){
        List<Week> weeks = weekRepository.findByPlanId(plan.getId());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(weeks.toString());
        for (int i = 0; i < weeks.size(); i++){
            for (int j = 0; j < 7 ;j++){
                Day day = new Day();
                day.setDate(plan.getStartDay().plusDays(i*7 + j));
                day.setDayOfWeek(j+1);
                day.setWeek(weeks.get(i));
                dayRepository.save(day);
                System.out.println("========================================");
                System.out.println(day.toString());
            }
        }
    }

    public void generateTrainings(Plan plan, List<Integer> trainingsInWeekdays){
        List<Week> weeks = weekRepository.findByPlanId(plan.getId());
        for (int i = 0; i < weeks.size(); i++){
            List<Day> days = dayRepository.findByWeekId(weeks.get(i).getId());
            for (int j = 0; j < 7; j++){
                for (int k = 0; k < trainingsInWeekdays.get(j); k++){
                    Traning traning = new Traning();
                    traning.setDescription("Write description here");
                    traning.setDay(days.get(j));
                    trainingRepository.save(traning);
                }
            }

        }



    }

}
