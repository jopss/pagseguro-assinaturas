package br.com.jopss.pagseguro.assinaturas.util;

import br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException;
import org.apache.commons.lang.StringUtils;

/**
 * Classe Singleton para guardar os dados de autenticação e Token.
 * Utilizado internamente pela API.
 * @author João Paulo Sossoloti.
 */
public final class APIConfigSingleton {

	private String email;
	private String token;
	private boolean teste = false;

	/**
	 * Instância Singleton.
	 */
	private static final APIConfigSingleton autenticacao = new APIConfigSingleton();

	/**
	 * Construtor padrão bloqueado.
	 */
	private APIConfigSingleton() {
	}
	
	/**
	 * Método central para retorno desta entidade.
	 * @return Autenticacao.
	 */
	public static APIConfigSingleton get() {
		return APIConfigSingleton.autenticacao;
	}

	/**
	 * Limpa os dados armazenados na memória desta entidade.
	 */
	public void limpar() {
		this.email = null;
		this.token = null;
		teste = false;
	}

	/**
	 * Retorna o ID_CLIENT para acessar a geração do Token. Caso o valor esteja inválido, lança exceção.
	 * @return String.
	 * @throws AutorizacaoInvalidaException 
	 */
	public String getEmail() throws AutorizacaoInvalidaException {
		if (StringUtils.isBlank(email)) {
			throw new AutorizacaoInvalidaException("Segurança: Email.");
		}
		return email;
	}

	/**
	 * Insere o ID_CLIENT na instância singleton. Será utilizado internamente pela API no acesso aos serviços remotos.
	 * @param idCredenciada Long.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retorna o AUTHENTICATION_CODE para acessar a geração do Token. Caso o valor esteja inválido, lança exceção.
	 * @return String.
	 * @throws AutorizacaoInvalidaException 
	 */
	public String getToken() throws AutorizacaoInvalidaException {
		if (StringUtils.isBlank(token)) {
			throw new AutorizacaoInvalidaException("Segurança: token.");
		}
		return token;
	}

	/**
	 * Insere o AUTHENTICATION_CODE na instância singleton. Será utilizado internamente pela API no acesso aos serviços remotos.
	 * @param codigoAutenticacao String.
	 */
	public void setToken(String token) {
		this.token = token;
	}

	public boolean isTeste() {
		return teste;
	}

	public void setTeste(boolean teste) {
		this.teste = teste;
	}

}
