package dk.ek.kinoxp.catalog.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movie_id;
    private String title;
    private String poster;
    private int duration_min;
    private int age_limit;
    private LocalDate start_date;
    private LocalDate end_date;

    @ManyToMany
    private List<Actor> actors = new ArrayList<>();

    @ManyToMany
    private List<Genre> genres = new ArrayList<>();

    public Movie() {

    }

    public Movie(Long movie_id, String title, String poster, int duration_min, int age_limit, LocalDate start_date, LocalDate end_date) {
        this.movie_id = movie_id;
        this.title = title;
        this.poster = poster;
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
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

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void addActor(Actor actor) {
        actors.add(actor); // opdatere ejersiden (movie)
        actor.getMovies().add(this);// opdatere den ikke ejende-side (actor)
    }

    public void addGenre(Genre genre) {
        genres.add(genre); // opdatere ejersiden (movie)
        genre.getMovies().add(this); // opdatere den ikke ejende-side (genre)
    }

}
