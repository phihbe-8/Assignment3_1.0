package net.codejava.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path ("test")
public class postTest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {
		return Response.ok("Hej").build();
	}
	
	
}
