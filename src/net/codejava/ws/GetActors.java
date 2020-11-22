package net.codejava.ws;

public class GetActors {
	
	private int id;
	private String fname;
	private String lname;
	private String kurskod;
	private String betygCanvas;
	private String betygLadok;
//	private String address;
//	private String course_code;
	

	public String getBetygLadok() {
		return betygLadok;
	}
	public void setBetygLadok(String betygLadok) {
		this.betygLadok = betygLadok;
	}
	public String getBetygCanvas() {
		return betygCanvas;
	}
	public void setBetygCanvas(String betygCanvas) {
		this.betygCanvas = betygCanvas;
	}
	public String getKurskod() {
		return kurskod;
	}
	public void setKurskod(String kurskod) {
		this.kurskod = kurskod;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	public String getFName() {
		return fname;
	}
	public void setFName(String fname) {
		this.fname = fname;
	}
	
	public String getLName() {
		return lname;
	}
	public void setLName(String lname) {
		this.lname = lname;
	}
		

}
