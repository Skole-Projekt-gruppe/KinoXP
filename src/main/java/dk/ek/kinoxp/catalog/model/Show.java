package dk.ek.kinoxp.catalog.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "shows")
public class Show
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long show_id;
    private LocalDate planned_at;
    private LocalTime start_time;
    private LocalTime end_time;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "teather_id")
    private Teather teather;

    public Show()
    {
    }

    public Show(Long show_id, LocalDate planned_at, LocalTime start_time, LocalTime end_time, Movie movie, Teather teather)
    {
        this.show_id = show_id;
        this.planned_at = planned_at;
        this.start_time = start_time;
        this.end_time = end_time;
        this.movie = movie;
        this.teather = teather;
    }

    public Long getShow_id()
    {
        return show_id;
    }

    public void setShow_id(Long show_Id)
    {
        this.show_id = show_Id;
    }

    public LocalDate getPlanned_at()
    {
        return planned_at;
    }

    public void setPlanned_at(LocalDate planned_at)
    {
        this.planned_at = planned_at;
    }

    public LocalTime getStart_time()
    {
        return start_time;
    }

    public void setStart_time(LocalTime start_time)
    {
        this.start_time = start_time;
    }

    public LocalTime getEnd_time()
    {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time)
    {
        this.end_time = end_time;
    }

    public Movie getMovie()
    {
        return movie;
    }

    public void setMovie(Movie movie)
    {
        this.movie = movie;
    }

    public Teather getTeather()
    {
        return teather;
    }

    public void setTeather(Teather teather)
    {
        this.teather = teather;
    }
}
