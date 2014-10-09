package br.com.jopss.pagseguro.assinaturas.modelos;

import br.com.jopss.pagseguro.assinaturas.modelos.interfaces.EnvioPagseguro;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe de requisição para uma cobraça de mensalidade.
 * 
 * @author João Paulo Sossoloti.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "payment")
public class EnvioCobranca implements EnvioPagseguro {
	
	@XmlElement(name = "preApprovalCode")
	private String codigoAssinatura;
	
	@XmlElement(name = "reference")
	private String idReferenciaLocal;
	
	@XmlElement(name = "items")
	private final Set<EnvioCobranca.Item> itens = new HashSet<>();
	
	/**
	 * Construtor vazio necessario para formatacao automatica.
	 */
	public EnvioCobranca() {
	}
	
	/**
	 * Construtor que recebe como dependencia os dados da cobrança.
	 * 
	 * @param codigoAssinatura String. Código da assinatura, concedida previamente, que identifica a cobrança sendo realizada. Texto de 32 caracteres.
	 * @param descricao String. Descreve o item a ser cobrado. Texto de 100 caracteres.
	 * @param valor Double. Representa o preço a cobrado.
	 */
	public EnvioCobranca(String codigoAssinatura, String descricao, Double valor) {
		this.codigoAssinatura=codigoAssinatura;
		
		//o identificador e a quantidade esta fixa, pois para uma única mensalidade, não muda.
		Item itemUnico = new Item();
		itemUnico.id = 1;
		itemUnico.quantidade = 1;
		itemUnico.descricao = descricao;
		itemUnico.valor = valor;
	}
	
	/**
	 * Classe interna de suporte ao item da mensalidade.
	 * O WS do PagSeguro requer uma lista de itens a serem cobrados, mas
	 * como é sobre uma assinatura, sempre teremos um único item.
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "item")
	public class Item{
		
		@XmlElement(name = "id")
		protected Integer id;
		
		@XmlElement(name = "description")
		protected String descricao;
		
		@XmlElement(name = "amount")
		protected Double valor;
		
		@XmlElement(name = "quantity")
		protected Integer quantidade;
	}

	/**
	 * Código/Identificador para fazer referência à transação em seu sistema.
	 * <ul>
	 *	<li>Formato: Texto de 200 caracteres.</li>
	 *	<li>Opcional.</li>
	 * </ul>
	 * 
	 * @param idReferenciaLocal String
	 */
	public void setIdReferenciaLocal(String idReferenciaLocal) {
		this.idReferenciaLocal = idReferenciaLocal;
	}
	
}
