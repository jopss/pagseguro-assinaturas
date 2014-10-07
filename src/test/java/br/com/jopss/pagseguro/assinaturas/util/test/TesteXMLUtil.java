package br.com.jopss.pagseguro.assinaturas.util.test;

import br.com.jopss.pagseguro.assinaturas.exception.ProblemaGenericoAPIException;
import br.com.jopss.pagseguro.assinaturas.modelos.ErrosPagSeguro;
import br.com.jopss.pagseguro.assinaturas.modelos.PreRequisicao;
import br.com.jopss.pagseguro.assinaturas.modelos.interfaces.RespostaPagseguro;
import br.com.jopss.pagseguro.assinaturas.modelos.suporte.PreAprovacao;
import br.com.jopss.pagseguro.assinaturas.modelos.suporte.enums.PeriodoPreAprovacao;
import br.com.jopss.pagseguro.assinaturas.util.XMLUtil;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Test;

public class TesteXMLUtil {

	@Test
	public void objetoParaXML() throws ProblemaGenericoAPIException{
		
		Date dataInicial = new Date();
		Date dataFinal = new Date();
		PreAprovacao preAprovacao = new PreAprovacao("TesteSL", PeriodoPreAprovacao.MENSAL, 50.0, dataInicial, dataFinal, 18762.55, 1998.11);
		
		PreRequisicao pre = new PreRequisicao(preAprovacao);
		pre.setIdReferenciaLocal("referencia123");
		pre.setUrlRedirecionamentoAposConfirmacao("http://www.cachorro.com.br");
		
		String xml = XMLUtil.objetoParaXML(pre);
		assertNotNull(xml);
	}
	
	@Test
	public void xmlParaObjeto() throws ProblemaGenericoAPIException{
		String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?><errors><error><code>11088</code><message>preApprovalName is required</message></error><error><code>11096</code><message>preApprovalFinalDate must be after preApprovalInitialDate.</message></error><error><code>11097</code><message>pre-approval total time less than minimum. Check parameter preApprovalFinalDate.</message></error></errors>";
		RespostaPagseguro resposta = XMLUtil.xmlParaObjeto(xml, ErrosPagSeguro.class);
		assertNotNull(resposta);
	}
}
