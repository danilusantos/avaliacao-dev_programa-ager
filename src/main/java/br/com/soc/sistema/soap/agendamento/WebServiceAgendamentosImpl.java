package br.com.soc.sistema.soap.agendamento;

import javax.jws.WebService;

import br.com.soc.sistema.business.AgendamentoBusiness;

@WebService(endpointInterface = "br.com.soc.sistema.soap.agendamento.WebServiceAgendamentos" )
public class WebServiceAgendamentosImpl implements WebServiceAgendamentos {

	private AgendamentoBusiness business;
	
	public WebServiceAgendamentosImpl() {
		this.business = new AgendamentoBusiness();
	}
	
	@Override
	public String buscarAgendamento(String codigo) {		
		return business.buscarAgendamentoPor(codigo).toString();
	}
}
