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

import br.com.zenSpaceOn.bo.AgendamentoBO;
import br.com.zenSpaceOn.to.AgendamentoTO;


@Path("/agendamentos")
public class AgendamentoResource {
	
	private AgendamentoBO bo = new AgendamentoBO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AgendamentoTO> listaDeAgendamentos() {
		return bo.listar();
	}
	
	@GET
	@Path("/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public AgendamentoTO selecionarAgendamento( @PathParam("codigo") Integer codigo) {
		return bo.selecionar(codigo);
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response criarAgendamento(AgendamentoTO agendamento, @Context UriInfo uriInfo) {
		List<AgendamentoTO> agendamentos = bo.listar();
		List<Integer>codigosCadastrados = new ArrayList<>();
		int codigoAgendamento = 1;
		
		for (AgendamentoTO p : agendamentos) {
			codigosCadastrados.add(p.getCodigo());
		}
		
		while (codigosCadastrados.indexOf(codigoAgendamento) != -1) {;
			codigoAgendamento++;
		}
		
		agendamento.setCodigo(codigoAgendamento);
		bo.criar(agendamento);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(agendamento.getCodigo()));
		return Response.created(builder.build()).build();
	}
	
	@PUT
	@Path("/{codigo}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarAgendamento(AgendamentoTO agendamento, @PathParam("codigo") Integer codigo) {
		bo.atualizar(agendamento, codigo);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluirAgendamento( @PathParam("codigo") Integer codigo) {
		bo.excluir(codigo);
		return Response.ok().build();
	}
}
