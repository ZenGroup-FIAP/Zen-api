package br.com.zenSpaceOn.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.zenSpaceOn.bo.PsicologoBO;
import br.com.zenSpaceOn.to.PsicologoTO;

@Path("/psicologos")
public class PsicologoResource {
	
	private PsicologoBO bo = new PsicologoBO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PsicologoTO> listarPsicologos() {
		return bo.listar();
	}
	
}