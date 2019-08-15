package pl.kowalskiadam.designrun.app.user;

import org.hibernate.annotations.Cascade;
import pl.kowalskiadam.designrun.app.plan.Plan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Athlete extends User {

    @ManyToMany(mappedBy = "athletes", cascade = CascadeType.PERSIST)
    private List<Coach> coaches = new ArrayList<>();

    @ManyToMany(mappedBy = "potentialAthletes", cascade = CascadeType.PERSIST)
    private List<Coach> potentialCoaches = new ArrayList<>();

    @OneToMany(mappedBy = "athlete")
    private List<Plan> plans;

    private String firstName;

    private String lastName;

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    public List<Coach> getPotentialCoaches() {
        return potentialCoaches;
    }

    public void setPotentialCoaches(List<Coach> potentialCoaches) {
        this.potentialCoaches = potentialCoaches;
    }

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
