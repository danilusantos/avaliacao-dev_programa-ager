package br.com.soc.sistema.soap.exame;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WebServiceExames {
	@WebMethod
	public String buscarExame(String codigo);
	
}
