pagseguro-assinaturas
=====================

API em Java que implementa as ações de assinaturas do PagSeguro, por meio do WS.

Licença
-------

O projeto foi concebido na licença Mozilla 2.0, ou seja, pode-se utilizar o jar em qualquer projeto, comercial ou não. Mas alterações na API devem ser obrigatoriamente disponibilizada na mesma licença.

É OpenSource, e como tal, qualquer um pode melhorar o código, corrigindo ou adicionando novas funcionalidades. Basta dar um fork neste repo.

Maven
-----

Ainda não há maven externo configurado.
	
Uso
===

Para utilizar esta lib na sua aplicação, basta configurar a dependência maven no seu projeto, utilizar a classe "PagSeguroAPI" para iniciar o fluxo.

Exemplo:

```java
//DADOS PARA ASSINATURA
String nome = "Nome do Plano a ser Assinado";
PeriodoPreAprovacao periodo = PeriodoPreAprovacao.MENSAL;
Double preco = 1.99D;
Date dataInicial = new Date();
Date dataFinal = new DateTime().plusYears(1).toDate();
Double maxVigencia = preco * 12;
String profileCode = "codigoMuitoSeguro123";
String token = "ABC123";
String email = "email@email.com";

//CONFIGURA A PRE APROVACAO
PreAprovacao pre = new PreAprovacao(nome, periodo, preco, dataInicial, dataFinal, maxVigencia);
pre.setTipo(TipoAssinatura.AUTOMATICO);
                
EnvioPreRequisicao envio = new EnvioPreRequisicao(pre);
envio.setIdReferenciaLocal(profileCode);
envio.setUrlRedirecionamentoAposConfirmacao("http://minhaapp/pagseguro/callback");

//CONFIGURA A API COM O USUARIO, SENHA, E QUAL AMBIENTE (PRODUCAO OU TESTE).
//PODE-SE ADICIONAR PROXY CASO PRECISE.
PagSeguroAPI.instance().config().setEmail(email).setToken(token).indicaAmbienteReal();
                        
try {
			
	//ENVIO EFETIVO AO PAGSEGURO
	RespostaPreAprovacao respostaPreAprovacao = PagSeguroAPI.instance().assinatura().preAprovacao(envio);
	PagSeguroAPI.instance().assinatura().redirecionarURLPagamento(response, respostaPreAprovacao);
			
} catch (ErrosRemotosPagSeguroException ex) {
	if(ex.contemErros()){
		for(Erro e : ex.getErrosPagSeguro().getErros()){
			System.out.println(e.getCodigoEMensagem());
		}
	}
} catch (PagSeguroException ex) {
	System.out.println(ex.getMessage());
}
```


Status
------

Em construção... (não há release).
