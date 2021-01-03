package FPLapp.User;

import FPLapp.Squad.Squad;

public class User {
	private String Name;
	private String Email;
	private String Password;
	private String favTeam;
	private int ID;
	private Squad squad;
	private boolean hasSquad = false;
	private String role = "coach";

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setName(String Name)
	{
		this.Name = Name;
	}

	public void setEmail(String Email)
	{
		this.Email = Email;
	}

	public void setPassword(String Password)
	{
		this.Password = Password;
	}

	public void setFavTeam(String favTeam)
	{
		this.favTeam = favTeam;
	}

	public void setID(int ID)
	{
		this.ID = ID;
	}

	public void setSquad(Squad squad) {
		this.squad = squad;
	}

	public void setHasSquad(boolean hasSquad) {
		this.hasSquad = hasSquad;
	}

	public void setHasSquad(String hasSquad) {
		this.hasSquad = hasSquad.equals("true") ? true : false;
	}

	public String getName()
	{
		return Name;
	}

	public String getEmail()
	{
		return Email;
	}

	public String getPassword()
	{
		return Password;
	}

	public String getFavTeam()
	{
		return favTeam;
	}

	public int getID()
	{
		return ID;
	}

	public Squad getSquad() {
		return squad;
	}
	public boolean HasSquad() {
		return hasSquad;
	}
}
