package br.com.jopss.pagseguro.assinaturas.util.test;

import br.com.jopss.pagseguro.assinaturas.PagSeguroAPI;
import br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException;
import br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException;
import br.com.jopss.pagseguro.assinaturas.exception.ErrosRemotosPagSeguroException;
import br.com.jopss.pagseguro.assinaturas.exception.ProblemaGenericoAPIException;
import br.com.jopss.pagseguro.assinaturas.modelos.EnvioPreRequisicao;
import br.com.jopss.pagseguro.assinaturas.modelos.RespostaPreAprovacao;
import br.com.jopss.pagseguro.assinaturas.modelos.suporte.PreAprovacao;
import br.com.jopss.pagseguro.assinaturas.modelos.suporte.enums.PeriodoPreAprovacao;
import java.util.Date;
import org.joda.time.DateTime;
import static org.junit.Assert.*;

public class TestePreAutorizacao {

	private String email = "jopss@ideebox.com.br";
	private String token = "FCB44BE80B7A4E178EC5D55BC6D7DC91";
	
	//@Test
	public void preAprovacao() throws ProblemaGenericoAPIException, AutorizacaoInvalidaException, ConfiguracaoInvalidaException{
		
		try {
			Date dataInicial = new Date();
			Date dataFinal = new DateTime().plusMonths(1).toDate();
			PreAprovacao preAprovacao = new PreAprovacao("TesteSL", PeriodoPreAprovacao.MENSAL, 50.0, dataInicial, dataFinal, 18762.55);
			preAprovacao.setValorLimiteMensal(1998.11);
			
			EnvioPreRequisicao pre = new EnvioPreRequisicao(preAprovacao);
			pre.setIdReferenciaLocal("referencia123");
			pre.setUrlRedirecionamentoAposConfirmacao("http://www.cachorro.com.br");
			
			PagSeguroAPI.instance().config().setEmail(email).setToken(token).setProxyURI("cache.bb.com.br").setProxyPorta(80).setProxyUsuario("c1276009").setProxySenha("34742132").envioAmbienteTestes();
			RespostaPreAprovacao response = PagSeguroAPI.instance().assinatura().preAprovacao(pre);
			assertNotNull(response);
			
		} catch (ErrosRemotosPagSeguroException ex) {
			ex.printStackTrace();
			fail();
		}
	}
	
}
