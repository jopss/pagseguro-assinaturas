package br.com.jopss.pagseguro.assinaturas.modelos;

import br.com.jopss.pagseguro.assinaturas.modelos.interfaces.RespostaPagseguro;
import br.com.jopss.pagseguro.assinaturas.modelos.suporte.MeioPagamento;
import br.com.jopss.pagseguro.assinaturas.modelos.suporte.enums.OrigemCancelamentoTransacao;
import br.com.jopss.pagseguro.assinaturas.modelos.suporte.enums.SituacaoTransacao;
import br.com.jopss.pagseguro.assinaturas.modelos.suporte.enums.TipoTransacao;
import br.com.jopss.pagseguro.assinaturas.util.DateAdapterJaxB;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "transaction")
public class RespostaTransacao implements RespostaPagseguro {
	
	/**
	 * Informa o momento em que a transação foi criada.
	 * 
	 * Formato: Data/Hora, YYYY-MM-DDThh:mm:ss.sTZD. 
	 * Obrigatorio.
	 */
	@XmlElement(name = "date")
	@XmlJavaTypeAdapter(DateAdapterJaxB.class)
	private Date dataTransacao;
	
	/**
	 * Retorna o código que identifica a transação de forma única.
	 * 
	 * Formato: Texto. Uma sequência de 36 caracteres.
	 * Obrigatorio.
	 */
	@XmlElement(name = "code")
	private String codigoTransacao;

	/**
	 * Informa o código que foi usado para fazer referência ao pagamento. 
	 * Este código foi fornecido no momento do pagamento e é útil para vincular as transações do 
	 * PagSeguro às vendas registradas no seu sistema.
	 * 
	 * Formato: Texto. Limite de 200 caracteres.
	 * Opcional.
	 */
	@XmlElement(name = "reference")
	private String idReferenciaLocal;
	
	/**
	 * Tipo da transação. Os valores mais comuns para este campo sao:
	 * 1	Pagamento: a transação foi criada por um comprador fazendo um pagamento. Este é o tipo mais comum de transação que você irá receber.
	 * 11	Assinatura: a transação foi criada a partir de uma cobrança de assinatura.
	 * 
	 * Formato: Inteiro.
	 * Obrigatorio.
	 */
	@XmlElement(name = "type")
	private TipoTransacao tipo;
	
	
	/**
	 * Informa o código representando o status da transação.
	 * 
	 *	1	Aguardando pagamento: o comprador iniciou a transação, mas até o momento o PagSeguro não recebeu nenhuma informação sobre o pagamento.
		2	Em análise: o comprador optou por pagar com um cartão de crédito e o PagSeguro está analisando o risco da transação.
		3	Paga: a transação foi paga pelo comprador e o PagSeguro já recebeu uma confirmação da instituição financeira responsável pelo processamento.
		4	Disponível: a transação foi paga e chegou ao final de seu prazo de liberação sem ter sido retornada e sem que haja nenhuma disputa aberta.
		5	Em disputa: o comprador, dentro do prazo de liberação da transação, abriu uma disputa.
		6	Devolvida: o valor da transação foi devolvido para o comprador.
		7	Cancelada: a transação foi cancelada sem ter sido finalizada.
* 
	 * Formato: Inteiro.
	 * Obrigatorio.
	 */
	@XmlElement(name = "status")
	private SituacaoTransacao situacao;
	
	/**
	 * Informa a origem do cancelamento da transação
	 * INTERNAL	PagSeguro
	 * EXTERNAL	Instituições Financeiras
	 * 
	 * Formato: Texto.
	 * Opcional (somente quando transactionStatus igual a 7).
	 */
	@XmlElement(name = "cancellationSource")
	private OrigemCancelamentoTransacao origemCancelamento;
	
	/**
	 * Informa o momento em que ocorreu a última alteração no status da transação.
	 * 
	 * Formato: Data/Hora, YYYY-MM-DDThh:mm:ss.sTZD. 
	 * Obrigatorio.
	 */
	@XmlElement(name = "lasteventdate")
	@XmlJavaTypeAdapter(DateAdapterJaxB.class)
	private Date dataUltimaMudancaSituacao;
	
	/**
	 * Data em que o valor da transação estará disponível na conta do vendedor.
	 * 
	 * Formato: Data/Hora, YYYY-MM-DDThh:mm:ss.sTZD. 
	 * Presente apenas quando o status da transação for um dos seguintes valores: Paga (3), Disponível (4), Em disputa (5) ou Devolvida (6).
	 */
	@XmlElement(name = "escrowEndDate")
	@XmlJavaTypeAdapter(DateAdapterJaxB.class)
	private Date dataCreditoVendedor;
	
	/**
	 * Informa o valor bruto da transação (calculado pela soma dos preços de todos os itens presentes no pagamento).
	 * 
	 * Formato: Decimal, com duas casas decimais separadas por ponto.
	 * Obrigatorio.
	 */
	@XmlElement(name = "grossamount")
	private Double valorBruto;
	
	/**
	 * Valor do desconto dado.
	 * 
	 * Formato: Decimal, com duas casas decimais separadas por ponto.
	 * Obrigatorio.
	 */
	@XmlElement(name = "discountamount")
	private Double desconto;
	
	/**
	 * Informa o valor total das taxas cobradas pelo PagSeguro nesta transação.
	 * 
	 * Formato: Decimal, com duas casas decimais separadas por ponto.
	 * Obrigatorio.
	 */
	@XmlElement(name = "feeamount")
	private Double taxasPagSeguro;
	
	/**
	 * Informa o valor líquido da transação, que corresponde ao valor bruto, menos o valor das taxas.
	 * Caso presente, o valor de extraAmount (que pode ser positivo ou negativo) também é considerado no cálculo.
	 * 
	 * Formato: Decimal, com duas casas decimais separadas por ponto.
	 * Obrigatorio.
	 */
	@XmlElement(name = "netamount")
	private Double valorLiquido;
	
	/**
	 * Informa um valor extra que foi somado ou subtraído do valor pago pelo comprador. Este valor é especificado por você no pagamento e pode representar um valor que você quer cobrar separadamente do comprador ou um desconto que quer dar a ele.
	 * 
	 * Formato: Decimal, com duas casas decimais separadas por ponto.
	 * Obrigatorio.
	 */
	@XmlElement(name = "extraamount")
	private Double valorExtra;
	
	/**
	 * Indica o número de parcelas que o comprador escolheu no pagamento com cartão de crédito.
	 * 
	 * Formato: Inteiro.
	 * Obrigatorio.
	 */
	@XmlElement(name = "installmentcount")
	private Integer parcelas;
	
	/**
	 * Aponta o número de itens contidos nesta transação.
	 * 
	 * Formato: Inteiro.
	 * Obrigatorio.
	 */
	@XmlElement(name = "itemcount")
	private Integer numeroItens;
	
	@XmlElement(name = "paymentmethod")
	private MeioPagamento metodoPagamento;
}
