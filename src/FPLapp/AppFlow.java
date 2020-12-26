package FPLapp;

import FPLapp.Player.Player;
import FPLapp.Player.PlayerDao;
import FPLapp.Player.PlayerDaoFile;
import FPLapp.Player.Position;
import FPLapp.User.User;

public class AppFlow {
	
	private User loggedUser;
	private PlayerDao playerdata;
	
	public AppFlow(User user)
	{
		loggedUser = user;
		playerdata = new PlayerDaoFile();
	}
	
	public void perform() {};
	
	public void addSquad() {};
	
	public boolean addNewPlayer(String Name, String Nationality, String pos , String Club , int Cost)
	{
		Player player = new Player();
		player.setName(Name);
		player.setNationality(Nationality);
		Position position = playerdata.pos_value(pos);
		if(position == null) return false;
		else player.setPos(position);
		player.setCost(Cost);
		playerdata.addPlayer(player);
		return true;
	}

}
