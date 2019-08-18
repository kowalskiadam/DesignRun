package pl.kowalskiadam.designrun.app.method;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "training_types")
public class TrainingType {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean hide;

    @NotNull
    @Size(min = 1, max = 40)
    private String name;

    @Size(min = 0, max = 5000)
    private String description;

    @NotNull
    @Size(min = 1, max = 10)
    private String shortCut;

    @Min(value=1)
    @Max(value = 99)
    private Integer minDistance;

    @Min(value=1)
    @Max(value = 99)
    private Integer maxDistance;

    @ManyToOne
    private Method method;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortCut() {
        return shortCut;
    }

    public void setShortCut(String shortCut) {
        this.shortCut = shortCut;
    }

    public Integer getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(Integer minDistance) {
        this.minDistance = minDistance;
    }

    public Integer getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(Integer maxDistance) {
        this.maxDistance = maxDistance;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "TrainingType{" +
                "id=" + id +
                ", hide=" + hide +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", shortCut='" + shortCut + '\'' +
                ", minDistance=" + minDistance +
                ", maxDistance=" + maxDistance +
                ", method=" + method +
                '}';
    }
}
