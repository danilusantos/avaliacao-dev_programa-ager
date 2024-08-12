package br.com.soc.sistema.vo.agendamento;

import br.com.soc.sistema.vo.exame.ExameVo;
import br.com.soc.sistema.vo.funcionario.FuncionarioVo;

public class AgendamentoVo {

	private String rowid;
	private FuncionarioVo funcionario;
	private ExameVo exame;
	private String dataAgendamento;

	public String getRowid() {
		return rowid;
	}

	public void setRowid(String rowid) {
		this.rowid = rowid;
	}

	public FuncionarioVo getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioVo funcionario) {
		this.funcionario = funcionario;
	}

	public ExameVo getExame() {
		return exame;
	}

	public void setExame(ExameVo exame) {
		this.exame = exame;
	}

	public String getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	@Override
	public String toString() {
		return "AgendamentoVo [rowid=" + rowid + ", exameId=" + exame.getRowid()
				+ ", funcionarioId=" + funcionario.getRowid()
				+ ", dataAgendamento=" + dataAgendamento.toString() + "]";
	}
}