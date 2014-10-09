package br.com.jopss.pagseguro.assinaturas;

/**
 * Classe inicial ao uso da API.
 * 
 * @author João Paulo Sossoloti.
 */
public final class PagSeguroAPI {
	
	/**
	 * Construtor padrao privado (padrao Singleton).
	 */
	private PagSeguroAPI(){}
	
	/**
	 * Retorna a propria instancia Singleton.
	 * @return {@link br.com.jopss.pagseguro.assinaturas.PagSeguroAPI}.
	 */	
	public static PagSeguroAPI instance(){
		return new PagSeguroAPI();
	}
	
	/**
	 * Acessa os itens sobre configuração, como setar email e token dos acessos.
	 * @return {@link  br.com.jopss.pagseguro.assinaturas.ConfiguracaoAPI}.
	 */
	public ConfiguracaoAPI config(){
		return new ConfiguracaoAPI();
	}
	
	/**
	 * Acessa os itens sobre assinaturas, como pré requisição, redirecionamento ao pagamento, cobrança e cancelamento.
	 * @return {@link  br.com.jopss.pagseguro.assinaturas.RequisicaoAssinatura}.
	 */
	public RequisicaoAssinatura assinatura(){
		return new RequisicaoAssinatura();
	}
	
	/**
	 * Acessa os itens sobre notificações de uma assinatura ou de uma transação.
	 * @return {@link  br.com.jopss.pagseguro.assinaturas.RequisicaoNotificacoes}.
	 */
	public RequisicaoNotificacoes notificacoes(){
		return new RequisicaoNotificacoes();
	}
	
}
