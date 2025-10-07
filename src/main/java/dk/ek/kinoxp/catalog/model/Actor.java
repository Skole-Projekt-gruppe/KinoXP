package dk.ek.kinoxp.catalog.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actor_id;
    private String name;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies = new ArrayList<>();

    public Actor() {
    }

    public Actor(Long actor_id, String name) {
        this.actor_id = actor_id;
        this.name = name;
    }

    public Long getActor_id() {
        return actor_id;
    }

    public void setActor_id(Long actor_id) {
        this.actor_id = actor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
