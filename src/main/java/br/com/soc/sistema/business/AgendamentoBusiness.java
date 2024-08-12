package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.agendamentos.AgendamentoDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.AgendamentoFilter;
import br.com.soc.sistema.vo.agendamento.AgendamentoVo;

public class AgendamentoBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um número.";
	private AgendamentoDao agendamentoDao;

	public AgendamentoBusiness() {
		this.agendamentoDao = new AgendamentoDao();
	}

	public List<AgendamentoVo> trazerTodosOsAgendamentos() {
		return agendamentoDao.findAllAgendamentos();
	}

	public void salvarAgendamento(AgendamentoVo agendamentoVo) {
		try {
			if (agendamentoVo.getFuncionario().getRowid() == null
					|| agendamentoVo.getExame().getRowid() == null
					|| agendamentoVo.getDataAgendamento().isEmpty()) {
				throw new BusinessException(
						"Funcionário, Exame, e Data de Agendamento não podem ser em branco.",
						null);
			}

			if (agendamentoDao.existeAgendamentoDuplicado(agendamentoVo)) {
				throw new BusinessException(
						"Este exame já foi agendado para o funcionário nesta data.",
						null);
			}

			agendamentoDao.insertAgendamento(agendamentoVo);
		} catch (Exception e) {
			throw new BusinessException(
					"Não foi possível realizar a inclusão do registro.", e);
		}
	}

	public List<AgendamentoVo> filtrarAgendamentos(AgendamentoFilter filter) {
		List<AgendamentoVo> agendamentos = new ArrayList<>();
		switch (filter.getOpcoesCombo()) {
			case ID :
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					AgendamentoVo agendamento = agendamentoDao
							.findByCodigo(codigo);
					if (agendamento != null) {
						agendamentos.add(agendamento);
					}
				} catch (NumberFormatException e) {
					throw new BusinessException(
							FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO, e);
				}
				break;

			case NOME_FUNCIONARIO :
				agendamentos.addAll(agendamentoDao.findAllByNome(
						filter.getValorBusca(), "funcionario.nm_funcionario"));
				break;

			case NOME_EXAME :
				agendamentos.addAll(agendamentoDao.findAllByNome(
						filter.getValorBusca(), "exame.nm_exame"));
				break;

			default :
				throw new BusinessException("Filtro de busca inválido.", null);
		}

		return agendamentos;
	}
	
	public List<AgendamentoVo> filtrarAgendamentosPorDatas(String dataInicio, String dataFim) {
	    return agendamentoDao.findAllByRangeData(dataInicio, dataFim);
	}

	public AgendamentoVo buscarAgendamentoPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return agendamentoDao.findByCodigo(cod);
		} catch (NumberFormatException e) {
			throw new BusinessException(
					FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO, e);
		}
	}

	public void atualizarAgendamento(AgendamentoVo agendamentoVo) {
		try {
			if (agendamentoDao.existeAgendamentoDuplicado(agendamentoVo)) {
				throw new BusinessException("Já existe um agendamento para este exame, funcionário e data.", null);
			}

			agendamentoDao.updateAgendamento(agendamentoVo);
		} catch (Exception e) {
			throw new BusinessException(
					"Não foi possível realizar a edição do registro.", e);
		}
	}

	public void deletarAgendamento(AgendamentoVo agendamentoVo) {
		Integer idAgendamento = Integer.parseInt(agendamentoVo.getRowid());
		try {
			agendamentoDao.destroyAgendamento(idAgendamento);
		} catch (Exception e) {
			throw new BusinessException(
					"Não foi possível realizar a exclusão do registro.", e);
		}
	}
}
