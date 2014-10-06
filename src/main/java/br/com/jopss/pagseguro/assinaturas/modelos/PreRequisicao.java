package br.com.jopss.pagseguro.assinaturas.modelos;

import br.com.jopss.pagseguro.assinaturas.modelos.suporte.PreAprovacao;
import br.com.jopss.pagseguro.assinaturas.modelos.suporte.Cliente;
import br.com.jopss.pagseguro.assinaturas.modelos.interfaces.EnvioPagseguro;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "preApprovalRequest")
public class PreRequisicao implements EnvioPagseguro {
	
	/**
	 * URL para onde o comprador será redirecionado após a finalização do fluxo de assinatura. 
	 * 
	 * Formato: Uma URL válida, com limite de 255 caracteres. 
	 * Opcional.
	 */
	@XmlElement(name = "redirectURL")
	private String urlRedirecionamentoAposConfirmacao;
	
	/**
	 * URL para onde o comprador será redirecionado, durante o fluxo de aprovação, caso deseje alterar/revisar as regras da assinatura. 
	 * 
	 * Formato: Uma URL válida, com limite de 255 caracteres. 
	 * Opcional.
	 */
	@XmlElement(name = "reviewURL")
	private String urlRevisao;
	
	/**
	 * Código/Identificador para fazer referência a assinatura em seu sistema. 
	 * 
	 * Formato: Livre, com limite de 200 caracteres.
	 * Opcional.
	 */
	@XmlElement(name = "reference")
	private String idReferenciaLocal;
	
	@XmlElement(name = "sender")
	private Cliente cliente;
	
	@XmlElement(name = "preApproval")
	private PreAprovacao preAprovacao;

	public PreRequisicao() {
	}
	
	public PreRequisicao(PreAprovacao preAprovacao) {
		this.preAprovacao = preAprovacao;
	}

	public void setUrlRedirecionamentoAposConfirmacao(String urlRedirecionamentoAposConfirmacao) {
		this.urlRedirecionamentoAposConfirmacao = urlRedirecionamentoAposConfirmacao;
	}

	public void setUrlRevisao(String urlRevisao) {
		this.urlRevisao = urlRevisao;
	}

	public void setIdReferenciaLocal(String idReferenciaLocal) {
		this.idReferenciaLocal = idReferenciaLocal;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
