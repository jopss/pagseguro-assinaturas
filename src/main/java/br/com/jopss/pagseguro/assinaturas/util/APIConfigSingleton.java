package br.com.jopss.pagseguro.assinaturas.util;

import br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException;
import br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException;
import org.apache.commons.lang.StringUtils;

/**
 * Classe Singleton para guardar os dados de configurações, como email, token, charset, proxy, etc.
 * Utilizado internamente pela API.
 * 
 * @author João Paulo Sossoloti.
 */
public final class APIConfigSingleton {

	private boolean teste = false;
	private String email;
	private String token;
	private String charset = "UTF-8";

	private Integer proxyPorta;
	private String proxyURI;
	private String proxyUsuario;
	private String proxySenha;
	
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
	 * @return APIConfigSingleton.
	 */
	public static APIConfigSingleton get() {
		return APIConfigSingleton.autenticacao;
	}

	/**
	 * Limpa os dados armazenados na memória.
	 */
	public void limpar() {
		this.email = null;
		this.token = null;
		teste = false;
	}
	
        public String getDefaultUrlPreCheckout(){
		return "https://ws.pagseguro.uol.com.br/v2/checkout";
	}
        
	public String getDefaultUrlPreAprovacao(){
		return "https://ws.pagseguro.uol.com.br/v2/pre-approvals/request";
	}
	
	public String getDefaultUrlPagamento(){
		return "https://pagseguro.uol.com.br/v2/pre-approvals/request.html";
	}
	
	public String getDefaultUrlNotificacaoTransacao(){
		return "https://ws.pagseguro.uol.com.br/v2/transactions/notifications";
	}
	
	public String getDefaultUrlNotificacaoAssinatura(){
		return "https://ws.pagseguro.uol.com.br/v2/pre-approvals/notifications";
	}
	
	public String getDefaultUrlCobranca(){
		return "https://ws.pagseguro.uol.com.br/v2/pre-approvals/payment";
	}
	
	public String getDefaultUrlCancelamento(){
		return "https://ws.pagseguro.uol.com.br/v2/pre-approvals/cancel";
	}
	
	public boolean proxyConfigurado(){
		if(proxyPorta!=null && StringUtils.isNotBlank(proxyURI)){
			return true;
		}
		return false;
	}

	/**
	 * Retorna o e-mail associado a sua conta no PagSeguro. Caso o valor esteja inválido, lança exceção.
	 * 
	 * @return String.
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException 
	 */
	public String getEmail() throws AutorizacaoInvalidaException {
		if (StringUtils.isBlank(email)) {
			throw new AutorizacaoInvalidaException("Configuração: E-mail obrigatório.");
		}
		return email;
	}

	/**
	 * Insere o e-mail na instância singleton. Será utilizado internamente pela API no acesso aos serviços remotos.
	 * 
	 * @param email String.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retorna o token associado a sua conta no PagSeguro. Caso o valor esteja inválido, lança exceção.
	 * 
	 * @return String.
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException 
	 */
	public String getToken() throws AutorizacaoInvalidaException {
		if (StringUtils.isBlank(token)) {
			throw new AutorizacaoInvalidaException("Configuração: Token obrigatório.");
		}
		return token;
	}

	/**
	 * Insere o token na instância singleton. Será utilizado internamente pela API no acesso aos serviços remotos.
	 * 
	 * @param token String.
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Retorna o charset que será utilizado internamente pela API no acesso aos serviços remotos.
	 * Caso o valor esteja inválido, lança exceção. Já existe um valor padrão: "UTF-8".
	 * 
	 * @return String.
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException 
	 */
	public String getCharset() throws ConfiguracaoInvalidaException {
		if (StringUtils.isBlank(charset)) {
			throw new ConfiguracaoInvalidaException("Configuração: Charset obrigatório. Default: UTF-8.");
		}
		return charset;
	}

	/**
	 * Insere o charset na instância singleton. Será utilizado internamente pela API no acesso aos serviços remotos.
	 * 
	 * @param charset String.
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**
	 * Retorna a URL que será utilizado internamente pela API no acesso a pré autorização da assinatura.
	 * Caso o valor esteja inválido, lança exceção. Já existe um valor padrão: "https://ws.pagseguro.uol.com.br/v2/pre-approvals/request".
	 * 
	 * @return String.
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException
	 */
	public String getUrlPreAprovacao() throws ConfiguracaoInvalidaException, AutorizacaoInvalidaException {
		String urlPreAprovacao = this.getDefaultUrlPreAprovacao();
		if (StringUtils.isBlank(urlPreAprovacao)) {
			throw new ConfiguracaoInvalidaException("Configuração: urlPreAprovacao obrigatório.");
		}
		urlPreAprovacao = urlPreAprovacao + "?email="+APIConfigSingleton.get().getEmail();
		urlPreAprovacao = urlPreAprovacao + "&token="+APIConfigSingleton.get().getToken();
		if(APIConfigSingleton.get().isTeste()){
			urlPreAprovacao = urlPreAprovacao.replaceAll("ws.pagseguro", "ws.sandbox.pagseguro");
		}
		return urlPreAprovacao;
	}
        
	/**
	 * Retorna a URL que será utilizado internamente pela API no acesso a pré autorização da assinatura com checkou de uma valor inicial.
	 * Caso o valor esteja inválido, lança exceção. Já existe um valor padrão: "https://ws.pagseguro.uol.com.br/v2/checkout".
	 * 
	 * @return String.
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException
	 */
	public String getUrlPreCheckout() throws ConfiguracaoInvalidaException, AutorizacaoInvalidaException {
		String urlPreCheckout = this.getDefaultUrlPreCheckout();
		if (StringUtils.isBlank(urlPreCheckout)) {
			throw new ConfiguracaoInvalidaException("Configuração: urlPreAprovacao obrigatório.");
		}
		urlPreCheckout = urlPreCheckout + "?email="+APIConfigSingleton.get().getEmail();
		urlPreCheckout = urlPreCheckout + "&token="+APIConfigSingleton.get().getToken();
		if(APIConfigSingleton.get().isTeste()){
			urlPreCheckout = urlPreCheckout.replaceAll("ws.pagseguro", "ws.sandbox.pagseguro");
		}
		return urlPreCheckout;
	}
        
	/**
	 * Retorna a URL que será utilizado para redicionamento ao pagamento PagSeguro.
	 * Caso o valor esteja inválido, lança exceção. Já existe um valor padrão: "https://pagseguro.uol.com.br/v2/pre-approvals/request.html".
	 * 
	 * @param codigoPreAprovacao String com o código gerado na pré aprovação.
	 * @return String.
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException
	 */
	public String getUrlPagamento(String codigoPreAprovacao) throws ConfiguracaoInvalidaException {
		String urlPagamento = this.getDefaultUrlPagamento();
		if (StringUtils.isBlank(urlPagamento)) {
			throw new ConfiguracaoInvalidaException("Configuração: urlPagamento obrigatório.");
		}
		urlPagamento = urlPagamento + "?code="+codigoPreAprovacao;
		if(APIConfigSingleton.get().isTeste()){
			urlPagamento = urlPagamento.replaceAll("pagseguro.uol", "sandbox.pagseguro.uol");
		}
		return urlPagamento;
	}
	
	/**
	 * Retorna a URL que será utilizado internamente pela API no acesso a consulta de uma notificação de transacao ao PagSeguro.
	 * Caso o valor esteja inválido, lança exceção. Já existe um valor padrão: "https://ws.pagseguro.uol.com.br/v2/transactions/notifications".
	 * 
	 * @return String.
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException
	 */
	public String getUrlNotificacaoTransacao(String notificationCode) throws ConfiguracaoInvalidaException, AutorizacaoInvalidaException {
		String urlNotificacaoTransacao = this.getDefaultUrlNotificacaoTransacao();
		if (StringUtils.isBlank(urlNotificacaoTransacao)) {
			throw new ConfiguracaoInvalidaException("Configuração: urlNotificacaoTransacao obrigatório.");
		}
		urlNotificacaoTransacao = urlNotificacaoTransacao + "/"+notificationCode;
		urlNotificacaoTransacao = urlNotificacaoTransacao + "?email="+APIConfigSingleton.get().getEmail();
		urlNotificacaoTransacao = urlNotificacaoTransacao + "&token="+APIConfigSingleton.get().getToken();
		if(APIConfigSingleton.get().isTeste()){
			urlNotificacaoTransacao = urlNotificacaoTransacao.replaceAll("ws.pagseguro", "ws.sandbox.pagseguro");
		}
		return urlNotificacaoTransacao;
	}
	
	/**
	 * Retorna a URL que será utilizado internamente pela API no acesso a consulta de uma notificação de assinatura ao PagSeguro.
	 * Caso o valor esteja inválido, lança exceção. Já existe um valor padrão: "https://ws.pagseguro.uol.com.br/v2/pre-approvals/notifications".
	 * 
	 * @param notificationCode String
	 * @return String.
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException
	 */
	public String getUrlNotificacaoAssinatura(String notificationCode) throws ConfiguracaoInvalidaException, AutorizacaoInvalidaException {
		String urlNotificacaoAssinatura = this.getDefaultUrlNotificacaoAssinatura();
		if (StringUtils.isBlank(urlNotificacaoAssinatura)) {
			throw new ConfiguracaoInvalidaException("Configuração: urlNotificacaoAssinatura obrigatório.");
		}
		urlNotificacaoAssinatura = urlNotificacaoAssinatura + "/"+notificationCode;
		urlNotificacaoAssinatura = urlNotificacaoAssinatura + "?email="+APIConfigSingleton.get().getEmail();
		urlNotificacaoAssinatura = urlNotificacaoAssinatura + "&token="+APIConfigSingleton.get().getToken();
		if(APIConfigSingleton.get().isTeste()){
			urlNotificacaoAssinatura = urlNotificacaoAssinatura.replaceAll("ws.pagseguro", "ws.sandbox.pagseguro");
		}
		return urlNotificacaoAssinatura;
	}

	/**
	 * Retorna a URL que será utilizado internamente pela API para envio de uma cobrança de mensalidade ao PagSeguro.
	 * Caso o valor esteja inválido, lança exceção. Já existe um valor padrão: "https://ws.pagseguro.uol.com.br/v2/pre-approvals/payment".
	 * 
	 * @return String.
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException
	 */
	public String getUrlCobranca() throws ConfiguracaoInvalidaException, AutorizacaoInvalidaException {
		String urlCobranca = this.getDefaultUrlCobranca();
		if (StringUtils.isBlank(urlCobranca)) {
			throw new ConfiguracaoInvalidaException("Configuração: urlCobranca obrigatório.");
		}
		urlCobranca = urlCobranca + "?email="+APIConfigSingleton.get().getEmail();
		urlCobranca = urlCobranca + "&token="+APIConfigSingleton.get().getToken();
		if(APIConfigSingleton.get().isTeste()){
			urlCobranca = urlCobranca.replaceAll("ws.pagseguro", "ws.sandbox.pagseguro");
		}
		return urlCobranca;
	}

	/**
	 * Retorna a URL que será utilizado internamente pela API para envio de um cancelamento de mensalidade ao PagSeguro.
	 * Caso o valor esteja inválido, lança exceção. Já existe um valor padrão: "https://ws.pagseguro.uol.com.br/v2/pre-approvals/cancel".
	 * 
	 * @param codigoMensalidade String com o código da mensalidade.
	 * @return String.
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException
	 */
	public String getUrlCancelamento(String codigoMensalidade) throws ConfiguracaoInvalidaException, AutorizacaoInvalidaException {
		String urlCancelamento = this.getDefaultUrlCancelamento();
		if (StringUtils.isBlank(urlCancelamento)) {
			throw new ConfiguracaoInvalidaException("Configuração: urlCancelamento obrigatório.");
		}
		urlCancelamento = urlCancelamento + "/"+codigoMensalidade;
		urlCancelamento = urlCancelamento + "?email="+APIConfigSingleton.get().getEmail();
		urlCancelamento = urlCancelamento + "&token="+APIConfigSingleton.get().getToken();
		if(APIConfigSingleton.get().isTeste()){
			urlCancelamento = urlCancelamento.replaceAll("ws.pagseguro", "ws.sandbox.pagseguro");
		}
		return urlCancelamento;
	}

	/**
	 * Indica se está marcado para acesso ao ambiente de testes do PagSeguro. Será utilizado internamente pela API no acesso aos serviços remotos.
	 * 
	 * @return boolean.
	 */
	public boolean isTeste() {
		return teste;
	}

	/**
	 * Indica que os acessos serão para o ambiente de testes do PagSeguro. Será utilizado internamente pela API no acesso aos serviços remotos.
	 * 
	 * @param teste boolean.
	 */
	public void setTeste(boolean teste) {
		this.teste = teste;
	}
	
	/**
	 * Retorna a porta do proxy configurado. Será utilizado internamente pela API no acesso aos serviços remotos.
	 * 
	 * @return Integer.
	 */
	public Integer getProxyPorta() {
		return proxyPorta;
	}

	/**
	 * Indica a porta do proxy da sua rede. Será utilizado internamente pela API no acesso aos serviços remotos.
	 * 
	 * @param proxyPorta Integer.
	 */
	public void setProxyPorta(Integer proxyPorta) {
		this.proxyPorta = proxyPorta;
	}

	/**
	 * Retorna a URI do proxy configurado. Será utilizado internamente pela API no acesso aos serviços remotos.
	 * 
	 * @return String.
	 */
	public String getProxyURI() {
		return proxyURI;
	}

	/**
	 * Indica a URI do proxy da sua rede. Será utilizado internamente pela API no acesso aos serviços remotos.
	 * 
	 * @param proxyURI String.
	 */
	public void setProxyURI(String proxyURI) {
		this.proxyURI = proxyURI;
	}

	/**
	 * Retorna o usuário do proxy configurado. Será utilizado internamente pela API no acesso aos serviços remotos.
	 * 
	 * @return String.
	 */
	public String getProxyUsuario() {
		return proxyUsuario;
	}

	/**
	 * Indica o usuário do proxy da sua rede. Será utilizado internamente pela API no acesso aos serviços remotos.
	 * 
	 * @param proxyUsuario String.
	 */
	public void setProxyUsuario(String proxyUsuario) {
		this.proxyUsuario = proxyUsuario;
	}

	/**
	 * Retorna a senha do proxy configurado. Será utilizado internamente pela API no acesso aos serviços remotos.
	 * 
	 * @return String.
	 */
	public String getProxySenha() {
		return proxySenha;
	}

	/**
	 * Indica a senha do proxy da sua rede. Será utilizado internamente pela API no acesso aos serviços remotos.
	 * 
	 * @param proxySenha String.
	 */
	public void setProxySenha(String proxySenha) {
		this.proxySenha = proxySenha;
	}

}
