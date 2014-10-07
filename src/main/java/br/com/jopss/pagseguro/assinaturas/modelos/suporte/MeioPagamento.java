package br.com.jopss.pagseguro.assinaturas.modelos.suporte;

import br.com.jopss.pagseguro.assinaturas.modelos.suporte.enums.BandeiraBancoPagamentoTransacao;
import br.com.jopss.pagseguro.assinaturas.modelos.suporte.enums.TipoMeioPagamentoTransacao;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "paymentmethod")
public class MeioPagamento {
	
	/**
	 * Informa o tipo do meio de pagamento usado pelo comprador. Este tipo agrupa diversos meios de pagamento e determina de forma geral o comportamento da transação.
	 * 
	 * 1	Cartão de crédito: O comprador pagou pela transação com um cartão de crédito. Neste caso, o pagamento é processado imediatamente ou no máximo em algumas horas, dependendo da sua classificação de risco.
	2	Boleto: O comprador optou por pagar com um boleto bancário. Ele terá que imprimir o boleto e pagá-lo na rede bancária. Este tipo de pagamento é confirmado em geral de um a dois dias após o pagamento do boleto. O prazo de vencimento do boleto é de 3 dias.
	3	Débito online (TEF): O comprador optou por pagar com débito online de algum dos bancos com os quais o PagSeguro está integrado. O PagSeguro irá abrir uma nova janela com o Internet Banking do banco escolhido, onde o comprador irá efetuar o pagamento. Este tipo de pagamento é confirmado normalmente em algumas horas.
	4	Saldo PagSeguro: O comprador possuía saldo suficiente na sua conta PagSeguro e pagou integralmente pela transação usando seu saldo.
	5	Oi Paggo *: o comprador paga a transação através de seu celular Oi. A confirmação do pagamento acontece em até duas horas.
	7	Depósito em conta: o comprador optou por fazer um depósito na conta corrente do PagSeguro. Ele precisará ir até uma agência bancária, fazer o depósito, guardar o comprovante e retornar ao PagSeguro para informar os dados do pagamento. A transação será confirmada somente após a finalização deste processo, que pode levar de 2 a 13 dias úteis.
* 
	 * Formato: Inteiro. 
	 * Obrigatorio.
	 */
	@XmlElement(name = "type")
	private TipoMeioPagamentoTransacao tipo;
	
	/**
	 * Informa um código que identifica o meio de pagamento usado pelo comprador. O meio de pagamento descreve a bandeira de cartão de crédito utilizada ou banco escolhido para um débito online.
	 * 101	Cartão de crédito Visa.
102	Cartão de crédito MasterCard.
103	Cartão de crédito American Express.
104	Cartão de crédito Diners.
105	Cartão de crédito Hipercard.
106	Cartão de crédito Aura.
107	Cartão de crédito Elo.
108	Cartão de crédito PLENOCard.
109	Cartão de crédito PersonalCard.
110	Cartão de crédito JCB.
111	Cartão de crédito Discover.
112	Cartão de crédito BrasilCard.
113	Cartão de crédito FORTBRASIL.
114	Cartão de crédito CARDBAN.
115	Cartão de crédito VALECARD.
116	Cartão de crédito Cabal.
117	Cartão de crédito Mais!.
118	Cartão de crédito Avista.
119	Cartão de crédito GRANDCARD.
120	Cartão de crédito Sorocred.
201	Boleto Bradesco. *
202	Boleto Santander.
301	Débito online Bradesco.
302	Débito online Itaú.
303	Débito online Unibanco. *
304	Débito online Banco do Brasil.
305	Débito online Banco Real. *
306	Débito online Banrisul.
307	Débito online HSBC.
401	Saldo PagSeguro.
501	Oi Paggo. *
701	Depósito em conta - Banco do Brasil
702	Depósito em conta - HSBC
* 
	 * Formato: Inteiro. 
	 * Obrigatorio.
	 */
	@XmlElement(name = "code")
	private BandeiraBancoPagamentoTransacao bandeiraOuBanco;

}
