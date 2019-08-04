package pl.kowalskiadam.designrun.app.user;

import org.hibernate.annotations.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "athletes")
public class Athlete extends User {

    private String firstName;

    private String lastName;

    @ManyToMany(mappedBy = "athletes", cascade = CascadeType.PERSIST)
    private List<Coach> coaches = new ArrayList<>();

    @ManyToMany(mappedBy = "potentialAthletes", cascade = CascadeType.PERSIST)
    private List<Coach> potentialCoaches = new ArrayList<>();


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
}
