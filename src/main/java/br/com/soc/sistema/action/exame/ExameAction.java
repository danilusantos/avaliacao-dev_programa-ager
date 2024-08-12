package br.com.soc.sistema.action.exame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import br.com.soc.sistema.business.ExameBusiness;
import br.com.soc.sistema.filter.ExameFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.exame.OpcoesComboBuscarExames;
import br.com.soc.sistema.vo.exame.ExameVo;

@SuppressWarnings("serial")
public class ExameAction extends Action {
	private List<ExameVo> exames = new ArrayList<>();
	private ExameBusiness business = new ExameBusiness();
	private ExameFilter filtrar = new ExameFilter();
	private ExameVo exameVo = new ExameVo();

	public String todos() {
		exames.addAll(business.trazerTodosOsExames());
		return SUCCESS;
	}

	public String filtrar() {
		if (validateFiltro())
			return REDIRECT;

		exames = business.filtrarExames(filtrar);

		return SUCCESS;
	}
	
	private boolean validateFiltro() {
		return filtrar.isNullOpcoesCombo() 
				|| filtrar.getValorBusca().isEmpty()
				|| !Pattern.matches("\\d+", filtrar.getValorBusca()) 
					&& filtrar.getOpcoesCombo().toString() == "ID";
	}
	
	public String novo() {
		if (isInvalid(exameVo.getNome()))
			return INPUT;

		if (exameVo.getRowid() == null || exameVo.getRowid().isEmpty()) {
			business.salvarExame(exameVo);
		} else {
			business.atualizarExame(exameVo);
		}

		return REDIRECT;
	}

	public String editar() {
		if (exameVo.getRowid() == null)
			return REDIRECT;

		exameVo = business.buscarExamePor(exameVo.getRowid());

		return INPUT;
	}

	public String deletar() {
		if (exameVo.getRowid() == null)
			return REDIRECT;

		exameVo = business.buscarExamePor(exameVo.getRowid());

		business.deletarExame(exameVo);

		return REDIRECT;
	}

	public List<OpcoesComboBuscarExames> getListaOpcoesCombo() {
		return Arrays.asList(OpcoesComboBuscarExames.values());
	}

	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	public List<ExameVo> getExames() {
		return exames;
	}

	public void setExames(List<ExameVo> exames) {
		this.exames = exames;
	}

	public ExameFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(ExameFilter filtrar) {
		this.filtrar = filtrar;
	}

	public ExameVo getExameVo() {
		return exameVo;
	}

	public void setExameVo(ExameVo exameVo) {
		this.exameVo = exameVo;
	}
}
