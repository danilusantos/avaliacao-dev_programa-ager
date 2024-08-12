package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import br.com.soc.sistema.dao.exames.ExameDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.ExameFilter;
import br.com.soc.sistema.vo.exame.ExameVo;

public class ExameBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private ExameDao dao;

	public ExameBusiness() {
		this.dao = new ExameDao();
	}

	public List<ExameVo> trazerTodosOsExames() {
		return dao.findAllExames();
	}

	public void salvarExame(ExameVo exameVo) {
		try {
			if (exameVo.getNome().isEmpty())
				throw new IllegalArgumentException(
						"Nome nao pode ser em branco");

			dao.insertExame(exameVo);
		} catch (BusinessException e) {
			ActionContext.getContext().getSession().put("errorMessage", e.getMessage());
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro", e);
		}

	}

	public List<ExameVo> filtrarExames(ExameFilter filter) {
		List<ExameVo> exames = new ArrayList<>();

		switch (filter.getOpcoesCombo()) {
			case ID :
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					exames.add(dao.findByCodigo(codigo));
				} catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO, e);
				}
				break;

			case NOME :
				exames.addAll(dao.findAllByNome(filter.getValorBusca()));
				break;
		}

		return exames;
	}

	public ExameVo buscarExamePor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		} catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO, e);
		}
	}

	public void atualizarExame(ExameVo exameVo) {
		try {
			if (exameVo.getNome().isEmpty())
				throw new IllegalArgumentException(
						"Nome nao pode ser em branco");

			dao.updateExame(exameVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a edicao do registro", e);
		}
	}

	public void deletarExame(ExameVo exameVo) {
		try {
			if (!dao.podeDeletarExame(Integer.parseInt(exameVo.getRowid()))) {
				throw new BusinessException("O exame não pode ser deletado, pois está associado a um ou mais funcionários.", null);
			}
			dao.destroyExame(exameVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a exclusao do registro", e);
		}
	}
}
