package br.com.zenSpaceOn.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

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
	
	
	// CADASTRO
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(PacienteTO paciente, @Context UriInfo uriInfo) {
		List<PacienteTO> pacientes = bo.listar();
		List<Integer>codigosCadastrados = new ArrayList<>();
		int codigoPaciente = 1;
		
		for (PacienteTO p : pacientes) {
			codigosCadastrados.add(p.getCodigo());
		}
		
		while (codigosCadastrados.indexOf(codigoPaciente) != -1) {;
			codigoPaciente++;
		}
		
		paciente.setCodigo(codigoPaciente);
		bo.cadastrar(paciente);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(paciente.getCodigo()));
		return Response.created(builder.build()).build();
	}
	
	
	@GET
	@Path("/login/{email}/{senha}")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean login(@PathParam("email") String email, @PathParam("senha") String senha) {	
		if (bo.autenticar(email, senha)) {
			return true;
		} else {
			return false;
		}
	}
	
	@PUT
	@Path("/{codigo}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualiza(PacienteTO paciente, @PathParam("codigo") int codigo) {
		paciente.setCodigo(codigo);
		bo.atualizar(paciente);
		
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{codigo}")
	public Response deletaPaciente(@PathParam("codigo") int codigo) {
		bo.exclur(codigo);
		
		return Response.ok().build();
	}
}
