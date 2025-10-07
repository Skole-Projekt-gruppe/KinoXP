package dk.ek.kinoxp.catalog.controller;

import dk.ek.kinoxp.catalog.dto.TeatherDto;
import dk.ek.kinoxp.catalog.service.TeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teathers")
public class TeatherController
{
    private final TeatherService teatherService;

    public TeatherController(TeatherService service){
        this.teatherService = service;
    }

    @GetMapping
    public ResponseEntity <List<TeatherDto>> getAllTeathers(){
        List<TeatherDto> teatherDtos = teatherService.getAllTeaters();
        return ResponseEntity.ok(teatherDtos);
    }
}
