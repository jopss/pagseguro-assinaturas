package br.com.jopss.pagseguro.assinaturas.util;

import br.com.jopss.pagseguro.assinaturas.exception.ProblemaGenericoAPIException;
import br.com.jopss.pagseguro.assinaturas.modelos.interfaces.EnvioPagseguro;
import br.com.jopss.pagseguro.assinaturas.modelos.interfaces.RespostaPagseguro;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Classe utilitária para parsear Objetos e XML.
 * 
 * @author João Paulo Sossoloti.
 */
public class XMLUtil {

	/**
	 * Método usado quando o PagSeguro retorna um XML e devemos converter para Objetos.
	 * 
	 * @param xml String
	 * @param clazz Class<? extends RespostaPagseguro>
	 * @return RespostaPagseguro
	 * @throws ProblemaGenericoAPIException 
	 */
	public static RespostaPagseguro xmlParaObjeto(String xml, Class<? extends RespostaPagseguro> clazz) throws ProblemaGenericoAPIException {
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller un = context.createUnmarshaller();
			return (RespostaPagseguro) un.unmarshal(new StringReader(xml));
		} catch (JAXBException e) {
			throw new ProblemaGenericoAPIException(e);
		}
	}

	/**
	 * Método usado quando queremos converter um Objeto para XML a ser enviado ao PagSeguro.
	 * 
	 * @param envio EnvioPagseguro.
	 * @return String com o XML.
	 * @throws ProblemaGenericoAPIException 
	 */
	public static String objetoParaXML(final EnvioPagseguro envio) throws ProblemaGenericoAPIException {

		try {
			StringWriter str = new StringWriter();
			
			JAXBContext context = JAXBContext.newInstance(envio.getClass());
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(envio, str);
			
			return str.toString();
		} catch (JAXBException e) {
			throw new ProblemaGenericoAPIException(e);
		}
	}

}
