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
		public Response b(@FormParam("first") String fname, @FormParam("last") String lname)
				throws URISyntaxException {
	
			if (!(fname.isEmpty()) && !(lname.isEmpty())) {
				Person auto_id = new testApp().createActors(fname, lname);
				return Response.created(URI.create("response/" + auto_id)).build();
			} else {
				return Response.status(400).entity(("saknar parametrar")).build();
			}
		}
		
		@POST
		@Path("/Ladok/{id}/{betyg}/{modId}")
		//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.APPLICATION_JSON)
		public Response ladok(@PathParam("id") int id, @PathParam("betyg") String betyg, @PathParam("modId") int modId)
				throws URISyntaxException {
	
			if (!(betyg.isEmpty())) {
				int auto_id = new testApp().betygLadok(id,betyg,modId);
				return Response.created(URI.create("response/Ladok/" + auto_id)).build();
			} else {
				return Response.status(400).entity(("saknar parametrar")).build();
			}
		}
		
		
		// hårdkodat för kurs D0031N, använd parametrar i metoden för att göra dynamisk
		@GET
		@Path("/kurskod/{kod}/{modName}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getActorByKurskod(@PathParam("kod") String kurskod, @PathParam("modName") String modName) {
			String ac;
			if(!kurskod.equals(" ")) {
			 ac = new testApp().selectKurs(kurskod, modName); // fixa hårdkodning
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
		
		@GET
		@Path("/module/{kod}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getModules(@PathParam("kod") String kurskod) {
			String ac;
			if(!kurskod.equals(" ")) {
			 ac = new testApp().selectModule(kurskod); // fixa hårdkodning
			}  else {
				return Response.status(400).entity(("saknar parametrar")).build();
			}
			return Response.ok(ac).build();
		}
		
		@GET
		@Path("/modbetyg/{name}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getModuleBetyg(@PathParam("name") String name) {
			String ac;
			if(!name.equals(" ")) {
			 ac = new testApp().visabetyg(name); // fixa hårdkodning
			}  else {
				return Response.status(400).entity(("saknar parametrar")).build();
			}
			return Response.ok(ac).build();
		}
		
	
//		// ------- POST med OBJECT --------
	//
//		@POST
//		@Path("/Ladok/")
//		@Consumes(MediaType.APPLICATION_JSON)
//		@Produces(MediaType.APPLICATION_JSON)
//		public Response newActor(GetActors p) throws URISyntaxException {
//	
//			if (!(p.getBetygLadok().isEmpty())) {
//				GetActors auto_id = new testApp().betygLadok2(p.getId(),p.getBetygLadok());
//				return Response.created(URI.create("response/" + auto_id)).build();
//			} else {
//				return Response.status(400).entity(("saknar parametrar")).build();
//			}
//		}
	}

