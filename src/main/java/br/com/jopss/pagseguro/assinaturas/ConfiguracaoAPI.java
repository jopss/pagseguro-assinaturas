package br.com.jopss.pagseguro.assinaturas;

import br.com.jopss.pagseguro.assinaturas.util.APIConfigSingleton;

/**
 * Disponibiliza itens de configurações, como métodos para indicar o email e token da sua conta empresarial, url´s, charset, proxy, etc.
 * Uma vez adicionado as configurações, pode-se acessar qualquer outros serviços da API em sequência.
 * 
 * A configuração do seu email e token do PagSeguro é o passo inicial necessário antes de qualquer outra ação.
 * Esta classe é chamada a partir da classe principal {@link br.com.jopss.pagseguro.assinaturas.PagSeguroAPI}.
 * 
 * @author João Paulo Sossoloti.
 */
public final class ConfiguracaoAPI {

	/**
	 * Adiciona na configuração o E-Mail da sua conta PagSeguro.
	 * Obrigatório para acessar a pré autorização.
	 * 
	 * @param email String.
	 * @return ConfiguracaoAPI
	 */
	public ConfiguracaoAPI setEmail(String email) {
		APIConfigSingleton.get().setEmail(email);
		return this;
	}

	/**
	 * Adiciona na configuração o Token da sua conta PagSeguro.
	 * Obrigatório para acessar a pré autorização.
	 * 
	 * @param token String.
	 * @return ConfiguracaoAPI
	 */
	public ConfiguracaoAPI setToken(String token) {
		APIConfigSingleton.get().setToken(token);
		return this;
	}

	/**
	 * Adiciona na configuração a URL da pré autorização.
	 * Opcional, já existe a configuração interna para a url 'https://ws.pagseguro.uol.com.br/v2/pre-approvals/request'.
	 * Altere se achar necessário. Internamente ele irá adicionar os parâmetro da requisição 'email' e 'token', bem como
	 * verificar se é para acessar o 'sandbox' para testes.
	 * 
	 * @param urlPreAprovacao String.
	 * @return ConfiguracaoAPI
	 */
	public ConfiguracaoAPI setUrlPreAprovacao(String urlPreAprovacao) {
		APIConfigSingleton.get().setUrlPreAprovacao(urlPreAprovacao);
		return this;
	}
	
	/**
	 * Adiciona na configuração a URL para redicionamento ao pagamento PagSeguro.
	 * Opcional, já existe a configuração interna para a url 'https://pagseguro.uol.com.br/v2/pre-approvals/request.html'.
	 * Altere se achar necessário. Internamente ele irá adicionar o parâmetro da requisição 'code', bem como
	 * verificar se é para acessar o 'sandbox' para testes.
	 * 
	 * @param urlPagamento String.
	 * @return ConfiguracaoAPI
	 */
	public ConfiguracaoAPI setUrlPagamento(String urlPagamento) {
		APIConfigSingleton.get().setUrlPagamento(urlPagamento);
		return this;
	}
	
	/**
	 * Adiciona na configuração o charset de acessos ao serviços remotos.
	 * Opcional, já existe a configuração interna para 'UTF-8'.
	 * Altere se achar necessário.
	 * 
	 * @param charset String.
	 * @return ConfiguracaoAPI
	 */
	public ConfiguracaoAPI setCharset(String charset) {
		APIConfigSingleton.get().setCharset(charset);
		return this;
	}
	
	/**
	 * Adiciona na configuração a porta do proxy da sua rede para saida aos serviços remotos.
	 * Opcional.
	 * 
	 * @param proxyPorta Integer.
	 * @return ConfiguracaoAPI
	 */
	public ConfiguracaoAPI setProxyPorta(Integer proxyPorta) {
		APIConfigSingleton.get().setProxyPorta(proxyPorta);
		return this;
	}
	
	/**
	 * Adiciona na configuração a URI do proxy da sua rede para saida aos serviços remotos.
	 * Opcional.
	 * 
	 * @param proxyURI String.
	 * @return ConfiguracaoAPI
	 */
	public ConfiguracaoAPI setProxyURI(String proxyURI) {
		APIConfigSingleton.get().setProxyURI(proxyURI);
		return this;
	}
	
	/**
	 * Adiciona na configuração o usuário do proxy da sua rede para saida aos serviços remotos.
	 * Opcional.
	 * 
	 * @param proxyUsuario String.
	 * @return ConfiguracaoAPI
	 */
	public ConfiguracaoAPI setProxyUsuario(String proxyUsuario) {
		APIConfigSingleton.get().setProxyUsuario(proxyUsuario);
		return this;
	}
	
	/**
	 * Adiciona na configuração a senha do proxy da sua rede para saida aos serviços remotos.
	 * Opcional.
	 * 
	 * @param proxySenha String.
	 * @return ConfiguracaoAPI
	 */
	public ConfiguracaoAPI setProxySenha(String proxySenha) {
		APIConfigSingleton.get().setProxySenha(proxySenha);
		return this;
	}
	
	/**
	 * Indica na configuração se os acessos ao serviços remotos do PagSeguro será para o ambiente de testes (sandbox).
	 * Opcional. Uma vez indicado, todos os demais acessos serão como testes.
	 * 
	 * @return ConfiguracaoAPI
	 */
	public ConfiguracaoAPI envioAmbienteTestes() {
		APIConfigSingleton.get().setTeste(true);
		return this;
	}
	
	/**
	 * Indica na configuração se os acessos ao serviços remotos do PagSeguro será para o ambiente de real (não teste).
	 * Opcional. Uma vez indicado, todos os demais acessos serão acessos reais.
	 * Por padrão todos os acessos são para os ambiente reais do PagSeguro.
	 * 
	 * @return ConfiguracaoAPI
	 */
	public ConfiguracaoAPI envioAmbienteReal() {
		APIConfigSingleton.get().setTeste(false);
		return this;
	}
}
