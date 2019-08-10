package pl.kowalskiadam.designrun.app.plan;

import pl.kowalskiadam.designrun.app.method.TrainingType;

import java.time.DayOfWeek;

public class TrainingForm {

    private int id;
    private DayOfWeek dayOfWeek;
    private int order;
    private TrainingType trainingType;
    private Integer distance;

    public TrainingForm(int id, DayOfWeek dayOfWeek,int order) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "TrainingForm{" +
                "id=" + id +
                ", dayOfWeek=" + dayOfWeek +
                ", order=" + order +
                ", trainingType=" + trainingType +
                ", distance=" + distance +
                '}';
    }
}
