package br.com.jopss.pagseguro.assinaturas.util.test;

import br.com.jopss.pagseguro.assinaturas.PagSeguroAPI;
import br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException;
import br.com.jopss.pagseguro.assinaturas.exception.ErrosRemotosPagSeguroException;
import br.com.jopss.pagseguro.assinaturas.exception.ProblemaGenericoAPIException;
import br.com.jopss.pagseguro.assinaturas.modelos.PreRequisicao;
import br.com.jopss.pagseguro.assinaturas.modelos.RespostaPreRequisicao;
import br.com.jopss.pagseguro.assinaturas.modelos.suporte.PreAprovacao;
import br.com.jopss.pagseguro.assinaturas.modelos.suporte.enums.PeriodoPreAprovacao;
import java.util.Date;
import org.joda.time.DateTime;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestePreAutorizacao {

	private String email = "jopss@ideebox.com.br";
	private String token = "FCB44BE80B7A4E178EC5D55BC6D7DC91";
	
	@Test
	public void objetoParaXML() throws ProblemaGenericoAPIException, AutorizacaoInvalidaException{
		
		try {
			Date dataInicial = new Date();
			Date dataFinal = new DateTime().plusMonths(1).toDate();
			PreAprovacao preAprovacao = new PreAprovacao("TesteSL", PeriodoPreAprovacao.MENSAL, dataInicial, dataFinal, 18762.55, 1998.11);
			
			PreRequisicao pre = new PreRequisicao(preAprovacao);
			pre.setIdReferenciaLocal("referencia123");
			pre.setUrlRedirecionamentoAposConfirmacao("http://www.cachorro.com.br");
			
			PagSeguroAPI.instance().config().setEmail(email).setToken(token).envioComoTeste();
			RespostaPreRequisicao response = PagSeguroAPI.instance().assinatura().preAprovacao(pre);
			assertNotNull(response);
			
		} catch (ErrosRemotosPagSeguroException ex) {
			ex.printStackTrace();
			fail();
		}
	}
	
}
