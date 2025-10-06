package dk.ek.kinoxp.catalog.common;

import dk.ek.kinoxp.catalog.model.Movie;
import dk.ek.kinoxp.catalog.model.Show;
import dk.ek.kinoxp.catalog.model.Teather;
import dk.ek.kinoxp.catalog.repository.MovieRepository;
import dk.ek.kinoxp.catalog.repository.ShowRepository;
import dk.ek.kinoxp.catalog.repository.TeatherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class InitData implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final ShowRepository showRepository;
    private final TeatherRepository teatherRepository;

    public InitData(MovieRepository movieRepository,
                    ShowRepository showRepository,
                    TeatherRepository teatherRepository) {
        this.movieRepository = movieRepository;
        this.teatherRepository = teatherRepository;
        this.showRepository = showRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Movie inception = new Movie(
                null,
                "Inception",
                "inception.png",
                148,
                13,
                LocalDate.of(2025, 10, 15),
                LocalDate.of(2025, 11, 15)
        );
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
