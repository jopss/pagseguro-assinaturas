package br.com.jopss.pagseguro.assinaturas;

import br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException;
import br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException;
import br.com.jopss.pagseguro.assinaturas.exception.ErrosRemotosPagSeguroException;
import br.com.jopss.pagseguro.assinaturas.exception.ProblemaGenericoAPIException;
import br.com.jopss.pagseguro.assinaturas.modelos.RespostaNotificacaoAssinatura;
import br.com.jopss.pagseguro.assinaturas.modelos.RespostaNotificacaoTransacao;
import br.com.jopss.pagseguro.assinaturas.util.APIConfigSingleton;
import br.com.jopss.pagseguro.assinaturas.util.AcessoPagSeguro;

/**
 * Classe de serviço para acessos a consultas de transações e assinaturas pelo número de notificação.
 * As notificações de transações são as mudanças de status, como pagamento, cancalemento, não pagamento, etc.
 * 
 * @author João Paulo Sossoloti.
 */
public final class RequisicaoNotificacoes {
	
	/**
	 * Método de acesso HTTP GET.
	 * Consulta uma transação pelo número de notificação recebido do PagSeguro.
	 * 
	 * @param notificationCode String com o número da notificação de transação.
	 * @return {@link br.com.jopss.pagseguro.assinaturas.modelos.RespostaNotificacaoTransacao}.
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ProblemaGenericoAPIException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ErrosRemotosPagSeguroException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException
	 */
	public RespostaNotificacaoTransacao transacao(String notificationCode) throws ProblemaGenericoAPIException, ErrosRemotosPagSeguroException, ConfiguracaoInvalidaException, AutorizacaoInvalidaException {
		return new AcessoPagSeguro().acessoGET( APIConfigSingleton.get().getUrlNotificacaoTransacao(notificationCode), RespostaNotificacaoTransacao.class );
	}
	
	/**
	 * Método de acesso HTTP GET.
	 * Consulta uma assinatura pelo número de notificação recebido do PagSeguro.
	 * 
	 * @param notificationCode String com o número da notificação de assinatura.
	 * @return {@link br.com.jopss.pagseguro.assinaturas.modelos.RespostaNotificacaoAssinatura}.
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ProblemaGenericoAPIException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ErrosRemotosPagSeguroException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException
	 */
	public RespostaNotificacaoAssinatura assinatura(String notificationCode) throws ProblemaGenericoAPIException, ErrosRemotosPagSeguroException, ConfiguracaoInvalidaException, AutorizacaoInvalidaException {
		return new AcessoPagSeguro().acessoGET( APIConfigSingleton.get().getUrlNotificacaoAssinatura(notificationCode), RespostaNotificacaoAssinatura.class );
	}
	
}
