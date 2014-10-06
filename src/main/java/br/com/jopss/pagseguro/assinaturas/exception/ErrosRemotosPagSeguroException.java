package br.com.jopss.pagseguro.assinaturas.exception;

import br.com.jopss.pagseguro.assinaturas.modelos.ErrosPagSeguro;

public class ErrosRemotosPagSeguroException extends Exception {

	private ErrosPagSeguro errosPagSeguro;
	
	public ErrosRemotosPagSeguroException(ErrosPagSeguro errosPagSeguro) {
		super(errosPagSeguro.toString());
		this.errosPagSeguro = errosPagSeguro;
	}

	public boolean contemErros(){
		if(errosPagSeguro !=null){
			return true;
		}
		return false;
	}
	
	public ErrosPagSeguro getErrosPagSeguro() {
		return errosPagSeguro;
	}
	
}
