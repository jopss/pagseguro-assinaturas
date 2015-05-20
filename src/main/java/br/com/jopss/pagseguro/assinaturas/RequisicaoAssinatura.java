package br.com.jopss.pagseguro.assinaturas;

import br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException;
import br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException;
import br.com.jopss.pagseguro.assinaturas.exception.ErrosRemotosPagSeguroException;
import br.com.jopss.pagseguro.assinaturas.exception.ProblemaGenericoAPIException;
import br.com.jopss.pagseguro.assinaturas.modelos.EnvioCobranca;
import br.com.jopss.pagseguro.assinaturas.modelos.EnvioPreRequisicao;
import br.com.jopss.pagseguro.assinaturas.modelos.EnvioPreRequisicaoCheckout;
import br.com.jopss.pagseguro.assinaturas.modelos.RespostaCancelamento;
import br.com.jopss.pagseguro.assinaturas.modelos.RespostaCobranca;
import br.com.jopss.pagseguro.assinaturas.modelos.RespostaPreAprovacao;
import br.com.jopss.pagseguro.assinaturas.modelos.RespostaPreAprovacaoCheckout;
import br.com.jopss.pagseguro.assinaturas.util.APIConfigSingleton;
import br.com.jopss.pagseguro.assinaturas.util.AcessoPagSeguro;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * Classe de serviço para acessos a registro de assinaturas.
 * Entende-se por assinatura a recorrência de um pagamento por um período de tempo.
 * 
 * @author João Paulo Sossoloti.
 */
public final class RequisicaoAssinatura {
	
	/**
	 * Método de acesso HTTP POST.
	 * Passo inicial para o registro de uma assinatura. O PagSeguro somente retorna a pré aprovação com um código do cliente, e data.
         * 
	 * Nesta etapa, passamos todos os dados de registro da assinatura, com valores, período, etc. Não é possível passar uma data futura para início do pagamento, 
         * nem um valor para ser pago inicialmente diferente da assinatura. Para isso, use o método 'preAprovacaoComCheckout'.
         * 
	 * Com o código retornado em mãos, podemos efetivar a autorização redirecionando para a página de pagamento.
	 * 
	 * @param preRequisicao EnvioPreRequisicao com os dados da assinatura e do cliente.
	 * @return {@link br.com.jopss.pagseguro.assinaturas.modelos.RespostaPreAprovacao} resposta com código do cliente e data, para iniciar fluxo de pagamento.
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ProblemaGenericoAPIException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ErrosRemotosPagSeguroException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException
	 */
	public RespostaPreAprovacao preAprovacao(EnvioPreRequisicao preRequisicao) throws ProblemaGenericoAPIException, ErrosRemotosPagSeguroException, ConfiguracaoInvalidaException, AutorizacaoInvalidaException {
		return new AcessoPagSeguro().acessoPOST( APIConfigSingleton.get().getUrlPreAprovacao(), RespostaPreAprovacao.class, preRequisicao );
	}
	
	/**
	 * Método de acesso HTTP POST.
	 * Passo inicial para o registro de uma assinatura com um pagamento inicial.
         * O PagSeguro somente retorna a pré aprovação com um código do cliente, e data.
         * 
	 * Nesta etapa, passamos todos os dados de registro da assinatura, com valores, período, etc. Nesta modalidade é possível passar uma data futura para início do pagamento. 
	 * Com o código retornado em mãos, podemos efetivar a autorização redirecionando para a página de pagamento.
	 * 
	 * @param preRequisicao EnvioPreRequisicaoCheckout com os dados da assinatura e do cliente.
	 * @return {@link br.com.jopss.pagseguro.assinaturas.modelos.RespostaPreAprovacaoCheckout} resposta com código do cliente e data, para iniciar fluxo de pagamento.
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ProblemaGenericoAPIException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ErrosRemotosPagSeguroException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException
	 */
	public RespostaPreAprovacaoCheckout preAprovacaoComCheckout(EnvioPreRequisicaoCheckout preRequisicao) throws ProblemaGenericoAPIException, ErrosRemotosPagSeguroException, ConfiguracaoInvalidaException, AutorizacaoInvalidaException {
		return new AcessoPagSeguro().acessoPOST( APIConfigSingleton.get().getUrlPreCheckout(), RespostaPreAprovacaoCheckout.class, preRequisicao );
	}
        
	/**
	 * Redireciona a requisição para o página de pagamento do PagSeguro, onde o usuário irá autenticar e autorizar a assinatura.
	 * A página de resposta após sucesso depende da configuração que foi utilizada na 'preAutorizacao'.
	 * 
	 * @param response HttpServletResponse do fluxo web para redirecionar ao PagSeguro.
	 * @param respostaPreRequisicao RespostaPreAprovacao com os dados da pré aprocação gerado anteriormente.
	 * @throws ConfiguracaoInvalidaException
	 * @throws ProblemaGenericoAPIException 
	 */
	public void redirecionarURLPagamento(HttpServletResponse response, RespostaPreAprovacao respostaPreRequisicao) throws ConfiguracaoInvalidaException, ProblemaGenericoAPIException{
		try {
			String urlWithSessionID = response.encodeRedirectURL(APIConfigSingleton.get().getUrlPagamento(respostaPreRequisicao.getCodigo()));
			response.sendRedirect( urlWithSessionID );
		} catch (IOException ex) {
			throw new ProblemaGenericoAPIException(ex);
		}
	}
        
        /**
	 * Redireciona a requisição para o página de pagamento do PagSeguro, onde o usuário irá autenticar e autorizar a assinatura.
	 * A página de resposta após sucesso depende da configuração que foi utilizada na 'preAutorizacao'.
	 * 
	 * @param response HttpServletResponse do fluxo web para redirecionar ao PagSeguro.
	 * @param respostaPreRequisicao RespostaPreAprovacaoCheckout com os dados da pré aprocação gerado anteriormente.
	 * @throws ConfiguracaoInvalidaException
	 * @throws ProblemaGenericoAPIException 
	 */
	public void redirecionarURLPagamento(HttpServletResponse response, RespostaPreAprovacaoCheckout respostaPreRequisicao) throws ConfiguracaoInvalidaException, ProblemaGenericoAPIException{
		try {
			String urlWithSessionID = response.encodeRedirectURL(APIConfigSingleton.get().getUrlPagamento(respostaPreRequisicao.getCodigo()));
			response.sendRedirect( urlWithSessionID );
		} catch (IOException ex) {
			throw new ProblemaGenericoAPIException(ex);
		}
	}
	
	/**
	 * Método de acesso HTTP POST.
	 * Efetiva uma cobraça para uma mensalidade.
	 * 
	 * @param envioCobranca EnvioCobranca com os dados da mensalidade a ser pago.
	 * @return {@link br.com.jopss.pagseguro.assinaturas.modelos.RespostaNotificacaoTransacao}.
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ProblemaGenericoAPIException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ErrosRemotosPagSeguroException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException
	 */
	public RespostaCobranca enviar(EnvioCobranca envioCobranca) throws ProblemaGenericoAPIException, ErrosRemotosPagSeguroException, ConfiguracaoInvalidaException, AutorizacaoInvalidaException {
		return new AcessoPagSeguro().acessoPOST( APIConfigSingleton.get().getUrlCobranca(), RespostaCobranca.class, envioCobranca );
	}
	
	/**
	 * Método de acesso HTTP GET.
	 * Efetua o cancelamento de uma assinatura no PagSeguro.
	 * 
	 * @param codigoMensalidade String com o número da assinatura a ser cancelada (preApprovalCode). Codigo remoto do PagSeguro.
	 * @return {@link br.com.jopss.pagseguro.assinaturas.modelos.RespostaNotificacaoAssinatura}.
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ProblemaGenericoAPIException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ErrosRemotosPagSeguroException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException
	 * @throws br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException
	 */
	public RespostaCancelamento cancelar(String codigoMensalidade) throws ProblemaGenericoAPIException, ErrosRemotosPagSeguroException, ConfiguracaoInvalidaException, AutorizacaoInvalidaException {
		return new AcessoPagSeguro().acessoGET( APIConfigSingleton.get().getUrlCancelamento(codigoMensalidade), RespostaCancelamento.class );
	}
	
}
