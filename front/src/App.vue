<template>
	<h1>Calculadora de lanches</h1>
	<label for="nome">Nome :</label>
	<input id="nome" type="text" v-model="nome" />
	<br />
	<label for="number">Valor do Lanche :</label>
	<input id="number" type="number" v-model="valorLanche" />
	<br />
	<label for="estaPagando">Essa pessoa que vai pagar a conta?</label>
	<input id="estaPagando" type="checkbox" v-model="estaPagando" />
	<button @click="this.adicionaPessoa()">Adicionar pessoa</button>
	<br />
	<label for="acrescimosRS">Acréscimos (Em R$) :</label>
	<input id="acrescimosRS" type="text" v-model="detalhesLanches.acrescimos" />
	<br />
	<label for="descontoRS">Desconto (Em R$) :</label>
	<input id="descontoRS" type="number" v-model="detalhesLanches.desconto" />
	<br />
	<label for="acrescimosPct">Acréscimos (Em %) :</label>
	<input
		id="acrescimosPct"
		type="text"
		v-model="detalhesLanches.acrescimosPct"
	/>
	<br />
	<label for="descontoPct">Desconto (Em %) :</label>
	<input id="descontoPct" type="number" v-model="detalhesLanches.descontoPct" />
	<br />
	<button @click="enviar()">Enviar</button>
	<h2 v-for="(texto, index) in this.retorno" :key="index">{{ texto }}</h2>
	<br />
	<div
		v-for="(pessoa, index) in this.detalhesLanches.pessoasValores"
		:key="index"
	>
		Nome - {{ pessoa.nome }} - Valor Do Lanche - {{ pessoa.valorDoLanche }} -
		Está pagando? - {{ pessoa.estaPagando }}
	</div>
</template>

<script>
import axios from 'axios';
export default {
	name: 'App',
	data() {
		return {
			detalhesLanches: {
				pessoasValores: [],
				acrescimos: 0.0,
				desconto: 0.0,
				acrescimosPct: 0.0,
				descontoPct: 0.0,
			},
			nome: '',
			valorLanche: 0.0,
			retorno: [],
			estaPagando: false,
		};
	},
	methods: {
		adicionaPessoa() {
			let pessoaValor = {
				nome: this.nome,
				valorDoLanche: this.valorLanche,
				estaPagando: this.estaPagando,
			};
			this.detalhesLanches.pessoasValores.push(pessoaValor);
			this.nome = '';
			this.valorLanche = 0.0;
			this.estaPagando = false;
		},
		enviar() {
			this.retorno = [];

			axios
				.post('http://localhost:8080/lanches/dividir', this.detalhesLanches)
				.then((response) => {
					let pessoasValoresResponse = response.data.pessoasValores;
					for (let i = 0; i < pessoasValoresResponse.length; i++)
						this.retorno.push(
							pessoasValoresResponse[i].nome +
								' deverá pagar R$' +
								pessoasValoresResponse[i].valorAPagar +
								' - Link do pagamento - ' +
								pessoasValoresResponse[i].urlPagamento
						);
				});
		},
	},
};
</script>

<style></style>
