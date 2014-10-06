package br.com.jopss.pagseguro.assinaturas.util;

import br.com.jopss.pagseguro.assinaturas.exception.ErrosRemotosPagSeguroException;
import br.com.jopss.pagseguro.assinaturas.exception.ProblemaGenericoAPIException;
import br.com.jopss.pagseguro.assinaturas.modelos.ErrosPagSeguro;
import br.com.jopss.pagseguro.assinaturas.modelos.interfaces.EnvioPagseguro;
import br.com.jopss.pagseguro.assinaturas.modelos.interfaces.RespostaPagseguro;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

public final class AcessoAPI {

	public <T> T acessoPOST(String sUrl, Class<? extends RespostaPagseguro> clazz, EnvioPagseguro envio) throws ProblemaGenericoAPIException, ErrosRemotosPagSeguroException {
		return this.acessarURL(sUrl, null, clazz, true, XMLUtil.objetoParaXML(envio));
	}

	private <T> T acessarURL(String sUrl, String requestQuery, Class<? extends RespostaPagseguro> clazz, boolean post, String xmlEnvio) throws ProblemaGenericoAPIException, ErrosRemotosPagSeguroException {
		try {
			URL url = new URL(sUrl + (StringUtils.isNotBlank(requestQuery) ? requestQuery : ""));

			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cache.bb.com.br", 80));
			Authenticator authenticator = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return (new PasswordAuthentication("c1276009","34742132".toCharArray()));
				}
			};
			Authenticator.setDefault(authenticator);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);
			conn.setUseCaches(false);
			conn.usingProxy();

//			if(sUrl.startsWith("https")){
//				HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
//				
//				InputStream fileKeyStore = this.retornarArquivoCertificado();
//				KeyStore keyStore = KeyStore.getInstance("JKS");
//				keyStore.load(fileKeyStore, null);
//				fileKeyStore.close();
//				
//				TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//				tmf.init(keyStore);
//				
//				SSLContext ctx = SSLContext.getInstance("TLS");
//				ctx.init(null, tmf.getTrustManagers(), null);
//				SSLSocketFactory sslFactory = ctx.getSocketFactory();
//				httpsConn.setSSLSocketFactory(sslFactory);
//				
//				conn = httpsConn;
//			}else{
//				conn = (HttpURLConnection) url.openConnection();
//			}
			OutputStreamWriter writer = null;
			if (post) {
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Type", "application/xml; charset=" + ParametrosAPI.getURLEncoding());
				conn.setRequestProperty("Content-Length", "" + Integer.toString(xmlEnvio.getBytes().length));
				conn.setRequestMethod("POST");

				writer = new OutputStreamWriter(conn.getOutputStream());
				writer.write(xmlEnvio);
				writer.flush();
			} else {
				conn.setRequestProperty("Content-Type", "application/xml; charset=" + ParametrosAPI.getURLEncoding());
			}

			BufferedReader bufferedreader = null;
			try {
				bufferedreader = new BufferedReader(new InputStreamReader(conn.getInputStream(), ParametrosAPI.getURLEncoding()));
			} catch (IOException e) {
				InputStream in = conn.getErrorStream();
				if (in == null) {
					throw new ProblemaGenericoAPIException("Sem acesso ao recurso remoto. O sistema por estar fora do ar. Verifique a URL ou tente novamente mais tarde. Tentativa em: '" + url + "'");
				}
				bufferedreader = new BufferedReader(new InputStreamReader(in, ParametrosAPI.getURLEncoding()));
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
			}else{
				resp = XMLUtil.xmlParaObjeto(xmlResposta, clazz);
			}
			
			return (T) resp;

		} catch (Exception ex) {
			throw new ProblemaGenericoAPIException(ex);
		}
	}

	private InputStream retornarArquivoCertificado() throws FileNotFoundException {
		return ParametrosAPI.class.getResourceAsStream("/pagseguro_ws_keystore");
	}

}
