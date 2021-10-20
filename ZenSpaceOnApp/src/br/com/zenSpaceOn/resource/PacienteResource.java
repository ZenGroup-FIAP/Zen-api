package br.com.zenSpaceOn.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.zenSpaceOn.bo.PacienteBO;
import br.com.zenSpaceOn.to.PacienteTO;

@Path("/pacientes")
public class PacienteResource {
	private PacienteBO bo = new PacienteBO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PacienteTO> listarTodos() {
		return bo.listar();
	}
	
	@GET
	@Path("/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public PacienteTO listarTodos(@PathParam("codigo") int codigo) {
		return bo.selecionar(codigo);
	}
	
}
