package pl.kowalskiadam.designrun.app.plan;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "days")
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Week week;

    @OneToMany(mappedBy = "day")
    private List<Traning> tranings = new ArrayList<>();

    private LocalDate date;

    private int dayOfWeek;

    public Long getId() {
        return id;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public List<Traning> getTranings() {
        return tranings;
    }

    public void setTranings(List<Traning> tranings) {
        this.tranings = tranings;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", week=" + week +
                ", tranings=" + tranings +
                ", date=" + date +
                ", dayOfWeek=" + dayOfWeek +
                '}';
    }
}

