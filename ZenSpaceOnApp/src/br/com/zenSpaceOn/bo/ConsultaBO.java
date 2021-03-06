package br.com.zenSpaceOn.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.zenSpaceOn.dao.ConsultaDaoImpl;
import br.com.zenSpaceOn.to.ConsultaTO;

public class ConsultaBO {
	
	private ConsultaDaoImpl dao = new ConsultaDaoImpl();
	
	public void criar(ConsultaTO consulta) {
		try {
			dao.insert(consulta);
		} catch (SQLException e) {
			System.out.println("ERRO AO CRIAR CONSULTA...");
			e.printStackTrace();
		}
	}
	
	public List<ConsultaTO> listar() {
		List<ConsultaTO> consultas = null;
		try {
			consultas = dao.select();
		} catch (SQLException e) {
			System.out.println("ERRO AO LISTAR CONSULTAS...");
			e.printStackTrace();
		}
		return consultas;
	}
	
	
	public ConsultaTO selecionar(Integer codigo) {
		ConsultaTO consulta = null;
		try {
			consulta = dao.select(codigo);
		} catch (SQLException e) {
			System.out.println("ERRO AO SELECIONAR CONSULTA...");
			e.printStackTrace();
		}
		return consulta;
	}
	
	public void atualizar(ConsultaTO consulta, Integer codigo) {
		try {
			dao.update(consulta, codigo);
		} catch (SQLException e) {
			System.out.println("ERRO AO ATUALIZAR DADOS DA CONSULTA...");
			e.printStackTrace();
		}
	}
	
	public void excluir(Integer codigo) {
		try {
			dao.delete(codigo);
		} catch (SQLException e) {
			System.out.println("ERRO AO EXCLUIR CONSULTA...");
			e.printStackTrace();
		}
	}
	
	public void excluirByPaciente(Integer codigo) {
		try {
			dao.deleteByPaciente(codigo);
		} catch (SQLException e) {
			System.out.println("ERRO AO EXCLUIR CONSULTA...");
			e.printStackTrace();
		}
	}
	
	public void excluirByPsicologo(Integer codigo) {
		try {
			dao.deleteByPsicologo(codigo);
		} catch (SQLException e) {
			System.out.println("ERRO AO EXCLUIR CONSULTA...");
			e.printStackTrace();
		}
	}
	
	public void excluirByAgendamento(Integer codigo) {
		try {
			dao.deleteByAgendamento(codigo);
		} catch (SQLException e) {
			System.out.println("ERRO AO EXCLUIR CONSULTA...");
			e.printStackTrace();
		}
	}
	
}
