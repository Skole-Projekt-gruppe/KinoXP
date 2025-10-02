package dk.ek.kinoxp.catalog.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movie_id;
    private String title;
    private int duration_min;
    private int age_limit;
    private Date start_date;
    private Date end_date;

    public Movie() {

    }

    public Movie(Long movie_id, String title, int duration_min, int age_limit, Date start_date, Date end_date) {
        this.movie_id = movie_id;
        this.title = title;
        this.duration_min = duration_min;
        this.age_limit = age_limit;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public Long getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Long movie_id) {
        this.movie_id = movie_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration_min() {
        return duration_min;
    }

    public void setDuration_min(int duration_min) {
        this.duration_min = duration_min;
    }

    public int getAge_limit() {
        return age_limit;
    }

    public void setAge_limit(int age_limit) {
        this.age_limit = age_limit;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
