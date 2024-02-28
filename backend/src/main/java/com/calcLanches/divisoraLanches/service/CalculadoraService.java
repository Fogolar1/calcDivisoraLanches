package com.calcLanches.divisoraLanches.service;


import com.calcLanches.divisoraLanches.client.PaymentClient;
import com.calcLanches.divisoraLanches.dto.Dto;
import com.calcLanches.divisoraLanches.dto.PessoasValoresDto;

import java.security.InvalidParameterException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class CalculadoraService {

    private final PaymentClient paymentClient;

    public Dto dividirLanche(Dto dto){
        List<PessoasValoresDto> pessoasValores = dto.getPessoasValores();

        Float totalAPagar = descubrirTotalPagamento(pessoasValores);
        Float totalDesconto = totalAPagar + dto.getAcrescimos() - dto.getDesconto();
        totalDesconto -= totalDesconto * (dto.getDescontoPct()/100);
        totalDesconto += totalDesconto * (dto.getAcrescimosPct()/100);

        if(totalAPagar <= 0)
            throw new InvalidParameterException("O valor total do lanche deve ser maior que zero");

        for(PessoasValoresDto pessoaValor : pessoasValores){
            Float porcentagem = pessoaValor.getValorDoLanche()/totalAPagar;
            pessoaValor.setValorAPagar(totalDesconto * porcentagem);
            if(!pessoaValor.isEstaPagando()){
                pessoaValor.setUrlPagamento(paymentClient.generatePaymentLink(pessoaValor.getValorAPagar().toString()));
            }else {
                pessoaValor.setUrlPagamento("Já está pagando a conta");
            }
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
