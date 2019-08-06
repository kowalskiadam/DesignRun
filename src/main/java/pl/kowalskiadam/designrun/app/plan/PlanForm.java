package pl.kowalskiadam.designrun.app.plan;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.context.WebApplicationContext;
import pl.kowalskiadam.designrun.app.method.Method;
import pl.kowalskiadam.designrun.app.user.Athlete;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PlanForm {

    private Athlete athlete;
    private String name;
    private LocalDate startDay;
    private int weeksNumber;
    private List<Athlete> coachAthletes = new ArrayList<>();
    private Method method;
    private List<LocalDate> mondays = new ArrayList<>();

    private int mondaysTrainings;
    private int tuesdaysTrainings;
    private int wednesdaysTrainings;
    private int thursdaysTrainings;
    private int fridaysTrainings;
    private int saturdaysTrainings;
    private int sundaysTrainings;

    private List<Integer> trainingsInWeekdays = new ArrayList<>();


    public PlanForm() {
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public int getWeeksNumber() {
        return weeksNumber;
    }

    public void setWeeksNumber(int weeksNumber) {
        this.weeksNumber = weeksNumber;
    }

    public List<Athlete> getCoachAthletes() {
        return coachAthletes;
    }

    public void setCoachAthletes(List<Athlete> coachAthletes) {
        this.coachAthletes = coachAthletes;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public List<LocalDate> getMondays() {
        return mondays;
    }

    public void setMondays(List<LocalDate> mondays) {
        this.mondays = mondays;
    }

    public int getMondaysTrainings() {
        return mondaysTrainings;
    }

    public void setMondaysTrainings(int mondaysTrainings) {
        this.mondaysTrainings = mondaysTrainings;
    }

    public int getTuesdaysTrainings() {
        return tuesdaysTrainings;
    }

    public void setTuesdaysTrainings(int tuesdaysTrainings) {
        this.tuesdaysTrainings = tuesdaysTrainings;
    }

    public int getWednesdaysTrainings() {
        return wednesdaysTrainings;
    }

    public void setWednesdaysTrainings(int wednesdaysTrainings) {
        this.wednesdaysTrainings = wednesdaysTrainings;
    }

    public int getThursdaysTrainings() {
        return thursdaysTrainings;
    }

    public void setThursdaysTrainings(int thursdaysTrainings) {
        this.thursdaysTrainings = thursdaysTrainings;
    }

    public int getFridaysTrainings() {
        return fridaysTrainings;
    }

    public void setFridaysTrainings(int fridaysTrainings) {
        this.fridaysTrainings = fridaysTrainings;
    }

    public int getSaturdaysTrainings() {
        return saturdaysTrainings;
    }

    public void setSaturdaysTrainings(int saturdaysTrainings) {
        this.saturdaysTrainings = saturdaysTrainings;
    }

    public int getSundaysTrainings() {
        return sundaysTrainings;
    }

    public void setSundaysTrainings(int sundaysTrainings) {
        this.sundaysTrainings = sundaysTrainings;
    }

    public List<Integer> getTrainingsInWeekdays() {
        return trainingsInWeekdays;
    }

    public void setTrainingsInWeekdays(List<Integer> trainingsInWeekdays) {
        this.trainingsInWeekdays = trainingsInWeekdays;
    }

    public void populateTrainingsInWeekdays(){
        this.trainingsInWeekdays.add(this.mondaysTrainings);
        this.trainingsInWeekdays.add(this.tuesdaysTrainings);
        this.trainingsInWeekdays.add(this.wednesdaysTrainings);
        this.trainingsInWeekdays.add(this.thursdaysTrainings);
        this.trainingsInWeekdays.add(this.fridaysTrainings);
        this.trainingsInWeekdays.add(this.saturdaysTrainings);
        this.trainingsInWeekdays.add(this.sundaysTrainings);

    }

    @Override
    public String toString() {
        return "PlanForm{" +
                "athlete=" + athlete +
                ", name='" + name + '\'' +
                ", startDay=" + startDay +
                ", weeksNumber=" + weeksNumber +
                ", coachAthletes=" + coachAthletes +
                ", method=" + method +
                ", mondays=" + mondays +
                ", mondaysTrainings=" + mondaysTrainings +
                ", tuesdaysTrainings=" + tuesdaysTrainings +
                ", wednesdaysTrainings=" + wednesdaysTrainings +
                ", thursdaysTrainings=" + thursdaysTrainings +
                ", fridaysTrainings=" + fridaysTrainings +
                ", saturdaysTrainings=" + saturdaysTrainings +
                ", sundaysTrainings=" + sundaysTrainings +
                '}';
    }
}
