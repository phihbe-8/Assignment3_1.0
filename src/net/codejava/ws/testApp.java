package net.codejava.ws;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class testApp {

	private static Statement stmt;
	private static ResultSet results;
	private static ResultSet results2;

	public static void main(String[] args) {

		String sql_select = "Select * From actors";

		int id = 10;
		String fname;
		String lname;

		Scanner input = new Scanner(System.in);
		System.out.println("Ange förnamn");
		fname = input.nextLine();
		System.out.println("Ange efternamn");
		lname = input.nextLine();

		Person person = new Person(fname, lname);

		String sql_select2 = "Insert into actors (fname, lname) values ('" + person.getFname() + "','"
				+ person.getLname() + "')";

		try (Connection conn = dbConnect.createNewDBconnection()) {

			stmt = conn.createStatement();
			stmt.executeUpdate(sql_select2);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}

		// ---------------Test för Select-----

		try (Connection conn = dbConnect.createNewDBconnection()) {

			stmt = conn.createStatement();
			results = stmt.executeQuery(sql_select);

			List<GetActors> studentsList = new ArrayList<GetActors>();

			while (results.next()) {

				GetActors actor = new GetActors();

				actor.setId(Integer.valueOf(results.getString("id")));
				actor.setFName(results.getString("fname"));
				actor.setLName(results.getString("lname"));

				studentsList.add(actor);
			}

			ObjectMapper mapper = new ObjectMapper();
			String JSONOutput = mapper.writeValueAsString(studentsList);
			System.out.println(JSONOutput);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}
	
	//----------

	public String testActors() {
		String sql_select = "Select * From actors";
		String JSONOutput = "";
		List<GetActors> studentsList = new ArrayList<GetActors>();

		try (Connection conn = dbConnect.createNewDBconnection()) {

			stmt = conn.createStatement();
			results = stmt.executeQuery(sql_select);

			while (results.next()) {

				GetActors actor = new GetActors();

				actor.setId(Integer.valueOf(results.getString("id")));
				actor.setFName(results.getString("fname"));
				actor.setLName(results.getString("lname"));
				actor.setKurskod(results.getString("kurskod"));
				actor.setBetygCanvas(results.getString("betygCanvas"));
				actor.setBetygLadok(results.getString("betygLadok"));

				studentsList.add(actor);
			}

			ObjectMapper mapper = new ObjectMapper();
			JSONOutput = mapper.writeValueAsString(studentsList);
			System.out.println(JSONOutput);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return JSONOutput;

	}

	public Person createActors(String fname, String lname) {

		int id = 0;

		Person person = new Person(fname, lname);

		String sql_select2 = "Insert into actors (fname, lname) values ('" + person.getFname() + "','"
				+ person.getLname() + "')";

		try (Connection conn = dbConnect.createNewDBconnection()) {

			stmt = conn.createStatement();
			stmt.executeUpdate(sql_select2);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return person;

	}
	// för post med formparam
	public int betygLadok(int id, String betyg, String modId, String kurskod) {
		
		String sql_select = "UPDATE modulebetyg m INNER JOIN actors a ON m.actor_id=a.id JOIN moduler mr ON mr.id =m.module_id"
				+ " SET m.betyg = '" + betyg + "' WHERE m.actor_id ="
				+"(SELECT id from actors WHERE id = " + id + ") AND m.module_id =(SELECT id from moduler WHERE modName = '" + modId + "' AND kurskod = '" + kurskod+ "');";
		//String test = id + betyg;

		try (Connection conn = dbConnect.createNewDBconnection()) {

			stmt = conn.createStatement();
			stmt.executeUpdate(sql_select);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return id;
	}
	
	//för post med object
public String visabetyg(String name) {
		
		String sql_select = "SELECT * FROM modulebetyg WHERE module_id IN(SELECT id FROM moduler WHERE name ='" + name + "');";
		List<Modules> moduler = new ArrayList<Modules>();
		String JSONOutput= "";

		try (Connection conn = dbConnect.createNewDBconnection()) {

			stmt = conn.createStatement();
			results = stmt.executeQuery(sql_select);
			
			while (results.next()) {
				Modules mod = new Modules();
				//mod.setId(Integer.valueOf(results.getString("id")));
				mod.setName(name);
				mod.setBetyg(results.getString("betyg"));
				moduler.add(mod);
			}

			ObjectMapper mapper = new ObjectMapper();
			JSONOutput = mapper.writeValueAsString(moduler);
			System.out.println(JSONOutput);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return JSONOutput;
	}

	// När man ska söka fritt på kurskod, sätt String kurskod som parameter. Använd
	// sedan den i ResponseMessage som Path parameter
	public String selectKurs(String kurskod, String modName) {
		//String sql_select = "Select * From actors where kurskod = '"+ kurskod +"'";
		String sql_select2 = "SELECT * FROM modulebetyg WHERE module_id IN(SELECT id FROM moduler WHERE name ='" + modName + "');";
		String sql_select3 = "SELECT actors.*, modulebetyg.betyg, moduler.kurskod, moduler.modName FROM actors JOIN modulebetyg ON actors.id = modulebetyg.actor_id " 
				+ " JOIN moduler ON modulebetyg.module_id = moduler.id WHERE module_id IN(SELECT id FROM moduler "
				+ " WHERE modName ='"+ modName +"' AND kurskod = '"+ kurskod +"');";
		String JSONOutput = "";
		List<GetActors> studentsList = new ArrayList<GetActors>();

		try (Connection conn = dbConnect.createNewDBconnection()) {

			stmt = conn.createStatement();
			results = stmt.executeQuery(sql_select3);

			while (results.next()) {

				GetActors actor = new GetActors();

				actor.setId(Integer.valueOf(results.getString("id")));
				actor.setFName(results.getString("fname"));
				actor.setLName(results.getString("lname"));
				actor.setKurskod(results.getString("kurskod"));
				actor.setBetygCanvas(results.getString("betygCanvas"));
				actor.setBetygLadok(results.getString("betyg"));
				actor.setModule(modName);

				studentsList.add(actor);
			}

			ObjectMapper mapper = new ObjectMapper();
			JSONOutput = mapper.writeValueAsString(studentsList);
			System.out.println(JSONOutput);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return JSONOutput;

	}

	public GetActors actorById(int actorId) {

		String JSONOutput = ""; // för att få resultatet i console som String, blir skevt annars
		String sql_select2 = "Select * from actors WHERE id = " + actorId + ";";
		GetActors actor = new GetActors();

		try (Connection conn = dbConnect.createNewDBconnection()) {

			stmt = conn.createStatement();
			results = stmt.executeQuery(sql_select2);
			while (results.next()) {
				actor.setId(Integer.valueOf(results.getString("id")));
				actor.setFName(results.getString("fname"));
				actor.setLName(results.getString("lname"));
				actor.setKurskod(results.getString("kurskod"));
				actor.setBetygCanvas(results.getString("betygCanvas"));
				actor.setBetygLadok(results.getString("betygLadok"));
			}

			ObjectMapper mapper = new ObjectMapper();
			JSONOutput = mapper.writeValueAsString(actor);
			System.out.println(JSONOutput);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return actor;

	}
	
	public String selectModule(String kurskod) {
		String sql_select = "Select * From moduler where kurskod = '"+ kurskod +"'";
		String JSONOutput = "";
		List<Modules> modulesList = new ArrayList<Modules>();

		try (Connection conn = dbConnect.createNewDBconnection()) {

			stmt = conn.createStatement();
			results = stmt.executeQuery(sql_select);

			while (results.next()) {

				Modules modules = new Modules();

				modules.setId(Integer.valueOf(results.getString("id")));
				modules.setName(results.getString("name"));
				modules.setKurskod(results.getString("kurskod"));


				modulesList.add(modules);
			}

			ObjectMapper mapper = new ObjectMapper();
			JSONOutput = mapper.writeValueAsString(modulesList);
			System.out.println(JSONOutput);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return JSONOutput;

	}

}
