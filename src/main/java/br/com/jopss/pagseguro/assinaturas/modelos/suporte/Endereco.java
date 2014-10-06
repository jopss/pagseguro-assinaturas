package br.com.jopss.pagseguro.assinaturas.modelos.suporte;

import br.com.jopss.pagseguro.assinaturas.modelos.suporte.enums.Pais;
import br.com.jopss.pagseguro.assinaturas.modelos.suporte.enums.UF;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "address")
public class Endereco {
	
	/**
	 * Endereço do comprador. 
	 * 
	 * Formato: Livre, com limite de 80 caracteres. 
	 * Opcional.
	 */
	@XmlElement(name = "street")
	private String rua;
	
	/**
	 * Número do endereço do comprador. 
	 * 
	 * Formato: Livre, com limite de 20 caracteres. 
	 * Opcional.
	 */
	@XmlElement(name = "number")
	private String numero;
	
	/**
	 * Complemento (bloco, apartamento, etc.) do endereço do comprador. 
	 * 
	 * Formato: Livre, com limite de 40 caracteres. 
	 * Opcional.
	 */
	@XmlElement(name = "complement")
	private String complemento;
	
	/**
	 * Bairro do endereço do comprador. 
	 * 
	 * Formato: Livre, com limite de 60 caracteres. 
	 * Opcional.
	 */
	@XmlElement(name = "district")
	private String bairro;
	
	/**
	 * CEP do endereço do comprador. 
	 * 
	 * Formato: Um número de 8 dígitos correspondente a um CEP válido.
	 * Opcional.
	 */
	@XmlElement(name = "postalCode")
	private Integer cep;
	
	/**
	 * Cidade do endereço do comprador. 
	 * 
	 * Formato: Deve ser um nome válido de cidade do Brasil, com no mínimo 2 e no máximo 60 caracteres. 
	 * Opcional.
	 */
	@XmlElement(name = "city")
	private String cidade;
	
	/**
	 * Unidade Federativa do endereço do comprador. 
	 * 
	 * Formato: Duas letras, em maiúsculo, representando a sigla do estado brasileiro correspondente (p.e, SP). 
	 * Opcional.
	 */
	@XmlElement(name = "state")
	private UF estado;
	
	/**
	 * País do endereço do comprador. 
	 * 
	 * Formato: Reconhece apenas o valor BRA. 
	 * Opcional.
	 */
	@XmlElement(name = "country")
	private Pais pais = Pais.BRA;

	public void setRua(String rua) {
		this.rua = rua;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setEstado(UF estado) {
		this.estado = estado;
	}
	
}
