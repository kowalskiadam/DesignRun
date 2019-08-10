package pl.kowalskiadam.designrun.app.plan;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "weeks")
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Plan plan;

    @OneToMany(mappedBy = "week")
    private List<Day> days = new ArrayList<>();

    @OneToMany(mappedBy = "week")
    private List<Training> trainings = new ArrayList<>();

    private Integer orderInPlan;

    public Long getId() {
        return id;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public Integer getOrderInPlan() {
        return orderInPlan;
    }

    public void setOrderInPlan(Integer orderInPlan) {
        this.orderInPlan = orderInPlan;
    }



}
