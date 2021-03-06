package br.com.zenSpaceOn.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.zenSpaceOn.bo.intefaces.Autenticavel;
import br.com.zenSpaceOn.dao.PacienteDaoImpl;
import br.com.zenSpaceOn.to.PacienteTO;

public class PacienteBO implements Autenticavel{

	private PacienteDaoImpl dao;
	
	@Override
	public Boolean autenticar(String email, String senha) {
		Boolean valido = null;
		dao = new PacienteDaoImpl();
		
		try {
			if (dao.select(email, senha).size() > 0) {
				valido = true;
			} else {
				valido = false;
			}
		} catch (SQLException e) {
			System.out.println("ERRO NO SISTEMA DE LOGIN...");
			e.printStackTrace();
		}
		
		return valido;
	}
	
	public void cadastrar(PacienteTO paciente) {
		dao = new PacienteDaoImpl();
		try {
			dao.insert(paciente);
		} catch (SQLException e) {
			System.out.println("ERRO AO CADASTRAR PACIENTE...");
			e.printStackTrace();
		}
	}
	
	public List<PacienteTO> listar() {
		dao = new PacienteDaoImpl();
		List<PacienteTO> pacientes = null;
		try {
			pacientes = dao.select();
		} catch (SQLException e) {
			System.out.println("ERRO AO LISTAR PACIENTES...");
			e.printStackTrace();
		}
		return pacientes;
	}
	
	public PacienteTO selecionar(Integer codigo) {
		dao = new PacienteDaoImpl();
		PacienteTO paciente = null;
		try {
			paciente = dao.select(codigo);
		} catch (SQLException e) {
			System.out.println("ERRO AO SELECIONAR PACIENTE...");
			e.printStackTrace();
		}
		return paciente;
	}
	
	public void atualizar(PacienteTO paciente) {
		dao = new PacienteDaoImpl();
		
		try {
			dao.update(paciente);
		} catch (SQLException e) {
			System.out.println("ERRO AO ATUALIZAAR PACIENTE...");
			e.printStackTrace();
		}
	}
	
	public void exclur(Integer codigo) {
		dao = new PacienteDaoImpl();
		AgendamentoBO agbo = new AgendamentoBO();
		ConsultaBO csbo = new ConsultaBO();
		
		
		csbo.excluirByPaciente(codigo);
		agbo.excluirByPaciente(codigo);
		try {
			dao.delete(codigo);
		} catch (SQLException e) {
			System.out.println("ERRO AO EXCLUIR PACIENTE...");
			e.printStackTrace();
		}
	}

}
