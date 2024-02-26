package com.calcLanches.divisoraLanches.service;


import com.calcLanches.divisoraLanches.dto.Dto;
import com.calcLanches.divisoraLanches.dto.PessoasValoresDto;

import java.util.List;

@org.springframework.stereotype.Service
public class CalculadoraService {

    public Dto dividirLanche(Dto dto){
        List<PessoasValoresDto> pessoasValores = dto.getPessoasValores();

        Float totalAPagar = descubrirTotalPagamento(pessoasValores);
        Float totalDesconto = totalAPagar + dto.getEntrega() - dto.getDesconto();

        for(PessoasValoresDto pessoaValor : pessoasValores){
            Float porcentagem = pessoaValor.getValorDoLanche()/totalAPagar;
            pessoaValor.setValorAPagar(totalDesconto * porcentagem);
        }

        return dto;
    }

    private Float descubrirTotalPagamento(List<PessoasValoresDto> pessoasValores){
        Float total = 0F;

        for(PessoasValoresDto pessoaValor : pessoasValores)
            total += pessoaValor.getValorDoLanche();

        return total;
    }
}
