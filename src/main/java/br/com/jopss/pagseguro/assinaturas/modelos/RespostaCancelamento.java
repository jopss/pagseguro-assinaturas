package br.com.jopss.pagseguro.assinaturas.modelos;

import br.com.jopss.pagseguro.assinaturas.modelos.interfaces.RespostaPagseguro;
import br.com.jopss.pagseguro.assinaturas.util.DateAdapterJaxB;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Classe de resposta sobre cancelamento de uma assinatura.
 * 
 * @author João Paulo Sossoloti.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "result")
public class RespostaCancelamento implements RespostaPagseguro {
	
	@XmlElement(name = "status")
	private String situacao;
	
	@XmlElement(name = "date")
	@XmlJavaTypeAdapter(DateAdapterJaxB.class)
	private Date data;

	/**
	 * Resposta ao pedido de cancelamento.
	 * <ul>
	 *	<li>Formato: Texto.</li>
	 *	<li>Obrigatorio.</li>
	 * </ul>
	 * 
	 * @return String
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * Data de solicitação do cancelamento.
	 * <ul>
	 *	<li>Formato: Data/Hora, YYYY-MM-DDThh:mm:ss.sTZD.</li>
	 *	<li>Obrigatorio.</li>
	 * </ul>
	 * 
	 * @return Date
	 */
	public Date getData() {
		return data;
	}
	
}
