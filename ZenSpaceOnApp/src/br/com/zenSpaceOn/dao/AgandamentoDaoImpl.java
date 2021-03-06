package br.com.zenSpaceOn.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.zenSpaceOn.to.AgendamentoTO;
import br.com.zenSpaceOn.tools.ConnectionOracle;

public class AgandamentoDaoImpl implements AgendamentoDao {
	
	private ConnectionOracle conn;

	@Override
	public void insert(AgendamentoTO agendamento) throws SQLException {
		conn = ConnectionOracle.getInstance();
		String sql = "INSERT INTO T_ZSO_AGENDAMENTO (CD_AGENDAMENTO, CD_PACIENTE, CD_PSICOLOGO, DT_CONSULTA, DT_HORA_CONSULTA) VALUES (?,?,?,?,?)";
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
		ps.setInt(1, agendamento.getCodigo());
		ps.setInt(2, agendamento.getCodgiPaciente());
		ps.setInt(3, agendamento.getCodigoPsicologo());
		ps.setDate(4, agendamento.getDataConsulta());
		ps.setDate(5, agendamento.getHoraConsulta());
		
		ps.execute();
		ps.close();
	}

	@Override
	public List<AgendamentoTO> select() throws SQLException {
		conn = ConnectionOracle.getInstance();
		List<AgendamentoTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM T_ZSO_AGENDAMENTO";
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			AgendamentoTO agendamento = new AgendamentoTO();
			agendamento.setCodigo(rs.getInt("CD_AGENDAMENTO"));
			agendamento.setCodgiPaciente(rs.getInt("CD_PACIENTE"));
			agendamento.setCodigoPsicologo(rs.getInt("CD_PSICOLOGO"));
			agendamento.setDataConsulta(rs.getDate("DT_CONSULTA"));
			agendamento.setHoraConsulta(rs.getDate("DT_HORA_CONSULTA"));
			lista.add(agendamento);
		}
		
		return lista;
	}
	

	@Override
	public AgendamentoTO select(Integer codigo) throws SQLException {
		conn = ConnectionOracle.getInstance();
		String sql = "SELECT * FROM T_ZSO_AGENDAMENTO WHERE CD_AGENDAMENTO = ?";
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
		ps.setInt(1, codigo);
		ResultSet rs = ps.executeQuery();
		
		
		AgendamentoTO agendamento = null;
		if (rs.next()) {
			agendamento = new AgendamentoTO(rs.getInt("CD_AGENDAMENTO"), rs.getInt("CD_PACIENTE"), rs.getInt("CD_PSICOLOGO"), rs.getDate("DT_CONSULTA"), rs.getDate("DT_HORA_CONSULTA"));
		} else {
			return null;
		}
		
		rs.close();
		ps.close();
		return agendamento;
		
	}

	@Override
	public void update(AgendamentoTO agendamento, Integer codigo) throws SQLException {
		conn = ConnectionOracle.getInstance();
		String sql = "UPDATE T_ZSO_AGENDAMENTO SET CD_PACIENTE=?, CD_PSICOLOGO=?, DT_CONSULTA=?, DT_HORA_CONSULTA=? WHERE CD_AGENDAMENTO=?";
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
		ps.setInt(1, agendamento.getCodgiPaciente());
		ps.setInt(2, agendamento.getCodigoPsicologo());
		ps.setDate(3, agendamento.getDataConsulta());
		ps.setDate(4, agendamento.getHoraConsulta());
		ps.setInt(5, codigo);
		
		ps.execute();
		ps.close();
	}

	@Override
	public void delete(Integer codigo) throws SQLException {
		conn = ConnectionOracle.getInstance();
		String sql = "DELETE FROM T_ZSO_AGENDAMENTO WHERE CD_AGENDAMENTO=?";
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
		ps.setInt(1, codigo);
		ps.execute();
		ps.close();	
	}

	@Override
	public void deleteByPaciente(Integer codigo) throws SQLException {
		conn = ConnectionOracle.getInstance();
		String sql = "DELETE FROM T_ZSO_AGENDAMENTO WHERE CD_PACIENTE=?";
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
		ps.setInt(1, codigo);
		ps.execute();
		ps.close();	
		
	}

	@Override
	public void deleteByPsicologo(Integer codigo) throws SQLException {
		conn = ConnectionOracle.getInstance();
		String sql = "DELETE FROM T_ZSO_AGENDAMENTO WHERE CD_PSICOLOGO=?";
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
		ps.setInt(1, codigo);
		ps.execute();
		ps.close();	
		
	}

}
