package FPLapp.User;

public class User {
	private String Name;
	private String Email;
	private String Password;
	private int ID;
	
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
	
	public void setID(int ID)
	{
		this.ID = ID;
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
	
	public int getID()
	{
		return ID;
	}
	
}
