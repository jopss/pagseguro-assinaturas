package br.com.jopss.pagseguro.assinaturas.modelos;

import br.com.jopss.pagseguro.assinaturas.modelos.interfaces.RespostaPagseguro;
import br.com.jopss.pagseguro.assinaturas.util.DateAdapterJaxB;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "preApprovalRequest")
public class RespostaPreAprovacao implements RespostaPagseguro {
	
	/**
	 * Código de requisição criado. Este código deve ser usado para direcionar o comprador para o fluxo de aprovação. 
	 * 
	 * Formato: Uma sequência de 32 caracteres.
	 * Obrigatorio.
	 */
	@XmlElement(name = "code")
	private String codigo;
	
	/**
	 * Data da requisição
	 * 
	 * Formato: Data/Hora, YYYY-MM-DDThh:mm:ss.sTZD. 
	 * Obrigatorio.
	 */
	@XmlElement(name = "date")
	@XmlJavaTypeAdapter(DateAdapterJaxB.class)
	private Date data;

	public String getCodigo() {
		return codigo;
	}

	public Date getData() {
		return data;
	}
	
}
