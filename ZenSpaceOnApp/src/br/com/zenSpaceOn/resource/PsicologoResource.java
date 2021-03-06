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

import br.com.zenSpaceOn.bo.PsicologoBO;
import br.com.zenSpaceOn.enums.Disponibilidade;
import br.com.zenSpaceOn.to.PsicologoTO;

@Path("/psicologos")
public class PsicologoResource {
	
	private PsicologoBO bo = new PsicologoBO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PsicologoTO> listarPsicologos() {
		return bo.listar();
	}
	
	@GET
	@Path("/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public PsicologoTO selecionarPsicologo( @PathParam("codigo") int codigo) {
		return bo.selecionar(codigo);
	}
	
	@GET
	@Path("/{rating}/{disponibilidade}/{consultas}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PsicologoTO> selecionarPorFiltro( @PathParam("rating") double rating, @PathParam("disponibilidade") String disponibilidade, @PathParam("consultas") String consultas) {		
		return bo.filtro(rating, Disponibilidade.valueOf(disponibilidade), consultas);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarPsicologo(PsicologoTO psicologo, @Context UriInfo uriInfo) {
		List<PsicologoTO> psicologos = bo.listar();
		List<Integer>codigosCadastrados = new ArrayList<>();
		int codigoPsicologo = 1;
		
		for (PsicologoTO p : psicologos) {
			codigosCadastrados.add(p.getCodigo());
		}
		
		while (codigosCadastrados.indexOf(codigoPsicologo) != -1) {;
			codigoPsicologo++;
		}
		
		psicologo.setCodigo(codigoPsicologo);
		bo.cadastrar(psicologo);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(psicologo.getCodigo()));
		return Response.created(builder.build()).build();
	}
	
	@PUT
	@Path("/{codigo}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarDados(PsicologoTO psicologo, @PathParam("codigo") Integer codigo) {
		psicologo.setCodigo(codigo);
		bo.atualizar(psicologo, codigo);
		
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{codigo}")
	public Response deletarPsicologo( @PathParam("codigo") Integer codigo) {
		bo.excluir(codigo);
		return Response.ok().build();
	}
}
