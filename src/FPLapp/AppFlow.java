package FPLapp;

import FPLapp.Player.*;
import FPLapp.Squad.*;
import FPLapp.User.*;
import java.util.ArrayList;

public class AppFlow {


	private User loggedUser;
	private PlayerDao playersList = new PlayerDaoFile();
	private SquadDao squadDao;
	public AppFlow(User user)
	{
		loggedUser = user;
	}

	public void addSquad(int[] IdsList) {
		loggedUser.setHasSquad("true");
		Squad squad = new Squad();
		SquadManager squadManager = new SquadManager(squad ,loggedUser);
		for(int i = 0; i < 15; i++){
			squadManager.addPlayer(playersList.getAllPlayers().get(IdsList[i]));
		}
		squadDao = new SquadDaoFile();
		squadDao.addSquad(squad.getOwner(), squad.getName(), squad.getInitValue(), squad.getPlayers());
		UserDao userDao = new UserDaoFile();
		userDao.updateUser(loggedUser);
	}
	
	public boolean addNewPlayer(String Name, String Nationality, String pos , String Club , int Cost)
	{
		Player player = new Player();
		player.setName(Name);
		player.setNationality(Nationality);
		PositionEnum position = playersList.pos_value(pos);
		if(position == null) {System.out.println("null"); return false;}
		else player.setPos(position);
		player.setCost(Cost);
		int newID = playersList.getAllPlayers().size();
		playersList.addPlayer(Name, Nationality, position, Club, Cost, newID);
		return true;
	}

	public void addNewAdmin(String email){
		UserDao userDao = new UserDaoFile();
		for(User user : userDao.getAllUsers()){
			if(user.getEmail().compareTo(email) == 0){
				user.setRole("admin");
				userDao.updateUser(user);
				break;
			}
		}
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	public boolean canAddSquad(){
		return !loggedUser.HasSquad();
	}

	public ArrayList<Player> getPlayersList(){
		return playersList.getAllPlayers();
	}
}
