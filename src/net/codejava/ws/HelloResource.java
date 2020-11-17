package net.codejava.ws;

import javax.ws.rs.GET; 
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/bonjour")
public class HelloResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String direBonjour() {
		return "Bonjour, tout le monde!";
	}
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHTMLHello() {
		return "<html><title> Hello REST</title><body>"
				+ "<h1>Bonjour, tout le monde!</h1></body></html>";
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Student sayJsonHello() {
		return new Student(123, "Samir", "Kumar");
	}
}
