package br.com.jopss.pagseguro.assinaturas.exception;

public class ConfiguracaoInvalidaException extends PagSeguroException {

	public ConfiguracaoInvalidaException(String message) {
		super(message);
	}

	public ConfiguracaoInvalidaException(Throwable cause) {
		super(cause);
	}
}
