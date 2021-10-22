package br.com.zenSpaceOn.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.zenSpaceOn.to.ConsultaTO;
import br.com.zenSpaceOn.tools.ConnectionOracle;

public class ConsultaDaoImpl implements ConsultaDao {

	private ConnectionOracle conn;
	
	@Override
	public void insert(ConsultaTO consulta) throws SQLException {
		conn = ConnectionOracle.getInstance();
		String sql = "INSERT INTO T_ZSO_CONSULTA (CD_CONSULTA, CD_AGENDAMENTO, CD_PACIENTE, CD_PSICOLOGO, VL_CONSULTA) VALUES (?,?,?,?,?)";
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
		ps.setInt(1, consulta.getCodigo());
		ps.setInt(2, consulta.getCodigoAgendamento());
		ps.setInt(3, consulta.getCodigoPaciente());
		ps.setInt(4, consulta.getCodigoPsicologo());
		ps.setDouble(5, consulta.getValorConsulta());
		
		ps.execute();
		ps.close();
	}

	@Override
	public List<ConsultaTO> select() throws SQLException {
		conn = ConnectionOracle.getInstance();
		List<ConsultaTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM T_ZSO_CONSULTA";
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			ConsultaTO consulta = new ConsultaTO();
			consulta.setCodigo(rs.getInt("CD_CONSULTA"));
			consulta.setCodigoAgendamento(rs.getInt("CD_AGENDAMENTO"));
			consulta.setCodigoPaciente(rs.getInt("CD_PACIENTE"));
			consulta.setCodigoPsicologo(rs.getInt("CD_PSICOLOGO"));
			consulta.setValorConsulta(rs.getDouble("VL_CONSULTA"));
			lista.add(consulta);
		}
		
		
		rs.close();
		ps.close();
		return lista;
	}

	@Override
	public ConsultaTO select(Integer codigo) throws SQLException {
		conn = ConnectionOracle.getInstance();
		String sql = "SELECT * FROM T_ZSO_CONSULTA WHERE CD_CONSULTA=?";
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
		ps.setInt(1, codigo);
		ResultSet rs = ps.executeQuery();
		
		ConsultaTO consulta = new ConsultaTO();
		if (rs.next()) {
			consulta.setCodigo(rs.getInt("CD_CONSULTA"));
			consulta.setCodigoAgendamento(rs.getInt("CD_AGENDAMENTO"));
			consulta.setCodigoPaciente(rs.getInt("CD_PACIENTE"));
			consulta.setCodigoPsicologo(rs.getInt("CD_PSICOLOGO"));
			consulta.setValorConsulta(rs.getDouble("VL_CONSULTA"));
		} else {
			return null;			
		}
		
		rs.close();
		ps.close();
		return consulta;
	}

	@Override
	public void update(ConsultaTO consulta, Integer codigo) throws SQLException {
		conn = ConnectionOracle.getInstance();
		String sql = "UPDATE T_ZSO_CONSULTA SET CD_CONSULTA=?, CD_AGENDAMENTO=?, CD_PACIENTE=?, CD_PSICOLOGO=?, VL_CONSULTA=? WHERE CD_CONSULTA=?";
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
		ps.setInt(1, consulta.getCodigo());
		ps.setInt(2, consulta.getCodigoAgendamento());
		ps.setInt(3, consulta.getCodigoPaciente());
		ps.setInt(4, consulta.getCodigoPsicologo());
		ps.setDouble(5, consulta.getValorConsulta());
		ps.setInt(6, codigo);
		
		ps.execute();
		ps.close();
	}

	@Override
	public void delete(Integer codigo) throws SQLException {
		conn = ConnectionOracle.getInstance();
		String sql = "DELETE FROM T_ZSO_CONSULTA WHERE CD_CONSULTA=?";
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
		ps.setInt(1, codigo);
		
		ps.execute();
		ps.close();
	}

}
