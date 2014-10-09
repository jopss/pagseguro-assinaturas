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
 * Classe de resposta sobre cobrança de uma assinatura.
 * 
 * @author João Paulo Sossoloti.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "result")
public class RespostaCobranca implements RespostaPagseguro {
	
	@XmlElement(name = "transactionCode")
	private String codigoTransacao;
	
	@XmlElement(name = "date")
	@XmlJavaTypeAdapter(DateAdapterJaxB.class)
	private Date data;

	/**
	 * Código identificador da transação.
	 * <ul>
	 *	<li>Formato: Texto de 32 caracteres.</li>
	 *	<li>Obrigatorio.</li>
	 * </ul>
	 * 
	 * @return String
	 */
	public String getCodigoTransacao() {
		return codigoTransacao;
	}

	/**
	 * Data da cobrança não presencial.
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
