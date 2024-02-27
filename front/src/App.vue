<template>
  <h1>Calculadora de lanches</h1>
  <label for="nome">Nome :</label>
  <input id="nome" type="text" v-model="nome"/>
  <br>
  <label for="number">Valor do Lanche :</label>
  <input id="number" type="number" v-model="valorLanche">
  <br>
  <button @click="this.adicionaPessoa()">Adicionar pessoa</button>
  <br>
  <label for="entrega">Valor da entrega :</label>
  <input id="entrega" type="text" v-model="detalhesLanches.entrega"/>
  <br>
  <label for="desconto">Valor do desconto :</label>
  <br>
  <input id="desconto" type="number" v-model="detalhesLanches.desconto">
  <br>
  <button @click="enviar()">Enviar</button>
  <h2 v-for="(texto, index) in this.retorno" :key="index">{{ texto }}</h2>
</template>

<script>
 import axios from 'axios'
export default {
  name: 'App',
  data() {
    return {
      detalhesLanches : {
        pessoasValores : [],
        entrega : 0.00,
        desconto : 0.00
      },
      nome : "",
      valorLanche : 0.00,
      retorno : []
    }
  },
  methods : {
    adicionaPessoa(){
      let pessoaValor = {nome : this.nome, valorDoLanche : this.valorLanche}
      this.detalhesLanches.pessoasValores.push(pessoaValor);
      this.nome = "";
      this.valorLanche= 0.00;
    },
    enviar(){
      this.retorno= [];

      axios.post('http://localhost:8080/lanches/dividir', this.detalhesLanches)
      .then((response) => {
        let pessoasValoresResponse = response.data.pessoasValores;
        for(let i = 0; i < pessoasValoresResponse.length; i++)
          this.retorno.push(pessoasValoresResponse[i].nome + " deverá pagar " + pessoasValoresResponse[i].valorAPagar);
      })
    }
  }
}
</script>

<style>
</style>
