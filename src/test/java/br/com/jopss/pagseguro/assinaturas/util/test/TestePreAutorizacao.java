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

	private String email = "jopss@supralegal.com.br";
	private String token = "FCB44BE80B7A4E178EC5D55BC6D7DC91";
	
	//@Test
	public void preAprovacao() throws ProblemaGenericoAPIException, AutorizacaoInvalidaException, ConfiguracaoInvalidaException{
		
		try {
			Date dataInicial = new Date();
			Date dataFinal = new DateTime().plusMonths(1).toDate();
                        
			PreAprovacao pre = new PreAprovacao("TesteSL", PeriodoPreAprovacao.MENSAL, 50.0, dataInicial, dataFinal, 18762.55);
			pre.setValorLimiteMensal(1998.11);
			
			EnvioPreRequisicao envio = new EnvioPreRequisicao(pre);
			envio.setIdReferenciaLocal("referencia123");
			envio.setUrlRedirecionamentoAposConfirmacao("http://www.cachorro.com.br");
			
			PagSeguroAPI.instance().config().setEmail(email).setToken(token).setProxyURI("127.0.0.1").setProxyPorta(3128).indicaAmbienteTestes();
			RespostaPreAprovacao response = PagSeguroAPI.instance().assinatura().preAprovacao(envio);
			assertNotNull(response);
			
		} catch (ErrosRemotosPagSeguroException ex) {
			ex.printStackTrace();
			fail();
		}
	}
	
}
