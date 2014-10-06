package br.com.jopss.pagseguro.assinaturas.modelos.suporte;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "phone")
public class Telefone {
	
	/**
	 * Código de área (DDD) do comprador.
	 * 
	 * Formato: Um número de 2 dígitos correspondente a um DDD válido. 
	 * Opcional.
	 */
	@XmlElement(name = "areaCode")
	private Integer codigoArea;
	
	/**
	 * Número de telefone do comprador.
	 * 
	 * Formato: Um número entre 7 e 9 dígitos. 
	 * Opcional.
	 */
	@XmlElement(name = "number")
	private Integer numero;

	public void setCodigoArea(Integer codigoArea) {
		this.codigoArea = codigoArea;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
}
