package br.com.soc.sistema.soap.agendamento;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WebServiceAgendamentos {
	@WebMethod
	public String buscarAgendamento(String codigo);
	
}
