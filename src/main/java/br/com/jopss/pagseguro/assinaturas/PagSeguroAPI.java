package br.com.jopss.pagseguro.assinaturas;

import br.com.jopss.pagseguro.assinaturas.requisicao.RequisicaoAssinatura;

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
	 * Acessa os itens sobre segurança e token.
	 * @return {@link br.gov.consumidor.consumidor.api.acesso.seguranca.SegurancaAPI}.
	 */
	public ConfiguracaoAPI config(){
		return new ConfiguracaoAPI();
	}
	
	public RequisicaoAssinatura assinatura(){
		return new RequisicaoAssinatura();
	}
	
}
