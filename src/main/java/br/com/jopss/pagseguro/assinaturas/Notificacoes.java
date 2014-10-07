package br.com.jopss.pagseguro.assinaturas;

import br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException;
import br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException;
import br.com.jopss.pagseguro.assinaturas.exception.ErrosRemotosPagSeguroException;
import br.com.jopss.pagseguro.assinaturas.exception.ProblemaGenericoAPIException;
import br.com.jopss.pagseguro.assinaturas.modelos.RespostaAssinatura;
import br.com.jopss.pagseguro.assinaturas.modelos.RespostaTransacao;
import br.com.jopss.pagseguro.assinaturas.util.APIConfigSingleton;
import br.com.jopss.pagseguro.assinaturas.util.AcessoPagSeguro;

/**
 * Para de serviço para acessos a consultas de transações e assinaturas pelo número de notificação.
 * As notificações de transações são as mudanças de status, como pagamento, cancalemento, não pagamento, etc.
 * 
 * @author João Paulo Sossoloti.
 */
public final class Notificacoes {
	
	/**
	 * Método de acesso HTTP GET.
	 * Consulta uma transação pelo número de notificação recebido do PagSeguro.
	 * 
	 * @param notificationCode String com o número da notificação de transação.
	 * @return {@link br.com.jopss.pagseguro.assinaturas.modelos.RespostaTransacao}.
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ProblemaGenericoAPIException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ErrosRemotosPagSeguroException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException
	 */
	public RespostaTransacao transacao(String notificationCode) throws ProblemaGenericoAPIException, ErrosRemotosPagSeguroException, ConfiguracaoInvalidaException, AutorizacaoInvalidaException {
		return new AcessoPagSeguro().acessoGET( APIConfigSingleton.get().getUrlNotificacaoTransacao(notificationCode), RespostaTransacao.class );
	}
	
	/**
	 * Método de acesso HTTP GET.
	 * Consulta uma assinatura pelo número de notificação recebido do PagSeguro.
	 * 
	 * @param notificationCode String com o número da notificação de assinatura.
	 * @return {@link br.com.jopss.pagseguro.assinaturas.modelos.RespostaAssinatura}.
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ProblemaGenericoAPIException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ErrosRemotosPagSeguroException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException
	 */
	public RespostaAssinatura assinatura(String notificationCode) throws ProblemaGenericoAPIException, ErrosRemotosPagSeguroException, ConfiguracaoInvalidaException, AutorizacaoInvalidaException {
		return new AcessoPagSeguro().acessoGET( APIConfigSingleton.get().getUrlNotificacaoAssinatura(notificationCode), RespostaAssinatura.class );
	}
	
}
