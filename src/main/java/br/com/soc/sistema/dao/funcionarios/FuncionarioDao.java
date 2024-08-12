package br.com.soc.sistema.dao.funcionarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.funcionario.FuncionarioVo;

public class FuncionarioDao extends Dao {

	public void insertFuncionario(FuncionarioVo funcionarioVo) {
		StringBuilder query = new StringBuilder(
				"INSERT INTO funcionario (nm_funcionario) values (?)");
		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;
			ps.setString(i++, funcionarioVo.getNome());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<FuncionarioVo> findAllFuncionarios() {
		String query = "SELECT rowid id, nm_funcionario nome FROM funcionario" + ORDERBY;
		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString());
				ResultSet rs = ps.executeQuery()) {

			FuncionarioVo vo = null;
			List<FuncionarioVo> funcionarios = new ArrayList<>();
			while (rs.next()) {
				vo = new FuncionarioVo();
				vo.setRowid(rs.getString("id"));
				vo.setNome(rs.getString("nome"));

				funcionarios.add(vo);
			}
			return funcionarios;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public List<FuncionarioVo> findAllByNome(String nome) {
		String sql = "SELECT rowid id, nm_funcionario nome FROM funcionario "
		           + "WHERE TRANSLATE(LOWER(nm_funcionario), "
		           + caracteresEspeciais() + ", "
		           + caracteresNormais() + ") "
		           + "LIKE TRANSLATE(LOWER(?), "
		           + caracteresEspeciais() + ", "
		           + caracteresNormais() + ") "
		           + ORDERBY;

		StringBuilder query = new StringBuilder(sql);

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;

			ps.setString(i, "%" + nome + "%");

			try (ResultSet rs = ps.executeQuery()) {
				FuncionarioVo vo = null;
				List<FuncionarioVo> funcionarios = new ArrayList<>();

				while (rs.next()) {
					vo = new FuncionarioVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));

					funcionarios.add(vo);
				}
				return funcionarios;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public FuncionarioVo findByCodigo(Integer codigo) {
		StringBuilder query = new StringBuilder(
				"SELECT rowid id, nm_funcionario nome FROM funcionario ")
						.append("WHERE rowid = ?");

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;

			ps.setInt(i, codigo);

			try (ResultSet rs = ps.executeQuery()) {
				FuncionarioVo vo = null;

				while (rs.next()) {
					vo = new FuncionarioVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));
				}
				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateFuncionario(FuncionarioVo funcionarioVo) {
		StringBuilder query = new StringBuilder(
				"UPDATE funcionario SET nm_funcionario = ? WHERE rowid = ?;");

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;
			ps.setString(i++, funcionarioVo.getNome());
			ps.setString(i++, funcionarioVo.getRowid());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroyFuncionario(FuncionarioVo funcionarioVo) {
		StringBuilder query = new StringBuilder(
				"DELETE FROM funcionario WHERE rowid=?");
		try (Connection con = getConexao();
				PreparedStatement ps = con
						.prepareStatement(query.toString());) {
			int i = 1;
			ps.setString(i++, funcionarioVo.getRowid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void destroyAgendamentoByFuncionario(Integer idFuncionario) {
        String query = "DELETE FROM agendamento WHERE id_funcionario = ?";
        
        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setInt(1, idFuncionario);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void destroyFuncionario(Integer idFuncionario) {
        String query = "DELETE FROM funcionario WHERE rowid = ?";
        
        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setInt(1, idFuncionario);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean hasAgendamentosFuncionario(Integer idFuncionario) {
        return !(countAgendamentosFuncionario(idFuncionario) == 0);
    }

    public int countAgendamentosFuncionario(Integer idFuncionario) {
        String query = "SELECT COUNT(*) FROM agendamento WHERE id_funcionario = ?";
        
        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setInt(1, idFuncionario);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Integer> findAgendamentosFuncionario(Integer idFuncionario) {
        String query = "SELECT rowid id FROM agendamento WHERE id_funcionario = ?";
        List<Integer> agendamentos = new ArrayList<>();
        
        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setInt(1, idFuncionario);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    agendamentos.add(rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agendamentos;
    }

    public void destroyAgendamentosFuncionario(Integer idFuncionario) {
        List<Integer> agendamentos = findAgendamentosFuncionario(idFuncionario);
        
        if (!agendamentos.isEmpty()) {
            for (Integer idAgendamento : agendamentos) {
                String query = "DELETE FROM agendamento WHERE rowid = ?";
                
                try (Connection con = getConexao();
                     PreparedStatement ps = con.prepareStatement(query)) {
                     
                    ps.setInt(1, idAgendamento);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
            if (!hasAgendamentosFuncionario(idFuncionario)) {
                destroyAgendamentosFuncionario(idFuncionario);
            }
        }
    }
}