package com.calcLanches.divisoraLanches;


import com.calcLanches.divisoraLanches.dto.Dto;
import com.calcLanches.divisoraLanches.service.CalculadoraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lanches")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {

    private final CalculadoraService service;

    @PostMapping("/dividir")
    public ResponseEntity<Dto> dividirLanches(@RequestBody Dto dto){
        Dto dtoResponse = service.dividirLanche(dto);
        return ResponseEntity.ok(dtoResponse);
    }

}
