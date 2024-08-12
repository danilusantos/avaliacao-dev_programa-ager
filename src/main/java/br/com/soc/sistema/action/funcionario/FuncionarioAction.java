package br.com.soc.sistema.action.funcionario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.funcionario.OpcoesComboBuscarFuncionarios;
import br.com.soc.sistema.vo.funcionario.FuncionarioVo;

@SuppressWarnings("serial")
public class FuncionarioAction extends Action {
	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	private FuncionarioBusiness business = new FuncionarioBusiness();
	private FuncionarioFilter filtrar = new FuncionarioFilter();
	private FuncionarioVo funcionarioVo = new FuncionarioVo();

	public String todos() {
		funcionarios.addAll(business.trazerTodosOsFuncionarios());

		return SUCCESS;
	}

	public String filtrar() {
		if (validateFiltro())
			return REDIRECT;

		funcionarios = business.filtrarFuncionarios(filtrar);

		return SUCCESS;
	}
	
	private boolean validateFiltro() {
		return filtrar.isNullOpcoesCombo() 
				|| filtrar.getValorBusca().isEmpty()
				|| !Pattern.matches("\\d+", filtrar.getValorBusca()) 
					&& filtrar.getOpcoesCombo().toString() == "ID";
	}

	public String novo() {
		if (isInvalid(funcionarioVo.getNome()))
			return INPUT;

		if (funcionarioVo.getRowid() == null
				|| funcionarioVo.getRowid().isEmpty()) {
			business.salvarFuncionario(funcionarioVo);
		} else {
			business.atualizarFuncionario(funcionarioVo);
		}

		return REDIRECT;
	}

	public String editar() {
		if (funcionarioVo.getRowid() == null)
			return REDIRECT;

		funcionarioVo = business.buscarFuncionarioPor(funcionarioVo.getRowid());

		return INPUT;
	}

	public String deletar() {
		if (funcionarioVo.getRowid() == null)
			return REDIRECT;

		funcionarioVo = business.buscarFuncionarioPor(funcionarioVo.getRowid());

		business.deletarFuncionario(funcionarioVo);

		return REDIRECT;
	}

	public List<OpcoesComboBuscarFuncionarios> getListaOpcoesCombo() {
		return Arrays.asList(OpcoesComboBuscarFuncionarios.values());
	}

	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	public List<FuncionarioVo> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioVo> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public FuncionarioFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(FuncionarioFilter filtrar) {
		this.filtrar = filtrar;
	}

	public FuncionarioVo getFuncionarioVo() {
		return funcionarioVo;
	}

	public void setFuncionarioVo(FuncionarioVo funcionarioVo) {
		this.funcionarioVo = funcionarioVo;
	}
}
