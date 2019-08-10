package pl.kowalskiadam.designrun.app.method;

import pl.kowalskiadam.designrun.app.plan.Plan;
import pl.kowalskiadam.designrun.app.user.Coach;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "methods")
public class Method {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private boolean hide;

    @ManyToOne
    private Coach owner;

    @OneToMany(mappedBy = "method")
    private List<TrainingType> trainingTypes = new ArrayList<>();

    @OneToMany(mappedBy = "method")
    private List<Plan> plans = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public Coach getOwner() {
        return owner;
    }

    public void setOwner(Coach owner) {
        this.owner = owner;
    }

    public List<TrainingType> getTrainingTypes() {
        return trainingTypes;
    }

    public void setTrainingTypes(List<TrainingType> trainingTypes) {
        this.trainingTypes = trainingTypes;
    }

    @Override
    public String toString() {
        return "Method{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", hide=" + hide +
                ", owner=" + owner +
                '}';
    }
}
