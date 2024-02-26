package com.calcLanches.divisoraLanches.dto;

import lombok.Data;

import java.util.List;

@Data
public class Dto {
    List<PessoasValoresDto> pessoasValores;
    Float desconto;
    Float entrega;
}
