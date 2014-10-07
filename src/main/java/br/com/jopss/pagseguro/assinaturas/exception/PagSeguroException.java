package br.com.jopss.pagseguro.assinaturas.exception;

public class PagSeguroException extends Exception {

	public PagSeguroException(String message) {
		super(message);
	}

	public PagSeguroException(Throwable cause) {
		super(cause);
	}
}
