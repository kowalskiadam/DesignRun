package pl.kowalskiadam.designrun.app.user;

import org.hibernate.Hibernate;
import pl.kowalskiadam.designrun.app.method.Method;
import pl.kowalskiadam.designrun.app.plan.Plan;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Entity
public class Coach extends User {

    @Size(max = 400)
    private String aboutMeShort;

    @OneToMany(mappedBy = "owner")
    private List<Method> methods = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "coaches_athletes")
    private List<Athlete> athletes = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "potential_coaches_athletes")
    private List<Athlete> potentialAthletes = new ArrayList<>();

    @OneToMany(mappedBy = "coach")
    private List<Plan> plans = new ArrayList<>();

    public String getAboutMeShort() {
        return aboutMeShort;
    }

    public void setAboutMeShort(String aboutMeShort) {
        this.aboutMeShort = aboutMeShort;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(List<Athlete> athletes) {
        this.athletes = athletes;
    }

    public List<Athlete> getPotentialAthletes() {
        return potentialAthletes;
    }

    public void setPotentialAthletes(List<Athlete> potentialAthletes) {
        this.potentialAthletes = potentialAthletes;
    }
}
