package br.com.jopss.pagseguro.assinaturas.util;

import br.com.jopss.pagseguro.assinaturas.ConfiguracaoAPI;
import br.com.jopss.pagseguro.assinaturas.exception.ConfiguracaoInvalidaException;
import br.com.jopss.pagseguro.assinaturas.exception.ErrosRemotosPagSeguroException;
import br.com.jopss.pagseguro.assinaturas.exception.ProblemaGenericoAPIException;
import br.com.jopss.pagseguro.assinaturas.modelos.ErrosPagSeguro;
import br.com.jopss.pagseguro.assinaturas.modelos.interfaces.EnvioPagseguro;
import br.com.jopss.pagseguro.assinaturas.modelos.interfaces.RespostaPagseguro;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import org.apache.commons.lang.StringUtils;

/**
 * Classe utilitária para acessos HTTP (GET ou POST).
 * 
 * @author João Paulo Sossoloti.
 */
public final class AcessoPagSeguro {

	public <T> T acessoGET(String sUrl, Class<? extends RespostaPagseguro> clazz) throws ProblemaGenericoAPIException, ErrosRemotosPagSeguroException {
		return this.acessarURL(sUrl, null, clazz, false, null);
	}
	
	public <T> T acessoPOST(String sUrl, Class<? extends RespostaPagseguro> clazz, EnvioPagseguro envio) throws ProblemaGenericoAPIException, ErrosRemotosPagSeguroException {
		return this.acessarURL(sUrl, null, clazz, true, XMLUtil.objetoParaXML(envio));
	}

	private <T> T acessarURL(String sUrl, String requestQuery, Class<? extends RespostaPagseguro> clazz, boolean post, String xmlEnvio) throws ProblemaGenericoAPIException, ErrosRemotosPagSeguroException {
		try {
			URL url = new URL(sUrl + (StringUtils.isNotBlank(requestQuery) ? requestQuery : ""));

			HttpURLConnection conn = null;
			if(APIConfigSingleton.get().proxyConfigurado()){
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(APIConfigSingleton.get().getProxyURI(), APIConfigSingleton.get().getProxyPorta()));
				Authenticator authenticator = new Authenticator() {
					@Override
					public PasswordAuthentication getPasswordAuthentication() {
						return (new PasswordAuthentication(APIConfigSingleton.get().getProxyUsuario(),
							APIConfigSingleton.get().getProxySenha().toCharArray()));
					}
				};
				Authenticator.setDefault(authenticator);
				conn = (HttpURLConnection) url.openConnection(proxy);
			}else{
				conn = (HttpURLConnection) url.openConnection();
			}
			
			conn.setUseCaches(false);
			
			if(APIConfigSingleton.get().isTeste()){
				conn.setRequestProperty("access-control-allow-origin", "https://sandbox.pagseguro.uol.com.br");
			}

			OutputStreamWriter writer = null;
			if (post) {
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Type", "application/xml; charset=" + APIConfigSingleton.get().getCharset());
				conn.setRequestProperty("Content-Length", "" + Integer.toString(xmlEnvio.getBytes().length));
				conn.setRequestMethod("POST");

				writer = new OutputStreamWriter(conn.getOutputStream());
				writer.write(xmlEnvio);
				writer.flush();
			} else {
				conn.setRequestProperty("Content-Type", "application/xml; charset=" + APIConfigSingleton.get().getCharset());
			}

			BufferedReader bufferedreader = null;
			try {
				bufferedreader = new BufferedReader(new InputStreamReader(conn.getInputStream(), APIConfigSingleton.get().getCharset()));
			} catch (IOException e) {
				InputStream in = conn.getErrorStream();
				if (in == null) {
					throw new ProblemaGenericoAPIException("Sem acesso ao recurso remoto. O sistema por estar fora do ar. Verifique a URL ou tente novamente mais tarde. Tentativa em: '" + url + "'");
				}
				bufferedreader = new BufferedReader(new InputStreamReader(in, APIConfigSingleton.get().getCharset()));
			}

			String xmlResposta = "";
			String linha;
			while ((linha = bufferedreader.readLine()) != null) {
				xmlResposta += linha;
			}

			if (writer != null) {
				writer.close();
			}
			bufferedreader.close();

			if (StringUtils.isBlank(xmlResposta)) {
				throw new ProblemaGenericoAPIException("Sem acesso ao PagSeguro. O sistema por estar fora do ar. Verifique a URL ou tente novamente mais tarde. Tentativa em: '" + url + "'");
			}
			
			RespostaPagseguro resp = null;
			if(xmlResposta.contains("<errors>")){
				throw new ErrosRemotosPagSeguroException( (ErrosPagSeguro) XMLUtil.xmlParaObjeto(xmlResposta, ErrosPagSeguro.class));
			}else if(xmlResposta.charAt(0) != '<'){
				throw new ProblemaGenericoAPIException(xmlResposta);
			}else{
				resp = XMLUtil.xmlParaObjeto(xmlResposta, clazz);
			}
			
			return (T) resp;

		} catch (ErrosRemotosPagSeguroException | ProblemaGenericoAPIException ex) {
			throw ex;
		} catch (ConfiguracaoInvalidaException | IOException ex) {
			throw new ProblemaGenericoAPIException(ex);
		}
	}

}
