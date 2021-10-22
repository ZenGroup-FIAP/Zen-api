package br.com.zenSpaceOn.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.zenSpaceOn.to.AgendamentoTO;

public interface AgendamentoDao {
	public void insert(AgendamentoTO agendamento) throws SQLException;
	
	public List<AgendamentoTO> select() throws SQLException;
	
	public AgendamentoTO select(Integer codigo) throws SQLException;
	
	public void update(AgendamentoTO agendamento, Integer codigo) throws SQLException;
	
	public void delete(Integer codigo) throws SQLException;
	
	public void deleteByPaciente(Integer codigo) throws SQLException;
	
	public void deleteByPsicologo(Integer codigo) throws SQLException;
}
