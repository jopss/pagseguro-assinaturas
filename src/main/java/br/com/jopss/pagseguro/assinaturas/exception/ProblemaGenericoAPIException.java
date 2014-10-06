package br.com.jopss.pagseguro.assinaturas.exception;

public class ProblemaGenericoAPIException extends Exception {

	public ProblemaGenericoAPIException(String message) {
		super(message);
	}

	public ProblemaGenericoAPIException(Throwable cause) {
		super(cause);
	}
}
