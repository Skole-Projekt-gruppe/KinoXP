package dk.ek.kinoxp.catalog.controller;

import dk.ek.kinoxp.catalog.dto.ShowDto;
import dk.ek.kinoxp.catalog.service.ShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController
{

        private final ShowService showService;

        public ShowController(ShowService showService) {
            this.showService = showService;
        }

        // Henter Shows
        @GetMapping
        public List<ShowDto> getAllShows() {
            return showService.getAllShow(); // modtager [] hvis tomt
        }
    @GetMapping("/{id}")
    public ResponseEntity<ShowDto>getshowById(@PathVariable Long id){
            try {
                ShowDto show = showService.getShowById(id);
                return  ResponseEntity.ok(show);
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<ShowDto> updateShow(
            @PathVariable Long id,      // id fra URL'en
            @RequestBody ShowDto dto    // delvis/fuld body fra klienten
    ) {
        // Rekonstruér DTO'en så show_id kommer fra path (records har ingen setters)
        ShowDto merged = new ShowDto(
                id,
                dto.planned_at(),
                dto.start_time(),
                dto.end_time(),
                dto.movie(),
                dto.teather()
        );

        ShowDto updated = showService.update(merged);
        return ResponseEntity.ok(updated);
    }

}

