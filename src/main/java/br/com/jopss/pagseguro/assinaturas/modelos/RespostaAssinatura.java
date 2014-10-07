package br.com.jopss.pagseguro.assinaturas.modelos;

import br.com.jopss.pagseguro.assinaturas.modelos.interfaces.RespostaPagseguro;
import br.com.jopss.pagseguro.assinaturas.modelos.suporte.enums.SituacaoAssinatura;
import br.com.jopss.pagseguro.assinaturas.modelos.suporte.enums.TipoAssinatura;
import br.com.jopss.pagseguro.assinaturas.util.DateAdapterJaxB;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "preApproval")
public class RespostaAssinatura implements RespostaPagseguro {
	
	/**
	 * Nome/Descrição da assinatura.
	 * 
	 * Formato: Texto.
	 */
	@XmlElement(name = "name")
	private String nomeAssinatura;
	
	/**
	 * Código identificador da assinatura.
	 * 
	 * Formato: Texto.
	 */
	@XmlElement(name = "code")
	private String codigoAssinatura;
	
	/**
	 * Data de criação/requisição da assinatura.
	 * 
	 * Formato: Data/Hora, YYYY-MM-DDThh:mm:ss.sTZD. 
	 */
	@XmlElement(name = "date")
	@XmlJavaTypeAdapter(DateAdapterJaxB.class)
	private Date data;
	
	/**
	 * Código identificador público. Utilizado para facilitar a diferenciação de múltiplas assinaturas com o mesmo nome/descrição.
	 * 
	 * Formato: Texto.
	 */
	@XmlElement(name = "tracker")
	private String identificadorPublico;
	
	/**
	 * Status atual da assinatura.
	 * 
	 * Formato: Texto.
	 */
	@XmlElement(name = "status")
	private SituacaoAssinatura situacao;
	
	/**
	 * Identificador que foi usado para fazer referência a assinatura no momento de sua requisição/cobrança.
	 * 
	 * Formato: Texto.
	 */
	@XmlElement(name = "reference")
	private String referenciaInterna;
	
	/**
	 * Data/hora em que ocorreu a última alteração no status da assinatura.
	 * 
	 * Formato: Data/Hora, YYYY-MM-DDThh:mm:ss.sTZD. 
	 */
	@XmlElement(name = "lastEventDate")
	private Date lastEventDate;
	
	/**
	 * Indica se a assinatura é gerenciada pelo vendedor (manual) ou pelo PagSeguro (auto)
	 * 
	 * Formato: Texto.
	 */
	@XmlElement(name = "charge")
	private TipoAssinatura tipo;
	
}
