package net.codejava.ws;

import java.net.URI;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class ClientDemo {
	private static final Client CLIENT = ClientBuilder.newClient();
	
	public static void main(String args[]) {
	WebTarget actorService = CLIENT.target(getBaseURI());
	
	String respons3 = actorService
			.request(MediaType.APPLICATION_JSON)
			.get(String.class);
	System.out.println(respons3);
	
	String respons1 = actorService
			.path("searchid/2")
			.request(MediaType.APPLICATION_JSON)
			.get(String.class);
	System.out.println(respons1);
	
	String respons2 = actorService
			.path("kurskod/D0024E")
			.request(MediaType.APPLICATION_JSON)
			.get(String.class);
	System.out.println(respons2);
	
	//@POST med form. Funkar
//	Form form = new Form();
//	form.param("first", "xoxo");
//	form.param("last", "yoyo");
//	
//	Response response = actorService
//			.request().post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED));
//	System.out.println("Status " + response.getStatus());
//	System.out.println(response.getHeaderString("location"));
	
			
	}
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/HelloREST/rest/response").build();
	}
}
