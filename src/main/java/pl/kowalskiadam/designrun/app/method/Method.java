package pl.kowalskiadam.designrun.app.method;

import org.hibernate.validator.constraints.NotBlank;
import pl.kowalskiadam.designrun.app.plan.Plan;
import pl.kowalskiadam.designrun.app.user.Coach;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "methods")
public class Method {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 40)
    private String name;

    @Size(min = 0, max = 1000)
    private String shortDescription;

    @NotNull
    private boolean hide;

    @NotNull
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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String description) {
        this.shortDescription = description;
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
                ", description='" + shortDescription + '\'' +
                ", hide=" + hide +
                ", owner=" + owner +
                '}';
    }
}
