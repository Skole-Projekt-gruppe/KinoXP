package dk.ek.kinoxp.catalog.common;

import dk.ek.kinoxp.catalog.model.*;
import dk.ek.kinoxp.catalog.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final ShowRepository showRepository;
    private final TeatherRepository teatherRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;

    public InitData(MovieRepository movieRepository,
                    ShowRepository showRepository,
                    TeatherRepository teatherRepository,
                    GenreRepository genreRepository,
                    ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.teatherRepository = teatherRepository;
        this.showRepository = showRepository;
        this.genreRepository = genreRepository;
        this.actorRepository = actorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Genres
        Genre scifi = new Genre(null,"Science Fiction");
        Genre action = new Genre(null,"Action");
        Genre thriller = new Genre(null,"Thriller");
        Genre crime = new Genre(null,"Crime");
        Genre fantasy = new Genre(null,"Fantasy");
        genreRepository.saveAll(List.of(scifi,action,thriller,crime,fantasy));

        // Actors
        Actor dicaprio = new Actor(null,"Leonardo DiCaprio");
        Actor mcconaughey = new Actor(null,"Matthew McConaughey");
        Actor bale = new Actor(null,"Christian Bale");
        Actor yeoJeong = new Actor(null,"Cho Yeo-jeong");
        Actor phoenix = new Actor(null,"Joaquin Phoenix");
        Actor reeves = new Actor(null,"Keanu Reeves");
        Actor worthington = new Actor(null,"Sam Worthington");
        Actor cruise = new Actor(null, "Tom Cruise");
        Actor boseman = new Actor(null,"Chadwick Boseman");
        Actor pratt = new Actor(null,"Chris Pratt");
        Actor nelson = new Actor(null,"Craig T. Nelson");
        actorRepository.saveAll(List.of(dicaprio,mcconaughey,bale,yeoJeong,phoenix,reeves,worthington,cruise,boseman,pratt,nelson));

        Movie inception = new Movie(
                null,
                "Inception",
                "inception.png",
                148,
                13,
                LocalDate.of(2025, 10, 15),
                LocalDate.of(2025, 11, 15)
        );
        inception.addActor(dicaprio);
        inception.addGenre(scifi);
        movieRepository.save(inception);

        Movie interstellar = new Movie(
                null,
                "Interstellar",
                "interstellar.png",
                169,
                13,
                LocalDate.of(2025, 11, 1),
                LocalDate.of(2025, 12, 1)
        );
        interstellar.addActor(mcconaughey);
        interstellar.addGenre(scifi);
        movieRepository.save(interstellar);

        Movie theDarkKnight = new Movie(
                null,
                "The Dark Knight",
                "dark_knight.png",
                152,
                13,
                LocalDate.of(2025, 10, 20),
                LocalDate.of(2025, 11, 20)
        );
        theDarkKnight.addActor(bale);
        theDarkKnight.addGenre(action);
        movieRepository.save(theDarkKnight);

        Movie parasite = new Movie(
                null,
                "Parasite",
                "parasite.png",
                132,
                15,
                LocalDate.of(2025, 10, 25),
                LocalDate.of(2025, 11, 25)
        );
        parasite.addActor(yeoJeong);
        parasite.addGenre(thriller);
        movieRepository.save(parasite);

        Movie joker = new Movie(
                null,
                "Joker",
                "joker.png",
                122,
                16,
                LocalDate.of(2025, 11, 5),
                LocalDate.of(2025, 12, 5)
        );
        joker.addActor(phoenix);
        joker.addGenre(crime);
        movieRepository.save(joker);

        Movie matrixResurrections = new Movie(
                null,
                "The Matrix Resurrections",
                "matrix_resurrections.png",
                148,
                13,
                LocalDate.of(2025, 11, 10),
                LocalDate.of(2025, 12, 10)
        );
        matrixResurrections.addActor(reeves);
        matrixResurrections.addGenre(action);
        movieRepository.save(matrixResurrections);

        Movie avatar2 = new Movie(
                null,
                "Avatar: The Way of Water",
                "avatar2.png",
                192,
                12,
                LocalDate.of(2025, 12, 15),
                LocalDate.of(2026, 1, 15)
        );
        avatar2.addActor(worthington);
        avatar2.addGenre(fantasy);
        movieRepository.save(avatar2);

        Movie topGunMaverick = new Movie(
                null,
                "Top Gun: Maverick",
                "topgun_maverick.png",
                131,
                13,
                LocalDate.of(2025, 11, 20),
                LocalDate.of(2025, 12, 20)
        );
        topGunMaverick.addActor(cruise);
        topGunMaverick.addGenre(action);
        movieRepository.save(topGunMaverick);

        Movie blackPanther2 = new Movie(
                null,
                "Black Panther: Wakanda Forever",
                "black_panther2.png",
                161,
                12,
                LocalDate.of(2025, 11, 25),
                LocalDate.of(2025, 12, 25)
        );
        blackPanther2.addActor(boseman);
        blackPanther2.addGenre(action);
        movieRepository.save(blackPanther2);

        Movie guardians3 = new Movie(
                null,
                "Guardians of the Galaxy Vol. 3",
                "guardians3.png",
                150,
                13,
                LocalDate.of(2025, 12, 1),
                LocalDate.of(2025, 12, 30)
        );
        guardians3.addActor(pratt);
        guardians3.addGenre(action);
        movieRepository.save(guardians3);

        Movie incredibles2 = new Movie(
                null,
                "Incredibles 2",
                "incredibles2.png",
                118,
                7,
                LocalDate.of(2025, 11, 30),
                LocalDate.of(2025, 12, 30)
        );
        incredibles2.addActor(nelson);
        incredibles2.addGenre(action);
        movieRepository.save(incredibles2);



        Teather smallTeather = new Teather(
            null,
            "small teahter"
        );
        teatherRepository.save(smallTeather);

        Show insterstallar = new Show(
                null,
                LocalDate.of(2025,11,1),
                LocalTime.of(1,32),
                LocalTime.of(19,32),
                interstellar,
                smallTeather

        );
        showRepository.save(insterstallar);
    }
}
