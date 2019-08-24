package pl.kowalskiadam.designrun.app.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.kowalskiadam.designrun.app.method.Method;
import pl.kowalskiadam.designrun.app.method.MethodRepository;
import pl.kowalskiadam.designrun.app.method.TrainingType;
import pl.kowalskiadam.designrun.app.method.TrainingTypeRepository;
import pl.kowalskiadam.designrun.app.plan.*;

import javax.validation.Valid;
import javax.validation.Validator;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/coach/")
@SessionAttributes("planForm")
public class CoachController {

    private final CoachRepository coachRepository;
    private final MethodRepository methodRepository;
    private final AthleteRepository athleteRepository;
    private final PlanRepository planRepository;
    private final WeekRepository weekRepository;
    private final DayRepository dayRepository;
    private final TrainingRepository trainingRepository;
    private final TrainingTypeRepository trainingTypeRepository;
    private final UserRepository userRepository;
 //   private final Validator validator;

    @Autowired
    private PlanForm planForm;

    @Autowired
    private TrainingFormContainer trainingFormContainer;


    @ModelAttribute("planForm")
    public PlanForm getPlanForm(){
        return planForm;
    }

    @ModelAttribute("traningFromContainer")
    public TrainingFormContainer getTrainingFormContainer(){
        return trainingFormContainer;
    }

    public CoachController(CoachRepository coachRepository, MethodRepository methodRepository, AthleteRepository athleteRepository,
                           PlanRepository planRepository, WeekRepository weekRepository, DayRepository dayRepository,
                           TrainingRepository trainingRepository, TrainingTypeRepository trainingTypeRepository, Validator validator, UserRepository userRepository){
        this.coachRepository = coachRepository;
        this.methodRepository = methodRepository;
        this.athleteRepository = athleteRepository;
        this.planRepository = planRepository;
        this.weekRepository = weekRepository;
        this.dayRepository = dayRepository;
        this.trainingRepository = trainingRepository;
        this.trainingTypeRepository = trainingTypeRepository;
     //   this.validator = validator;
        this.userRepository = userRepository;
    }

    private Coach checkCoachSecurity(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            String login = ((UserDetails) principal).getUsername();
            Coach coach = coachRepository.findByLogin(login);
            return coach;
        }
        else return null;
    }

    @GetMapping("dashboard")
    public String showDashboard(Model model){

        Coach coach = checkCoachSecurity();
        if (coach == null){
            return "redirect:/login";
        } else {
            model.addAttribute("coach", coach);
            return "coach/dashboard";
        }
    }

    @GetMapping("plansList")
    public String showPlansList(Model model){
        Coach coach = checkCoachSecurity();
        if (coach == null){
            return "redirect:/login";
        } else {
            Long id = coach.getId();
            List <Plan> coachPlans = planRepository.getByCoachId(id);
            model.addAttribute("coachPlans", coachPlans);
            List<Method> coachMethods = methodRepository.getByOwnerIdAvailable(id);
            model.addAttribute("coachMethods", coachMethods);
            return "coach/plansList";
        }
    }

    @GetMapping("addPlan1")
    public String addNewPlan1(Model model){

        Coach coach = checkCoachSecurity();
        if (coach == null){
            return "redirect:/login";
        } else {
            Long id = coach.getId();
            planForm.setCoachAthletes(coachAthletes(id));
            planForm.setMondays(findMondays());
            planForm.setCoachMethods(coachMethod(id));
            model.addAttribute("planFrom", planForm);
            return "coach/addPlan1";
        }
    }

    @PostMapping("addPlan1")
    public String addNewPlan1complete1(@ModelAttribute PlanForm planForm){
        planForm.setTrainingTypes(trainingTypes(planForm.getMethod().getId()));
        return "redirect:/coach/addPlan2";
    }

    @GetMapping("addPlan2")
    public String addNewPlan2(Model model){

        Coach coach = checkCoachSecurity();
        if (coach == null){
            return "redirect:/login";
        } else {
            return "coach/addPlan2";
        }
    }

    @PostMapping("addPlan2")
    public String addNewPlan1complete2(@ModelAttribute PlanForm planForm){
        Coach coach = checkCoachSecurity();
        Long id = coach.getId();
        planForm.populateTrainingsInWeekdays();
        populateTraningForm(planForm.getTrainingFormsInWeekdays(), planForm.getTrainingsInWeekdays());
        Plan plan = new Plan();
        plan.setName(planForm.getName());
        plan.setWeeksNumber(planForm.getWeeksNumber());
        plan.setAthlete(planForm.getAthlete());
        plan.setCoach(thisCoach(id));
        plan.setStartDay(planForm.getStartDay());
        plan.setMethod(planForm.getMethod());
        planRepository.save(plan);
        generatePlan(plan);
        planRepository.save(plan);
        generateDays(plan);
        planRepository.save(plan);
        generateTrainingsNew(plan, planForm.getTrainingFormsInWeekdays());
        planRepository.save(plan);
        return "redirect:/coach/plansList";
    }

    @GetMapping("methodsList")
    public String showMethodList(Model model){
        Coach coach = checkCoachSecurity();
        if (coach == null){
            return "redirect:/login";
        } else {
            Long id = coach.getId();
            List<Method> coachMethods = methodRepository.getByOwnerIdAvailable(id);
            model.addAttribute("coachMethods", coachMethods);
            return "coach/methodsList";
        }
    }

    @GetMapping("addMethod")
    public String addNewMethod(Model model){
        Coach coach = checkCoachSecurity();
        if (coach == null){
            return "redirect:/login";
        } else {
            model.addAttribute("method", new Method());
            return "coach/addMethod";
        }
    }

    @PostMapping("addMethod")
    public String createNewMethod(@ModelAttribute @Valid Method method, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "coach/addMethod";
        }
        Coach coach = checkCoachSecurity();
        Long id = coach.getId();
        method.setOwner(coachRepository.findById(id).orElseThrow(IllegalArgumentException::new));
        method.setHide(false);
        methodRepository.save(method);
        return "redirect:methodsList";
    }

    @GetMapping("advanced")
    public String showAdvanced(){
        Coach coach = checkCoachSecurity();
        if (coach == null){
            return "redirect:/login";
        } else {
            return "coach/advanced";
        }
    }

    @GetMapping("hideMethodsList")
    public String showHideMethodList(Model model){
        Coach coach = checkCoachSecurity();
        if (coach == null){
            return "redirect:/login";
        } else {
            Long id = coach.getId();
            List<Method> coachHideMethods = methodRepository.getByOwnerIdHide(id);
            model.addAttribute("coachHideMethods", coachHideMethods);
            return "coach/hideMethodsList";
        }
    }

    @GetMapping("newAthlete")
    public String addNewAthlete(Model model){
        Coach coach = checkCoachSecurity();
        if (coach == null){
            return "redirect:/login";
        } else {
            model.addAttribute("athlete", new Athlete());
            return "coach/newAthlete";
        }
    }

    @PostMapping("newAthlete")
    public String createNewAthlete(@ModelAttribute @Valid Athlete athlete, BindingResult bindingResult, Model model){
        Coach coach = checkCoachSecurity();
        Long id = coach.getId();
        if (bindingResult.hasErrors()){
            return "coach/newAthlete";
        }
        List<User> users = userRepository.findAll();
        for (User user : users){
            if (user.getLogin().equals(athlete.getLogin())){
                model.addAttribute("message", "Login exist. Create athlete with new login");
                return "coach/newAthlete";
            }
        }
        athlete.setPassword(BCrypt.hashpw(athlete.getPassword(), BCrypt.gensalt()));
        athleteRepository.save(athlete);
        List<Athlete> athletes = athleteRepository.getAthleteByCoaches(id);
        athletes.add(athlete);
        coach.setAthletes(athletes);
        coachRepository.save(coach);
        return "redirect: athleteList";
    }


    @GetMapping("athleteList")
    public String showAthletes(Model model){
        Coach coach = checkCoachSecurity();
        if (coach == null){
            return "redirect:/login";
        } else {
            Long id = coach.getId();
            List<Athlete> athletes = athleteRepository.getAthleteByCoaches(id);
            model.addAttribute("athletes", athletes);
            model.addAttribute("coachId", id);
            return "coach/athleteList";
        }
    }

    @GetMapping("athleteRequestList")
    public String showAthletesRequest(Model model){
        Coach coach = checkCoachSecurity();
        if (coach == null){
            return "redirect:/login";
        } else {
            Long id = coach.getId();
            List<Athlete> potentialAthletes = athleteRepository.getPotentialAthleteByCoaches(id);
            model.addAttribute("potentialAthletes", potentialAthletes);
            model.addAttribute("coachId", id);
            return "coach/athleteRequestList";
        }
    }

    @GetMapping("findAthlete")
    public String findAthlete(Model model){
        Coach coach = checkCoachSecurity();
        if (coach == null){
            return "redirect:/login";
        } else {
            Long id = coach.getId();
            model.addAttribute("coachId", id);
            return "coach/findAthlete";
        }
    }


    @PostMapping("findAthlete")
    public String findAthete(@RequestParam  String loginToFind, Model model){

        Coach coach = checkCoachSecurity();
        Long id = coach.getId();

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
                potentialAthletes.add(athlete);
                coach.setPotentialAthletes(potentialAthletes);
                coachRepository.save(coach);
                message = "Request was sended. Wait patiently for he / she response";
            }
        }catch (Exception e){
            message = "Login is wrong or athlete blocks new invitations";
        }

        model.addAttribute("message", message);

        return "coach/addAthleteResult";

    }


    @GetMapping("remove/{athleteId}")
    public String removeAthlete(@PathVariable Long athleteId){
        Coach coach = checkCoachSecurity();
        if (coach == null){
            return "redirect:/login";
        } else {
            Long id = coach.getId();
            List<Athlete> athletes = athleteRepository.getAthleteByCoaches(id);
            removeAthlete(athletes, athleteId, id);
            coach.setAthletes(athletes);
            coachRepository.save(coach);
            return "redirect:/coach/athleteList";
        }
    }

    @GetMapping("cancelInvitation/{athleteId}")
    public String cancelInvitation(@PathVariable Long athleteId){

        Coach coach = checkCoachSecurity();
        if (coach == null){
            return "redirect:/login";
        } else {
            Long id = coach.getId();
            List<Athlete> potentialAthletes = athleteRepository.getPotentialAthleteByCoaches(id);
            removeAthlete(potentialAthletes, athleteId, id);
            coach.setPotentialAthletes(potentialAthletes);
            coachRepository.save(coach);
            return "redirect:/coach/athleteList";
        }
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

    private List<Method> coachMethod(Long id) {
        List<Method> methods = methodRepository.getByOwnerIdAvailable(id);
        return methods;
    }

    private Coach thisCoach(Long id){
        Coach coach = coachRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return coach;
    }

    private List<TrainingType> trainingTypes(Long id){
        List <TrainingType> trainingTypes = trainingTypeRepository.getByMethodId(id);
        return trainingTypes;
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
        System.out.println(weeks.toString());
        for (int i = 0; i < weeks.size(); i++){
            for (int j = 0; j < 7 ;j++){
                Day day = new Day();
                day.setDate(plan.getStartDay().plusDays(i*7 + j));
                day.setDayOfWeek(j+1);
                day.setWeek(weeks.get(i));
                day.setPlan(planRepository.findById(plan.getId()).orElseThrow(IllegalArgumentException::new));
                dayRepository.save(day);
            }
        }
    }

    public void generateTrainingsNew(Plan plan, List<TrainingForm> trainingForms){
        List<Day> planDays = dayRepository.getByPlanId(plan.getId());
        for (int i = 0; i < trainingForms.size(); i++){
            List<Week> weeks = weekRepository.findByPlanId(plan.getId());
            for (int j = 0; j < weeks.size(); j++){
                Training training = new Training();
                training.setDescription("Write description here");
                training.setDay(planDays.get(7*j+trainingForms.get(i).getDayOfWeek().getValue()-1));
                training.setDistance(planForm.getDefaultDistance());
                training.setTrainingType(planForm.getDefaultTrainingType());
                training.generateName();
                training.generateShortCut();
                training.setOrderInDay(trainingForms.get(i).getOrder());
                trainingRepository.save(training);
            }
        }
    }

/*    public void generateTrainings(Plan plan, List<Integer> trainingsInWeekdays){
        List<Week> weeks = weekRepository.findByPlanId(plan.getId());
        for (int i = 0; i < weeks.size(); i++){
            List<Day> days = dayRepository.findByWeekId(weeks.get(i).getId());
            for (int j = 0; j < 7; j++){
                for (int k = 0; k < trainingsInWeekdays.get(j); k++){
                    Training training = new Training();
                    training.setDescription("Write description here");
                    training.setDay(days.get(j));
                    trainingRepository.save(training);
                }
            }
        }
    }*/




    public void populateTraningForm(List<TrainingForm> trainingForms, List<Integer> trainingsInWeekdays){
        int id = 1;
        for (int j = 0; j < 7; j++){
            for (int k = 0; k < trainingsInWeekdays.get(j); k++){
                DayOfWeek dayOfWeek = DayOfWeek.of(j+1);
                trainingForms.add(new TrainingForm(id, dayOfWeek, k+1 ));
                id++;
            }
        }
    }



}
