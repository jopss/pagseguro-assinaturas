package br.com.jopss.pagseguro.assinaturas.requisicao;

import br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException;
import br.com.jopss.pagseguro.assinaturas.exception.ErrosRemotosPagSeguroException;
import br.com.jopss.pagseguro.assinaturas.exception.ProblemaGenericoAPIException;
import br.com.jopss.pagseguro.assinaturas.modelos.PreRequisicao;
import br.com.jopss.pagseguro.assinaturas.modelos.RespostaPreRequisicao;
import br.com.jopss.pagseguro.assinaturas.util.AcessoAPI;
import br.com.jopss.pagseguro.assinaturas.util.ParametrosAPI;
import java.util.Set;

public final class RequisicaoAssinatura {
	
	/**
	 * Método de acesso HTTP POST.
	 * Retorna uma lista de situações ativas relacionadas com as reclamações.
	 * @return {@link Set}&lt;{@link br.gov.consumidor.consumidor.api.modelos.CadastrosBasicos}&gt; lista dos dados, com código e descrição.
	 * @throws AutorizacaoInvalidaException, ProblemaGenericoAPIException.
	 */
	public RespostaPreRequisicao preAprovacao(PreRequisicao preRequisicao) throws AutorizacaoInvalidaException, ProblemaGenericoAPIException, ErrosRemotosPagSeguroException{
		return new AcessoAPI().acessoPOST( ParametrosAPI.getURLPreAprovacao(), RespostaPreRequisicao.class, preRequisicao );
	}
	
}
