package FPLapp.Player;

public class Player {
	private String Name;
	private String Nationality;
	private PositionEnum pos;
	private String Club;
	private int Cost;
	private int ID;
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getNationality() {
		return Nationality;
	}

	public void setNationality(String nationality) {
		this.Nationality = nationality;
	}

	public PositionEnum getPos() {
		return pos;
	}

	public void setPos(PositionEnum pos) {
		this.pos = pos;
	}

	public String getClub() {
		return Club;
	}

	public void setClub(String club) {
		this.Club = club;
	}

	public int getCost() {
		return Cost;
	}

	public void setCost(int cost) {
		this.Cost = cost;
	}

    public int getID() {
		return ID;
	}

	public void setID(int id) {
		this.ID = id;
	}
}
