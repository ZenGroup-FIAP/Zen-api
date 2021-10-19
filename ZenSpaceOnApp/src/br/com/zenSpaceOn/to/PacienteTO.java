package br.com.zenSpaceOn.to;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.zenSpaceOn.entities.Usuario;

@XmlRootElement
public class PacienteTO extends Usuario {

	public PacienteTO() {
		super();
	}

	public PacienteTO(Integer codigo, String nome, String email, String senha, String cpf, String telefone,
			Date nascimento) {
		super(codigo, nome, email, senha, cpf, telefone, nascimento);
	}
	
}
