package br.com.jopss.pagseguro.assinaturas.modelos.suporte;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "sender")
public class Cliente {
	
	/**
	 * Nome completo do comprador. 
	 * 
	 * Formato: Livre, com no mínimo duas sequências de strings e limite total de 50 caracteres. 
	 * Opcional.
	 */
	@XmlElement(name = "name")
	private String nome;
	
	/**
	 * E-mail do comprador. 
	 * 
	 * Formato: Um e-mail válido, com limite de 60 caracteres. 
	 * Opcional.
	 */
	@XmlElement(name = "email")
	private String email;

	@XmlElement(name = "phone")
	private Telefone telefone;
	
	@XmlElement(name = "address")
	private Endereco endereco;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
