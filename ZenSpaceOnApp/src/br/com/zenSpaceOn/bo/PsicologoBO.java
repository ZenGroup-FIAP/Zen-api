package br.com.zenSpaceOn.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.zenSpaceOn.bo.intefaces.Autenticavel;
import br.com.zenSpaceOn.dao.PsicologoDaoImpl;
import br.com.zenSpaceOn.enums.Disponibilidade;
import br.com.zenSpaceOn.to.PsicologoTO;



public class PsicologoBO implements Autenticavel {
	private PsicologoDaoImpl dao = null;
	
	@Override
	public Boolean autenticar(String email, String senha) {
		dao = new PsicologoDaoImpl();
		Boolean valido = null;
		try {
			if (dao.select(email, senha) != null) {
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
	
	public void cadastrar(PsicologoTO psicologo) {
		dao = new PsicologoDaoImpl();
		try {
			dao.insert(psicologo);
		} catch (SQLException e) {
			System.out.println("ERRO AO CADASTRAR PSICÓLOGO...");
			e.printStackTrace();
		}
	}
	
	public List<PsicologoTO> listar() {
		dao = new PsicologoDaoImpl();
		List<PsicologoTO> lista = null;
		try {
			lista = dao.select();
		} catch (SQLException e) {
			System.out.println("ERRO AO LISTAR PSICÓLOGOS...");
			e.printStackTrace();
		}
		return lista;
	}
	
	public PsicologoTO selecionar(Integer codigo) {
		dao = new PsicologoDaoImpl();
		PsicologoTO psicologo = null;
		try {
			psicologo = dao.select(codigo);
		} catch (SQLException e) {
			System.out.println("ERRO AO SELECIONAR PSICÓLOGO...");
			e.printStackTrace();
		}
		return psicologo;
	}
	
	public List<PsicologoTO> filtro(Double rating, Disponibilidade disponibilidade, String consultas) {
		dao = new PsicologoDaoImpl();
		List<PsicologoTO> lista = null;
		try {
			lista = dao.select(rating, disponibilidade, consultas);
		} catch (SQLException e) {
			System.out.println("ERRO NOS FILTROS...");
			e.printStackTrace();
		}
		return lista;
	}
	
	public void atualizar(PsicologoTO psicologo, Integer codigo) {
		dao = new PsicologoDaoImpl();
		try {
			dao.update(psicologo, codigo);
		} catch (SQLException e) {
			System.out.println("ERRO AO ATUALIZAR PSICOLOGO...");
			e.printStackTrace();
		}
	}
	
	public void excluir(Integer codigo) {
		AgendamentoBO agbo = new AgendamentoBO();
		ConsultaBO csbo = new ConsultaBO();
		
		
		csbo.excluirByPsicologo(codigo);
		agbo.excluirByPsicologo(codigo);
		dao = new PsicologoDaoImpl();
		try {
			dao.delete(codigo);
		} catch (SQLException e) {
			System.out.println("ERRO AO EXCLUIR PSICOLOGO...");
			e.printStackTrace();
		}
	}

}
