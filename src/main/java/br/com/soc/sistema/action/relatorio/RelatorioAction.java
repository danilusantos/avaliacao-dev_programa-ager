package br.com.soc.sistema.action.relatorio;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.AgendamentoBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.service.ServiceRelatorios;
import br.com.soc.sistema.vo.agendamento.AgendamentoVo;

@SuppressWarnings("serial")
public class RelatorioAction extends Action {
	private String dataInicio;
	private String dataFim;
	private List<AgendamentoVo> agendamentos = new ArrayList<>();
	private AgendamentoBusiness agendamentoBusiness = new AgendamentoBusiness();
	private ServiceRelatorios service = new ServiceRelatorios();

	private boolean validateGerar() {
		return ! (getDataInicio() == null || getDataInicio().length() == 0
				|| getDataFim() == null || getDataFim().length() == 0);
	}

	public String gerar() {
		if (! validateGerar())
			return SUCCESS;
		try {
			agendamentos = agendamentoBusiness
					.filtrarAgendamentosPorDatas(dataInicio, dataFim);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return REDIRECT;
		}
	}

	public String baixarExcel() {
		try {
			service.gerarExcelRelatorios(dataInicio, dataFim);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return REDIRECT;
		}
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public List<AgendamentoVo> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<AgendamentoVo> agendamentos) {
		this.agendamentos = agendamentos;
	}
}
