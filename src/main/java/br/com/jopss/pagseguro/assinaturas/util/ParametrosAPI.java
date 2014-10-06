package br.com.jopss.pagseguro.assinaturas.util;

import br.com.jopss.pagseguro.assinaturas.exception.AutorizacaoInvalidaException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public final class ParametrosAPI {
	private static final Logger log = Logger.getLogger(ParametrosAPI.class);
	private static final Properties prop = new Properties();
	
	private ParametrosAPI(){}
	
	static{
		InputStream input = null;
		try {
			input = ParametrosAPI.class.getResourceAsStream("/pagseguro-parametros.properties");
			prop.load(input);
		} catch (FileNotFoundException ex) {
			log.error(ex);
		} catch (IOException ex) {
			log.fatal(ex);
		} finally {
			try {
				input.close();
			} catch (IOException ex) {
				log.fatal(ex);
			}
		}
	}
	
	public static String getURLEncoding() {
		return prop.getProperty("url.charset");
	}
	
	public static String getURLPreAprovacao() throws AutorizacaoInvalidaException{
		String url = prop.getProperty("url.pre.aprovacao");
		url = url.replaceAll("\\{email\\}", APIConfigSingleton.get().getEmail());
		url = url.replaceAll("\\{token\\}", APIConfigSingleton.get().getToken());
		if(APIConfigSingleton.get().isTeste()){
			url = url.replaceAll("\\{teste\\}", "sandbox.");
		}
		else{
			url = url.replaceAll("\\{teste\\}", "");
		}
		return url;
	}
	
}
