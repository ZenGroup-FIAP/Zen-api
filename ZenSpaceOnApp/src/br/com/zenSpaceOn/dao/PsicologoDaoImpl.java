package br.com.zenSpaceOn.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.zenSpaceOn.enums.Disponibilidade;
import br.com.zenSpaceOn.to.PsicologoTO;
import br.com.zenSpaceOn.tools.ConnectionOracle;



public class PsicologoDaoImpl implements PsicologoDao {
	
	private ConnectionOracle conn;

	@Override
	public void insert(PsicologoTO psicologo) throws SQLException {
		conn = ConnectionOracle.getInstance();
		String sql = "INSERT INTO T_ZSO_PSICOLOGO (CD_PSICOLOGO, NM_PSICOLOGO, DS_EMAIL, DS_SENHA, VL_HORA, NR_RANKING, TX_DESC_PERFIL,  NR_TELEFONE,  NR_CPF, DT_NASCIMENTO,  NR_RATING, DS_DISPONIBILIDADE, NR_CONSULTAS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
			
		ps.setInt(1, psicologo.getCodigo());
		ps.setString(2, psicologo.getNome());
		ps.setString(3, psicologo.getEmail());
		ps.setString(4, psicologo.getSenha());
		ps.setDouble(5, psicologo.getVlHora());
		ps.setInt(6, psicologo.getRanking());
		ps.setString(7, psicologo.getBio());
		ps.setString(8, psicologo.getTelefone());
		ps.setString(9, psicologo.getCpf());
		ps.setDate(10, psicologo.getNascimento());
		ps.setDouble(11, psicologo.getRating());
		ps.setString(12, psicologo.getDisponibilidade().toString());
		ps.setInt(13, psicologo.getConsultas());
		
		ps.execute();
		ps.close();
	}

	@Override
	public List<PsicologoTO> select() throws SQLException {
		conn = ConnectionOracle.getInstance();
		List<PsicologoTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM T_ZSO_PSICOLOGO";
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			PsicologoTO psicologo = new PsicologoTO();
			psicologo.setCodigo(rs.getInt("CD_PSICOLOGO"));
			psicologo.setNome(rs.getString("NM_PSICOLOGO"));
			psicologo.setEmail(rs.getString("DS_EMAIL"));
			psicologo.setSenha(rs.getString("DS_SENHA"));
			psicologo.setVlHora(rs.getDouble("VL_HORA"));
			psicologo.setRanking(rs.getInt("NR_RANKING"));
			psicologo.setBio(rs.getString("TX_DESC_PERFIL"));
			psicologo.setTelefone(rs.getString("NR_TELEFONE"));
			psicologo.setCpf(rs.getString("NR_CPF"));
			psicologo.setNascimento(rs.getDate("DT_NASCIMENTO"));
			psicologo.setRating(rs.getDouble("NR_RATING"));
			psicologo.setDisponibilidade(Disponibilidade.valueOf(rs.getString("DS_DISPONIBILIDADE")));
			psicologo.setConsultas(rs.getInt("NR_CONSULTAS"));
			
			lista.add(psicologo);
		}
		
		rs.close();
		ps.close();
		
		return lista;
	}

	
	@Override
	public List<PsicologoTO> select(Double rating, Disponibilidade disponibilidade, String consultas) throws SQLException {
		conn = ConnectionOracle.getInstance();
		List<PsicologoTO> lista = new ArrayList<>();
		String sql = null;		
		if (consultas.equals("ACIMA_DE_100")) {
			sql = "SELECT * FROM T_ZSO_PSICOLOGO WHERE NR_RATING = ? AND DS_DISPONIBILIDADE = ? AND NR_CONSULTAS > ?";
		} else if (consultas.equals("ABAIXO_DE_100")) {
			sql = "SELECT * FROM T_ZSO_PSICOLOGO WHERE NR_RATING = ? AND DS_DISPONIBILIDADE = ? AND NR_CONSULTAS < ?";
		} else {
			sql = "SELECT * FROM T_ZSO_PSICOLOGO WHERE NR_RATING = ? AND DS_DISPONIBILIDADE = ? AND NR_CONSULTAS = ?";
		}
		
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
		ps.setString(1, rating.toString());
		ps.setString(2, disponibilidade.toString());
		ps.setInt(3, 100);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()){
			PsicologoTO psicologo = new PsicologoTO();
			psicologo.setCodigo(rs.getInt("CD_PSICOLOGO"));
			psicologo.setNome(rs.getString("NM_PSICOLOGO"));
			psicologo.setEmail(rs.getString("DS_EMAIL"));
			psicologo.setSenha(rs.getString("DS_SENHA"));
			psicologo.setVlHora(rs.getDouble("VL_HORA"));
			psicologo.setRanking(rs.getInt("NR_RANKING"));
			psicologo.setBio(rs.getString("TX_DESC_PERFIL"));
			psicologo.setTelefone(rs.getString("NR_TELEFONE"));
			psicologo.setCpf(rs.getString("NR_CPF"));
			psicologo.setNascimento(rs.getDate("DT_NASCIMENTO"));
			psicologo.setRating(rs.getDouble("NR_RATING"));
			psicologo.setDisponibilidade(Disponibilidade.valueOf(rs.getString("DS_DISPONIBILIDADE")));
			psicologo.setConsultas(rs.getInt("NR_CONSULTAS"));		
			 
			lista.add(psicologo);
		}
		
		rs.close();
		ps.close();
		
		return lista;
	}

	@Override
	public PsicologoTO select(Integer codigo) throws SQLException {
		conn = ConnectionOracle.getInstance();
		PsicologoTO psicologo = new PsicologoTO();
		String sql = "SELECT * FROM T_ZSO_PSICOLOGO WHERE CD_PSICOLOGO = " + codigo;
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			psicologo.setCodigo(rs.getInt("CD_PSICOLOGO"));
			psicologo.setNome(rs.getString("NM_PSICOLOGO"));
			psicologo.setEmail(rs.getString("DS_EMAIL"));
			psicologo.setSenha(rs.getString("DS_SENHA"));
			psicologo.setVlHora(rs.getDouble("VL_HORA"));
			psicologo.setRanking(rs.getInt("NR_RANKING"));
			psicologo.setBio(rs.getString("TX_DESC_PERFIL"));
			psicologo.setTelefone(rs.getString("NR_TELEFONE"));
			psicologo.setCpf(rs.getString("NR_CPF"));
			psicologo.setNascimento(rs.getDate("DT_NASCIMENTO"));
			psicologo.setRating(rs.getDouble("NR_RATING"));
			psicologo.setDisponibilidade(Disponibilidade.valueOf(rs.getString("DS_DISPONIBILIDADE")));
			psicologo.setConsultas(rs.getInt("NR_CONSULTAS"));	
			
		}else {
			psicologo = null;
		}
		
		rs.close();
		ps.close();
		
		return psicologo;
	}

	@Override
	public PsicologoTO select(String email, String senha) throws SQLException {
		conn = ConnectionOracle.getInstance();
		PsicologoTO psicologo = new PsicologoTO();
		String sql = "SELECT * FROM T_ZSO_PACIENTE WHERE DS_EMAIL = " + email + " AND DS_SENHA = " + senha;
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			psicologo.setCodigo(rs.getInt("CD_PSICOLOGO"));
			psicologo.setNome(rs.getString("NM_PSICOLOGO"));
			psicologo.setEmail(rs.getString("DS_EMAIL"));
			psicologo.setSenha(rs.getString("DS_SENHA"));
			psicologo.setVlHora(rs.getDouble("VL_HORA"));
			psicologo.setRanking(rs.getInt("NR_RANKING"));
			psicologo.setBio(rs.getString("TX_DESC_PERFIL"));
			psicologo.setTelefone(rs.getString("NR_TELEFONE"));
			psicologo.setCpf(rs.getString("NR_CPF"));
			psicologo.setNascimento(rs.getDate("DT_NASCIMENTO"));
			psicologo.setRating(rs.getDouble("NR_RATING"));
			psicologo.setDisponibilidade(Disponibilidade.valueOf(rs.getString("DS_DISPONIBILIDADE")));
			psicologo.setConsultas(rs.getInt("NR_CONSULTAS"));
			
		}else {
			psicologo = null;
			
		}
		
		rs.close();
		ps.close();
		
		return psicologo;
		
	}

	
	@Override
	public void update(PsicologoTO psicologo, Integer codigo) throws SQLException {
		conn = ConnectionOracle.getInstance();
		String sql = "UPDATE T_ZSO_PSICOLOGO SET NM_PSICOLOGO=?, DS_EMAIL=?, DS_SENHA=?, VL_HORA=?, NR_RANKING=?, TX_DESC_PERFIL=?, NR_TELEFONE=?, NR_CPF=?, DT_NASCIMENTO=?, NR_RATING=?, DS_DISPONIBILIDADE=?, NR_CONSULTAS=? WHERE CD_PSICOLOGO=?";
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
		
		ps.setString(1, psicologo.getNome());
		ps.setString(2, psicologo.getEmail());
		ps.setString(3, psicologo.getSenha());
		ps.setDouble(4, psicologo.getVlHora());
		ps.setInt(5, psicologo.getRanking());
		ps.setString(6, psicologo.getBio());
		ps.setString(7, psicologo.getTelefone());
		ps.setString(8, psicologo.getCpf());
		ps.setDate(9, psicologo.getNascimento());
		ps.setDouble(10, psicologo.getRating());
		ps.setString(11, psicologo.getDisponibilidade().toString());
		ps.setInt(12, psicologo.getConsultas());
		ps.setInt(13, psicologo.getCodigo());
		
		ps.execute();
		
		ps.close();
	}

	@Override
	public void delete(Integer codigo) throws SQLException {
		conn = ConnectionOracle.getInstance();
		String sql = "DELETE FROM T_ZSO_PSICOLOGO WHERE CD_PSICOLOGO = ?";
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
		ps.setInt(1, codigo);
		
		ps.execute();
		ps.close();
	}

}

