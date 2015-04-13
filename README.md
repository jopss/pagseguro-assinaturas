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

Para utilizar esta lib na sua aplicação, centralize o uso e o fluxo pela classe "PagSeguroAPI". Com ela pode-se configurar dados, enviar e cancelar assinaturas, e receber notificações.

Exemplo de Envio de Assinaturas:

```java
//DADOS PARA ASSINATURA
String nome = "Nome do Plano a ser Assinado";
PeriodoPreAprovacao periodo = PeriodoPreAprovacao.MENSAL;
Double preco = 1.99D;
Date dataInicial = new Date();
Date dataFinal = new DateTime().plusYears(1).toDate(); //uso da API JodaTime.
Double maxVigencia = preco * 12;
String codigoInterno = "codigoMuitoSeguro123";
String token = "ABC123";
String email = "email@email.com";

//CONFIGURA A PRE APROVACAO
PreAprovacao pre = new PreAprovacao(nome, periodo, preco, dataInicial, dataFinal, maxVigencia);
pre.setTipo(TipoAssinatura.AUTOMATICO);
                
EnvioPreRequisicao envio = new EnvioPreRequisicao(pre);
envio.setIdReferenciaLocal(codigoInterno);
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
			System.out.println("ErrosRemotosPagSeguroException: "+ e.getCodigoEMensagem()); //tratar e exibir ao usuario conforme sua app...
		}
	}
} catch (PagSeguroException ex) {
	System.out.println("PagSeguroException: "+ ex); //tratar e exibir ao usuario conforme sua app...
}
```

Exemplo de Retorno de Notificações:

Abaixo segue um trecho de código com SpringMVC para exemplificar o retorno periódico do PagSeguro. A URL do retorno deve ser configurado na sua conta do PagSeguro. Os nomes dos parâmetros de código de notificação e tipo de notificação são padrões. Para mais informações, veja os docs online do PagSeguro.

```java
@Transactional
@RequestMapping(value = "/pagseguro/callback/periodic", method = RequestMethod.POST)
public void pagseguroPeriodicCallback(@RequestParam("notificationCode") String notificationCode, @RequestParam("notificationType") String notificationType) {

	//DADOS PARA ASSINATURA
	String token = "ABC123";
	String email = "email@email.com";

	try {
			//CONFIGURA A API COM O USUARIO, SENHA, E QUAL AMBIENTE (PRODUCAO OU TESTE).
			//PODE-SE ADICIONAR PROXY CASO PRECISE.
			PagSeguroAPI.instance().config().setEmail(email).setToken(token);
			
			//TENTA PEGAR A NOTIFICACAO
			RespostaNotificacaoAssinatura resposta = PagSeguroAPI.instance().notificacoes().assinatura(notificationCode);
            
            //faz algo com a resposta....
			
	} catch (ErrosRemotosPagSeguroException ex) {
		if(ex.contemErros()){
			for(Erro e : ex.getErrosPagSeguro().getErros()){
				System.out.println("ErrosRemotosPagSeguroException: "+ e.getCodigoEMensagem()); //tratar e exibir ao usuario conforme sua app...
			}
		}
	} catch (PagSeguroException ex) {
		System.out.println("PagSeguroException: "+ ex); //tratar e exibir ao usuario conforme sua app...
	} 
}
```

Status
------

Em construção e testes... (não há release).
