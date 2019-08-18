package pl.kowalskiadam.designrun.app.plan;

import pl.kowalskiadam.designrun.app.method.TrainingType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tranings")
public class Training {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private Day day;

        @NotNull
        @ManyToOne
        private TrainingType trainingType;

        private int orderInDay;

        private int distance;

        private String name;

        private String shortCut;

        private String description;

        private String athleteComment;



        public Long getId() {
        return id;
    }

    public int getOrderInDay() {
        return orderInDay;
    }

    public void setOrderInDay(int orderInDay) {
        this.orderInDay = orderInDay;
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

    public void generateName(){
        this.name = this.trainingType.getName() + ": " + this.getDistance() + " km";
    }

    public void generateShortCut(){
        this.shortCut = this.trainingType.getShortCut() + ": " + this.getDistance() + " km";
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", day=" + day +
                ", trainingType=" + trainingType +
                ", orderInDay=" + orderInDay +
                ", distance=" + distance +
                ", name='" + name + '\'' +
                ", shortCut='" + shortCut + '\'' +
                ", description='" + description + '\'' +
                ", athleteComment='" + athleteComment + '\'' +
                '}';
    }
}
