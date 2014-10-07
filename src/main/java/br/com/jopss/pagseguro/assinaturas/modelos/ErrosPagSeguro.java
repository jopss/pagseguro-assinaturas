package br.com.jopss.pagseguro.assinaturas.modelos;

import br.com.jopss.pagseguro.assinaturas.modelos.interfaces.RespostaPagseguro;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "errors")
public class ErrosPagSeguro implements RespostaPagseguro {
	
	@XmlElement(name = "error")
	private Set<Erro> erros = new HashSet<>();

	public Set<Erro> getErros() {
		return erros;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "error")
	public static class Erro{
		
		@XmlElement(name = "code")
		private Integer codigo;
		
		@XmlElement(name = "message")
		private String mensagem;
		
		public String getCodigoEMensagem(){
			return codigo+" - "+mensagem;
		}

		public Integer getCodigo() {
			return codigo;
		}

		public void setCodigo(Integer codigo) {
			this.codigo = codigo;
		}

		public String getMensagem() {
			return mensagem;
		}

		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}

		@Override
		public String toString() {
			return "Erro{" + "codigo=" + codigo + ", mensagem=" + mensagem + '}';
		}
	}

	@Override
	public String toString() {
		return "ErrosPagSeguro{" + "erros=" + erros + '}';
	}
	
}
