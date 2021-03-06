package br.com.zenSpaceOn.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.zenSpaceOn.to.ConsultaTO;

public interface ConsultaDao {
public void insert(ConsultaTO consulta) throws SQLException;
	
	public List<ConsultaTO> select() throws SQLException;
	
	public ConsultaTO select(Integer codigo) throws SQLException;
	
	public void update(ConsultaTO agendamento, Integer codigo) throws SQLException;
	
	public void delete(Integer codigo) throws SQLException;
	
	public void deleteByPaciente(Integer codigo) throws SQLException;
	
	public void deleteByPsicologo(Integer codigo) throws SQLException;
	
	public void deleteByAgendamento(Integer codigo) throws SQLException; 
}
