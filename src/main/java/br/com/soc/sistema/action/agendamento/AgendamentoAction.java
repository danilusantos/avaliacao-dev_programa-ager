package br.com.soc.sistema.action.agendamento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import br.com.soc.sistema.business.AgendamentoBusiness;
import br.com.soc.sistema.business.ExameBusiness;
import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.filter.AgendamentoFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.agendamento.OpcoesComboBuscarAgendamentos;
import br.com.soc.sistema.vo.agendamento.AgendamentoVo;
import br.com.soc.sistema.vo.exame.ExameVo;
import br.com.soc.sistema.vo.funcionario.FuncionarioVo;

@SuppressWarnings("serial")
public class AgendamentoAction extends Action {
	private List<AgendamentoVo> agendamentos = new ArrayList<>();
	private List<ExameVo> exames = new ArrayList<>();
	private List<FuncionarioVo> funcionarios  = new ArrayList<>();
	private AgendamentoBusiness agendamentoBusiness = new AgendamentoBusiness();
	private ExameBusiness exameBusiness = new ExameBusiness();
	private FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
	private AgendamentoFilter filtrar = new AgendamentoFilter();
	private AgendamentoVo agendamentoVo = new AgendamentoVo();

	public String todos() {
		agendamentos.addAll(agendamentoBusiness.trazerTodosOsAgendamentos());
		return SUCCESS;
	}

	public String filtrar() {
		if (validateFiltro())
			return REDIRECT;

		agendamentos = agendamentoBusiness.filtrarAgendamentos(filtrar);

		return SUCCESS;
	}
	
	private boolean validateFiltro() {
		return filtrar.isNullOpcoesCombo() 
				|| filtrar.getValorBusca().isEmpty()
				|| !Pattern.matches("\\d+", filtrar.getValorBusca()) 
					&& filtrar.getOpcoesCombo().toString() == "ID";
	}

	public String novo() {
		if (agendamentoVo.getExame() == null && agendamentoVo.getFuncionario() == null) {
			exames.addAll(exameBusiness.trazerTodosOsExames());
			funcionarios.addAll(funcionarioBusiness.trazerTodosOsFuncionarios());
			return INPUT;
		}

		if (isInvalid(agendamentoVo.getRowid())) {
			agendamentoBusiness.salvarAgendamento(agendamentoVo);
		} else {
			agendamentoBusiness.atualizarAgendamento(agendamentoVo);
		}

		return REDIRECT;
	}

	public String editar() {
		agendamentoVo = agendamentoBusiness.buscarAgendamentoPor(agendamentoVo.getRowid());
		
		if (agendamentoVo.getRowid() == null)
			return REDIRECT;
		
		if (agendamentoVo.getFuncionario() == null
				|| agendamentoVo.getExame() == null
				|| agendamentoVo.getDataAgendamento() == null)
			return REDIRECT;

		exames.addAll(exameBusiness.trazerTodosOsExames());
		funcionarios.addAll(funcionarioBusiness.trazerTodosOsFuncionarios());
		
		return INPUT;
	}

	public String deletar() {
		if (agendamentoVo.getRowid() == null)
			return REDIRECT;

		agendamentoVo = agendamentoBusiness.buscarAgendamentoPor(agendamentoVo.getRowid());

		agendamentoBusiness.deletarAgendamento(agendamentoVo);

		return REDIRECT;
	}
	
	public List<OpcoesComboBuscarAgendamentos> getListaOpcoesCombo() {
		return Arrays.asList(OpcoesComboBuscarAgendamentos.values());
	}

	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	public List<AgendamentoVo> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<AgendamentoVo> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public AgendamentoFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(AgendamentoFilter filtrar) {
		this.filtrar = filtrar;
	}

	public AgendamentoVo getAgendamentoVo() {
		return agendamentoVo;
	}

	public void setAgendamentoVo(AgendamentoVo agendamentoVo) {
		this.agendamentoVo = agendamentoVo;
	}
	
	public List<ExameVo> getExames() {
	    return exames;
	}

	public List<FuncionarioVo> getFuncionarios() {
	    return funcionarios;
	}
}
