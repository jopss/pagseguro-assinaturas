package br.com.jopss.pagseguro.assinaturas.exception;

public class AutorizacaoInvalidaException extends Exception {

	public AutorizacaoInvalidaException(String message) {
		super(message);
	}

	public AutorizacaoInvalidaException(Throwable cause) {
		super(cause);
	}
}
