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

import br.com.zenSpaceOn.bo.ConsultaBO;
import br.com.zenSpaceOn.to.ConsultaTO;

@Path("/consultas")
public class ConsultaResource {
	
	private ConsultaBO bo = new ConsultaBO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConsultaTO> listarConsultas() {
		return bo.listar();
	}
	
	@GET
	@Path("/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsultaTO selecionarConsulta( @PathParam("codigo") Integer codigo) {
		return bo.selecionar(codigo);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response criarConsulta(ConsultaTO consulta, @Context UriInfo uriInfo) {
		List<ConsultaTO> consultas = bo.listar();
		List<Integer>codigosCadastrados = new ArrayList<>();
		int codigoConsulta = 1;
		
		for (ConsultaTO p : consultas) {
			codigosCadastrados.add(p.getCodigo());
		}
		
		while (codigosCadastrados.indexOf(codigoConsulta) != -1) {;
			codigoConsulta++;
		}
		
		consulta.setCodigo(codigoConsulta);
		bo.criar(consulta);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(consulta.getCodigo()));
		return Response.created(builder.build()).build();
	}
	
	@PUT
	@Path("/{codigo}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarConsulta(ConsultaTO consulta, @PathParam("codigo") Integer codigo) {
		consulta.setCodigo(codigo);
		bo.atualizar(consulta, codigo);
		
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluirConsulta( @PathParam("codigo") Integer codigo) {
		bo.excluir(codigo);
		
		return Response.ok().build();
	}
}
