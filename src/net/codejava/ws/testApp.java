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

				GetActors stdObject = new GetActors();

				stdObject.setId(Integer.valueOf(results.getString("id")));
				stdObject.setFName(results.getString("fname"));
				stdObject.setLName(results.getString("lname"));
				// stdObject.setAddress(results.getString("Address"));
				// stdObject.setCourse_code(results.getString("course_code"));

				studentsList.add(stdObject);
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

				GetActors stdObject = new GetActors();

				stdObject.setId(Integer.valueOf(results.getString("id")));
				stdObject.setFName(results.getString("fname"));
				stdObject.setLName(results.getString("lname"));
				stdObject.setKurskod(results.getString("kurskod"));
				stdObject.setBetygCanvas(results.getString("betygCanvas"));
				stdObject.setBetygLadok(results.getString("betygLadok"));
				// stdObject.setAddress(results.getString("Address"));
				// stdObject.setCourse_code(results.getString("course_code"));

				studentsList.add(stdObject);
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
	public int betygLadok(int id, String betyg) {
		
		String sql_select = "UPDATE actors SET betygLadok = '" + betyg + "' Where id = " + id +";";
		String test = id + betyg;

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
public GetActors betygLadok2(int id, String betyg) {
		
		String sql_select = "UPDATE actors SET betygLadok = '" + betyg + "' Where id = " + id +";";
		GetActors stdObject = new GetActors();
		stdObject.setId(id);
		stdObject.setBetygLadok(betyg);

		try (Connection conn = dbConnect.createNewDBconnection()) {

			stmt = conn.createStatement();
			stmt.executeUpdate(sql_select);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return stdObject;
	}

	// När man ska söka fritt på kurskod, sätt String kurskod som parameter. Använd
	// sedan den i ResponseMessage som Path parameter
	public String selectKurs(String kurskod) {
		String sql_select = "Select * From actors where kurskod = '"+ kurskod +"'";
		String JSONOutput = "";
		List<GetActors> studentsList = new ArrayList<GetActors>();

		try (Connection conn = dbConnect.createNewDBconnection()) {

			stmt = conn.createStatement();
			results = stmt.executeQuery(sql_select);

			while (results.next()) {

				GetActors stdObject = new GetActors();

				stdObject.setId(Integer.valueOf(results.getString("id")));
				stdObject.setFName(results.getString("fname"));
				stdObject.setLName(results.getString("lname"));
				stdObject.setKurskod(results.getString("kurskod"));
				stdObject.setBetygCanvas(results.getString("betygCanvas"));
				stdObject.setBetygLadok(results.getString("betygLadok"));
				// stdObject.setAddress(results.getString("Address"));
				// stdObject.setCourse_code(results.getString("course_code"));

				studentsList.add(stdObject);
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
		GetActors stdObject = new GetActors();

		try (Connection conn = dbConnect.createNewDBconnection()) {

			stmt = conn.createStatement();
			results = stmt.executeQuery(sql_select2);
			while (results.next()) {
				stdObject.setId(Integer.valueOf(results.getString("id")));
				stdObject.setFName(results.getString("fname"));
				stdObject.setLName(results.getString("lname"));
				stdObject.setKurskod(results.getString("kurskod"));
				stdObject.setBetygCanvas(results.getString("betygCanvas"));
				stdObject.setBetygLadok(results.getString("betygLadok"));
			}

			ObjectMapper mapper = new ObjectMapper();
			JSONOutput = mapper.writeValueAsString(stdObject);
			System.out.println(JSONOutput);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return stdObject;

	}

}
