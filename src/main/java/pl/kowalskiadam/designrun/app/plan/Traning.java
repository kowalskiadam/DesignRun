package pl.kowalskiadam.designrun.app.plan;

import pl.kowalskiadam.designrun.app.method.TrainingType;

import javax.persistence.*;

@Entity
@Table(name = "tranings")
public class Traning {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private Day day;

        @ManyToOne
        private TrainingType trainingType;

        private int distance;

        private String name;

        private String shortCut;

        private String description;

        private String athleteComment;

    public Long getId() {
        return id;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortCut() {
        return shortCut;
    }

    public void setShortCut(String shortCut) {
        this.shortCut = shortCut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAthleteComment() {
        return athleteComment;
    }

    public void setAthleteComment(String athleteComment) {
        this.athleteComment = athleteComment;
    }

    @Override
    public String toString() {
        return "Traning{" +
                "id=" + id +
                ", day=" + day +
                ", trainingType=" + trainingType +
                ", distance=" + distance +
                ", name='" + name + '\'' +
                ", shortCut='" + shortCut + '\'' +
                ", description='" + description + '\'' +
                ", athleteComment='" + athleteComment + '\'' +
                '}';
    }
}
