package dk.ek.kinoxp.catalog.Service;

import dk.ek.kinoxp.catalog.dto.*;
import dk.ek.kinoxp.catalog.exception.NotFoundException;
import dk.ek.kinoxp.catalog.repository.ShowRepository;
import dk.ek.kinoxp.catalog.service.ShowService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShowServiceTest {

    @Mock ShowRepository repository;
    @InjectMocks
    ShowService showService;

    // ActorDto
    private ActorDto actor(long id) {
        return new ActorDto(id, "Actor " + id);
    }
    // GenreDto
    private GenreDto genre(long id) {
        return new GenreDto(id, "Genre " + id);
    }

    // MovieDto
    private MovieDto movieDto(long id) {
        // Alle @NotNull felter udfyldes
        return new MovieDto(
                id,
                "Dummy Movie",
                "poster.png",              // må gerne være null hvis ikke @NotNull — men en værdi er fin
                120,                       // duration_min
                15,                        // age_limit
                LocalDate.now(),           // start_date (NOT NULL)
                LocalDate.now().plusDays(7), // end_date (NOT NULL)
                List.of(actor(1L)),        // actors (min 1)
                List.of(genre(1L))         // genres (min 1)
        );
    }

    private TeatherDto teatherDto(long id) {
        return new TeatherDto(id, "Dummy Teather");
    }

    private ShowDto showDto(
            Long showId,
            LocalDate day,
            LocalTime start,
            LocalTime end,
            long movieId,
            long teatherId
    ) {
        return new ShowDto(
                showId,
                day,
                start,
                end,
                movieDto(movieId),
                teatherDto(teatherId)
        );
    }


    @Test
        // Sikrer at service kaster NotFoundException, når repo ikke finder show’et
    void getShowById_throwsNotFound_whenMissing() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> showService.getShowById(99L));

        verify(repository).findById(99L);
        verifyNoMoreInteractions(repository);
    }

    @Test
        // Sikrer at create() validerer tider (start >= end → IllegalArgumentException) og ikke kalder save()
    void create_throws_whenStartNotBeforeEnd() {
        var dto = showDto(
                null,
                LocalDate.now(),
                LocalTime.of(14, 0),
                LocalTime.of(14, 0),   // ugyldigt: start == end
                1L,
                1L
        );

        assertThrows(IllegalArgumentException.class, () -> showService.create(dto));
        verify(repository, never()).save(any());
        verifyNoMoreInteractions(repository);
    }
}