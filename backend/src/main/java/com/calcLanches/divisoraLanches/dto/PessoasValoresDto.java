package com.calcLanches.divisoraLanches.dto;

import lombok.Data;

@Data
public class PessoasValoresDto{
    String nome;
    Float valorDoLanche;
    Float valorAPagar;
    boolean estaPagando;
    String urlPagamento;
}
