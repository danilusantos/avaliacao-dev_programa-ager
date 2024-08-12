package br.com.soc.sistema.dao.exames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.exame.ExameVo;

public class ExameDao extends Dao {

	public void insertExame(ExameVo exameVo) {
		StringBuilder query = new StringBuilder(
				"INSERT INTO exame (nm_exame) values (?)");
		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;
			ps.setString(i++, exameVo.getNome());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ExameVo> findAllExames() {
		String query = "SELECT rowid id, nm_exame nome FROM exame" + ORDERBY;
		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString());
				ResultSet rs = ps.executeQuery()) {

			ExameVo vo = null;
			List<ExameVo> exames = new ArrayList<>();
			while (rs.next()) {
				vo = new ExameVo();
				vo.setRowid(rs.getString("id"));
				vo.setNome(rs.getString("nome"));

				exames.add(vo);
			}
			return exames;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public List<ExameVo> findAllByNome(String nome) {
		String sql = "SELECT rowid id, nm_exame nome FROM exame "
		           + "WHERE TRANSLATE(LOWER(nm_exame), "
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
				ExameVo vo = null;
				List<ExameVo> exames = new ArrayList<>();

				while (rs.next()) {
					vo = new ExameVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));

					exames.add(vo);
				}
				return exames;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public ExameVo findByCodigo(Integer codigo) {
		StringBuilder query = new StringBuilder(
				"SELECT rowid id, nm_exame nome FROM exame ")
						.append("WHERE rowid = ?");

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;

			ps.setInt(i, codigo);

			try (ResultSet rs = ps.executeQuery()) {
				ExameVo vo = null;

				while (rs.next()) {
					vo = new ExameVo();
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

	public void updateExame(ExameVo exameVo) {
		StringBuilder query = new StringBuilder(
				"UPDATE exame SET nm_exame = ? WHERE rowid = ?;");

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;
			ps.setString(i++, exameVo.getNome());
			ps.setString(i++, exameVo.getRowid());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroyExame(ExameVo exameVo) {
		StringBuilder query = new StringBuilder(
				"DELETE FROM exame WHERE rowid=?");
		try (Connection con = getConexao();
				PreparedStatement ps = con
						.prepareStatement(query.toString());) {
			int i = 1;
			ps.setString(i++, exameVo.getRowid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean podeDeletarExame(Integer exameId) {
        String query = "SELECT COUNT(*) AS total FROM agendamento WHERE id_exame = ?";
        
        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setInt(1, exameId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int total = rs.getInt("total");
                    return total == 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
}