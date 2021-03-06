package br.com.zenSpaceOn.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.zenSpaceOn.enums.Disponibilidade;
import br.com.zenSpaceOn.to.PsicologoTO;

public interface PsicologoDao {
	public void insert(PsicologoTO psicologo) throws SQLException;
	
	public List<PsicologoTO> select() throws SQLException;
	
	public List<PsicologoTO> select(Double rating, Disponibilidade disponibilidade, String consultas) throws SQLException;

	public PsicologoTO select(Integer codigo) throws SQLException;
	
	public PsicologoTO select(String email, String senha) throws SQLException;
	
	public void update(PsicologoTO psicologo, Integer codigo) throws SQLException;
	
	public void delete(Integer codigo) throws SQLException;
}
