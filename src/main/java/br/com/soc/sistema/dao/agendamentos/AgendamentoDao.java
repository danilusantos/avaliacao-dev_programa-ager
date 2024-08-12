package br.com.soc.sistema.dao.agendamentos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.agendamento.AgendamentoVo;
import br.com.soc.sistema.vo.exame.ExameVo;
import br.com.soc.sistema.vo.funcionario.FuncionarioVo;

public class AgendamentoDao extends Dao {

	private String querySelectAll() {
		return "SELECT agendamento.rowid id, "
				+ "funcionario.rowid id_funcionario, funcionario.nm_funcionario nome_funcionario, "
				+ "exame.rowid id_exame, exame.nm_exame nome_exame, "
				+ "dt_agendamento data_agendamento " + "FROM agendamento "
				+ "JOIN funcionario ON agendamento.id_funcionario = funcionario.rowid "
				+ "JOIN exame ON agendamento.id_exame = exame.rowid ";
	}

	public void insertAgendamento(AgendamentoVo agendamentoVo) {

		String query = "INSERT INTO agendamento (id_funcionario, id_exame, dt_agendamento) VALUES (?, ?, ?)";

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query)) {
			int i = 1;
			ps.setInt(i++, Integer
					.parseInt(agendamentoVo.getFuncionario().getRowid()));
			ps.setInt(i++,
					Integer.parseInt(agendamentoVo.getExame().getRowid()));
			ps.setString(i++, agendamentoVo.getDataAgendamento());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateAgendamento(AgendamentoVo agendamentoVo) {
		if (existeAgendamentoDuplicado(agendamentoVo)) {
			throw new BusinessException(
					"Já existe um agendamento para este exame, funcionário e data.",
					null);
		}

		String query = "UPDATE agendamento SET " + "id_funcionario = ?, "
				+ "id_exame = ?, " + "dt_agendamento = ? " + "WHERE rowid = ?";

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query)) {
			int i = 1;
			ps.setString(i++, agendamentoVo.getFuncionario().getRowid());
			ps.setString(i++, agendamentoVo.getExame().getRowid());
			ps.setString(i++, agendamentoVo.getDataAgendamento());
			ps.setString(i++, agendamentoVo.getRowid());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<AgendamentoVo> findAllAgendamentos() {
		String query = querySelectAll() + ORDERBY;

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			List<AgendamentoVo> agendamentos = new ArrayList<>();

			while (rs.next()) {
				AgendamentoVo vo = new AgendamentoVo();
				vo.setRowid(rs.getString("id"));

				FuncionarioVo funcionario = new FuncionarioVo();
				funcionario.setRowid(rs.getString("id_funcionario"));
				funcionario.setNome(rs.getString("nome_funcionario"));
				vo.setFuncionario(funcionario);

				ExameVo exame = new ExameVo();
				exame.setRowid(rs.getString("id_exame"));
				exame.setNome(rs.getString("nome_exame"));
				vo.setExame(exame);

				vo.setDataAgendamento(rs.getString("data_agendamento"));

				agendamentos.add(vo);
			}
			return agendamentos;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public AgendamentoVo findByCodigo(Integer codigo) {
		String query = querySelectAll() + "WHERE agendamento.rowid = ? "
				+ ORDERBY;

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, codigo);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					AgendamentoVo vo = new AgendamentoVo();
					vo.setRowid(rs.getString("id"));

					FuncionarioVo funcionario = new FuncionarioVo();
					funcionario.setRowid(rs.getString("id_funcionario"));
					funcionario.setNome(rs.getString("nome_funcionario"));
					vo.setFuncionario(funcionario);

					ExameVo exame = new ExameVo();
					exame.setRowid(rs.getString("id_exame"));
					exame.setNome(rs.getString("nome_exame"));
					vo.setExame(exame);

					vo.setDataAgendamento(rs.getString("data_agendamento"));

					return vo;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<AgendamentoVo> findAllByRangeData(String dataInicio,
			String dataFim) {
		String query = querySelectAll() + "WHERE dt_agendamento "
				+ "BETWEEN ? AND ? "
				+ "ORDER BY dt_agendamento ASC;";

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, dataInicio);
			ps.setString(2, dataFim);

			try (ResultSet rs = ps.executeQuery()) {
				List<AgendamentoVo> agendamentos = new ArrayList<>();

				while (rs.next()) {
					AgendamentoVo vo = new AgendamentoVo();
					vo.setRowid(rs.getString("id"));

					FuncionarioVo funcionario = new FuncionarioVo();
					funcionario.setRowid(rs.getString("id_funcionario"));
					funcionario.setNome(rs.getString("nome_funcionario"));
					vo.setFuncionario(funcionario);

					ExameVo exame = new ExameVo();
					exame.setRowid(rs.getString("id_exame"));
					exame.setNome(rs.getString("nome_exame"));
					vo.setExame(exame);

					vo.setDataAgendamento(rs.getString("data_agendamento"));

					agendamentos.add(vo);
				}

				return agendamentos;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public List<AgendamentoVo> findAllByNome(String nome,
			String tabelaNomeCampoNome) {
		String query = querySelectAll() + "WHERE TRANSLATE(LOWER("
				+ tabelaNomeCampoNome + "), " + caracteresEspeciais() + ", "
				+ caracteresNormais() + ") " + "LIKE TRANSLATE(LOWER(?), "
				+ caracteresEspeciais() + ", " + caracteresNormais() + ") "
				+ ORDERBY;

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, "%" + nome + "%");

			try (ResultSet rs = ps.executeQuery()) {
				List<AgendamentoVo> agendamentos = new ArrayList<>();

				while (rs.next()) {
					AgendamentoVo vo = new AgendamentoVo();
					vo.setRowid(rs.getString("id"));

					FuncionarioVo funcionario = new FuncionarioVo();
					funcionario.setRowid(rs.getString("id_funcionario"));
					funcionario.setNome(rs.getString("nome_funcionario"));
					vo.setFuncionario(funcionario);

					ExameVo exame = new ExameVo();
					exame.setRowid(rs.getString("id_exame"));
					exame.setNome(rs.getString("nome_exame"));
					vo.setExame(exame);

					vo.setDataAgendamento(rs.getString("data_agendamento"));

					agendamentos.add(vo);
				}

				return agendamentos;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public void destroyAgendamento(Integer codigo) {
		String query = "DELETE FROM agendamento WHERE rowid = ?";

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, codigo);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean existeAgendamentoDuplicado(AgendamentoVo agendamentoVo) {
		String query = "SELECT COUNT(*) FROM agendamento "
				+ "WHERE id_funcionario = ? AND id_exame = ? AND dt_agendamento = ?";

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query)) {
			int i = 1;
			ps.setString(i++, agendamentoVo.getFuncionario().getRowid());
			ps.setString(i++, agendamentoVo.getExame().getRowid());
			ps.setString(i++, agendamentoVo.getDataAgendamento());

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean exameTemAgendamentos(Integer idExame) {
		String query = "SELECT COUNT(*) FROM agendamento WHERE id_exame = ?";

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, idExame);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
