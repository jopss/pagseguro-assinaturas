package br.com.jopss.pagseguro.assinaturas;

import br.com.jopss.pagseguro.assinaturas.util.APIConfigSingleton;

/**
 * Disponibiliza itens sobre segurança, como métodos para manter as credenciais da empresa e acesso a geração do Token.
 * Uma vez adicionado as credenciais e gerado o Token, pode-se acessar qualquer outros serviços da API sem ser necessário
 * regerar o Token, a não ser quando este perde a sua validade pelo prazo de expiração.
 * 
 * A geração do token a partir das credenciais é o passo inicial necessário antes de qualquer outra ação.
 * Esta classe é chamada a partir da classe principal {@link br.gov.consumidor.consumidor.api.ConsumidorAPI}.
 * @author João Paulo Sossoloti.
 */
public final class ConfiguracaoAPI {

	/**
	 * Adiciona na instancia singleton o ID_CLIENT para geração do Token OAuth.
	 * @param id Long com a identificado do cliente.
	 * @return SegurancaAPI.
	 */
	public ConfiguracaoAPI setEmail(String email) {
		APIConfigSingleton.get().setEmail(email);
		return this;
	}

	/**
	 * Adiciona na instancia singleton o AUTHENTICATE_CODE para geração do Token OAuth.
	 * @param codigoAutenticacao String com o código de autenticação.
	 * @return SegurancaAPI.
	 */
	public ConfiguracaoAPI setToken(String token) {
		APIConfigSingleton.get().setToken(token);
		return this;
	}

	public ConfiguracaoAPI envioComoTeste() {
		APIConfigSingleton.get().setTeste(true);
		return this;
	}
}
