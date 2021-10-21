package br.com.zenSpaceOn.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	
}
