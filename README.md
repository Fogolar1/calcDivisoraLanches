
# Calculadora de Rachar Conta

## Instruções para Deploy

Para subir o projeto é necessário rodar dois serviços backend, basta subi-los a partir da IDE em instâncias separadas, os arquivos estão nas pastas **backend** e **paymentIntegration**.

O frontend foi desenvolvido em Vue apesar de ser bem simples, funcionando apenas para inserção de dados. Para subir a instância basta acessar a pasta **front** e executar 
`npm install` e depois subir na porta 3000 utilizando `npm run serve -- --port 3000`.



## Guia da API

#### Dividir Lanche

```
  POST /lanches/dividir
```

| Paramêtro | Tipo     | Descrição               |
| :-------- | :------- | :------------------------- |
| `pessoasValores` | `array` | Contém informações sobre as pessoas e o preço do lanche de cada um |
| `desconto` | `float` | Desconto da conta total em reais|
| `acrescimos` | `float` | Acréscimos da conta total em reais |
| `descontoPct` | `float` | Desconto da conta total em % |
| `acrescimosPct` | `float` | Acréscimos da conta total em % |

Ao fim, um exemplo de payload seria esse: 


```
{
    "pessoasValores" : [
        {
            "nome": "Arthur",
            "valorDoLanche" : 42,
            "estaPagando" : true
        },
        {
            "nome": "Pietra",
            "valorDoLanche" : 8,
            "estaPagando" : false
        }
    ],
    "desconto" : 20,
    "acrescimos" : 8,
    "descontoPct" : 0,
    "acrescimosPct" : 0
}
```

O retorno esperado do endpoint é:

```
{
    "pessoasValores" : [
        {
            "nome": "Arthur",
            "valorDoLanche" : 42,
            "valorAPagar" : 31.92,
            "estaPagando" : true,
            "urlPagamento" : "Já está pagando a conta"
        },
        {
            "nome": "Pietra",
            "valorDoLanche" : 8,
            "valorAPagar" : 6.08,
            "estaPagando" : false,
            "urlPagamento" : "https://www.fakepicpay.com.br/payment/link?value=6.08"

        }
    ],
    "desconto" : 20,
    "acrescimos" : 8,
    "descontoPct" : 0,
    "acrescimosPct" : 0
}
```
Como Arthur já está pagando a conta não é necessário gerar um link de pagamento, visto que a Pietra estará realizando um pagamento para ele.
