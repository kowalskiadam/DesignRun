package pl.kowalskiadam.designrun.app.plan;

import org.hibernate.validator.constraints.NotBlank;
import pl.kowalskiadam.designrun.app.method.Method;
import pl.kowalskiadam.designrun.app.user.Athlete;
import pl.kowalskiadam.designrun.app.user.Coach;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plans")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToMany(mappedBy = "plan")
    private List<Week> weeks = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "plan")
    private List<Day> days = new ArrayList<>();

    @NotNull
    @ManyToOne
    private Coach coach;

    @NotNull
    @ManyToOne
    private Athlete athlete;

    @NotNull
    @ManyToOne
    private Method method;

    @NotNull
    @Size(min = 2, max = 60)
    private String name;

    @NotNull
    private LocalDate startDay;

    @NotNull
    @Min(value=1)
    @Max(value = 52)
    private int weeksNumber;

    public Long getId() {
        return id;
    }

    public List<Week> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<Week> weeks) {
        this.weeks = weeks;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
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

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", coach=" + coach +
                ", athlete=" + athlete +
                ", method=" + method +
                ", name='" + name + '\'' +
                ", startDay=" + startDay +
                ", weeksNumber=" + weeksNumber +
                '}';
    }
}
