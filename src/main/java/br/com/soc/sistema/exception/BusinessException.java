package br.com.soc.sistema.exception;

@SuppressWarnings("serial")
public class BusinessException extends RuntimeException{
	public BusinessException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
