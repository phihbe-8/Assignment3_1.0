package net.codejava.ws;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


@Path("response")
public class ResponseMessage {
	
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response Message() {	
		String App = new testApp().testActors();
		return Response.ok(App).build();
		}
	
	 //------ POST med FORM -------
		@POST
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.APPLICATION_JSON)
		public Response newActorByForm(@FormParam("first") String fname, @FormParam("last") String lname)
				throws URISyntaxException {
	
			if (!(fname.isEmpty()) && !(lname.isEmpty())) {
				Person auto_id = new testApp().createActors(fname, lname);
				return Response.created(URI.create("response/" + auto_id)).build();
			} else {
				return Response.status(400).entity(("saknar parametrar")).build();
			}
		}
		
		// hårdkodat för kurs D0031N, använd parametrar i metoden för att göra dynamisk
		@GET
		@Path("/kurskod/{kod}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getActorByKurskod(@PathParam("kod") String kurskod) {
			String ac;
			if(!kurskod.equals(" ")) {
			 ac = new testApp().selectKurs(kurskod); // fixa hårdkodning
			}  else {
				return Response.status(400).entity(("saknar parametrar")).build();
			}
			return Response.ok(ac).build();
		}
		
		@GET
		@Path("/searchid/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getActorById(@PathParam("id") int actorId){
			GetActors ac = new testApp().actorById(actorId);
			return Response.ok(ac).build();
		}
		
	
//		// ------- POST med OBJECT --------
	//
//		@POST
//		@Consumes(MediaType.APPLICATION_JSON)
//		@Produces(MediaType.APPLICATION_JSON)
//		public Response newActor(Person p) throws URISyntaxException {
//	
//			if (!(p.getFname().isEmpty()) && !(p.getLname().isEmpty())) {
//				Person auto_id = new testApp().createActors(p.getFname(), p.getLname());
//				return Response.created(URI.create("response/" + auto_id)).build();
//			} else {
//				return Response.status(400).entity(("saknar parametrar")).build();
//			}
//		}
	}

