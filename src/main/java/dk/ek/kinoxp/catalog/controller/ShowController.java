package dk.ek.kinoxp.catalog.controller;

import dk.ek.kinoxp.catalog.dto.ShowDto;
import dk.ek.kinoxp.catalog.service.ShowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController
{

        private final ShowService showService;

        public ShowController(ShowService showService) {
            this.showService = showService;
        }

        @GetMapping
        public List<ShowDto> getAllShows() {
            return showService.getAllShow(); // modtager [] hvis tomt
        }
}

