package com.calcLanches.divisoraLanches;


import com.calcLanches.divisoraLanches.dto.Dto;
import com.calcLanches.divisoraLanches.service.CalculadoraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lanches")
@RequiredArgsConstructor
public class Controller {

    private final CalculadoraService service;

    @PostMapping("/dividir")
    public ResponseEntity<Dto> dividirLanches(@RequestBody Dto dto){
        Dto dtoResponse = service.dividirLanche(dto);
        return ResponseEntity.ok(dtoResponse);
    }

}
