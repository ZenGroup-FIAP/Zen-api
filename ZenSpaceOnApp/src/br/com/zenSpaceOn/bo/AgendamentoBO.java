package br.com.zenSpaceOn.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.zenSpaceOn.dao.AgandamentoDaoImpl;
import br.com.zenSpaceOn.to.AgendamentoTO;

public class AgendamentoBO {
	
	private AgandamentoDaoImpl dao = new AgandamentoDaoImpl();
	
	public void criar(AgendamentoTO agendamento) {
		try {
			dao.insert(agendamento);
		} catch (SQLException e) {
			System.out.println("ERRO AO CRIAR AGENDAMENTO...");
			e.printStackTrace();
		}
	}
	
	public List<AgendamentoTO> listar() {
		List<AgendamentoTO> agendamentos = null;
		try {
			agendamentos = dao.select();
		} catch (SQLException e) {
			System.out.println("ERRO AO LISTAR AGENDAMENTOS...");
			e.printStackTrace();
		}
		return agendamentos;
	}
	
	public AgendamentoTO selecionar(Integer codigo) {
		AgendamentoTO agendamento = null;
		try {
			agendamento = dao.select(codigo);
		} catch (SQLException e) {
			System.out.println("ERRO AO SELECIONAR AGENDAMENTO...");
			e.printStackTrace();
		}
		return agendamento;
	}
	
	public void atualizar(AgendamentoTO agendamento, Integer codigo) {
		try {
			dao.update(agendamento, codigo);
		} catch (SQLException e) {
			System.out.println("ERRO AO ATUALIZAR AGENDAMENTO...");
			e.printStackTrace();
		}
	}
	
	public void excluir(Integer codigo) {
		ConsultaBO csbo = new ConsultaBO();
		
		csbo.excluirByAgendamento(codigo);
		try {
			dao.delete(codigo);
		} catch (SQLException e) {
			System.out.println("ERRO AO EXCLUIR AGENDAMENTO...");
			e.printStackTrace();
		}
	}
	
	public void excluirByPaciente(Integer codigo) {
		try {
			dao.deleteByPaciente(codigo);
		} catch (SQLException e) {
			System.out.println("ERRO AO EXCLUIR AGENDAMENTO...");
			e.printStackTrace();
		}
	}
	
	public void excluirByPsicologo(Integer codigo) {
		try {
			dao.deleteByPsicologo(codigo);
		} catch (SQLException e) {
			System.out.println("ERRO AO EXCLUIR AGENDAMENTO...");
			e.printStackTrace();
		}
	}
}
