package FPLapp;

import FPLapp.Player.Player;
import FPLapp.Player.PlayerDao;
import FPLapp.Player.PlayerDaoFile;
import FPLapp.Player.Position;
import FPLapp.Squad.Squad;
import FPLapp.Squad.SquadDao;
import FPLapp.Squad.SquadDaoFile;
import FPLapp.User.User;
import FPLapp.User.UserDao;
import FPLapp.User.UserDaoFile;

import java.util.Scanner;

public class AppFlow {
	
	private User loggedUser;
	private PlayerDao playersList = new PlayerDaoFile();
	private SquadDao squadDao;
	public AppFlow(User user)
	{
		loggedUser = user;
	}

	public void addSquad() {
		if(loggedUser.HasSquad()){
			System.out.println("User already has one squad!");
			return;
		}
		else{
			loggedUser.setHasSquad("true");
			Squad squad = new Squad();
			for(Player player : playersList.getAllPlayers()){
				System.out.println(player.getID() + "-" + player.getName() + "("+player.getPos()+")");
			}
			for(int i = 0; i < 15; i++){
				System.out.println("Enter player("+(i+1)+") ID to add to your squad: ");
				Scanner scanner = new Scanner(System.in);
				int id = -1;
				try {
					id = Integer.parseInt(scanner.nextLine());
				}
				catch (Exception e){
					System.out.println("Please enter a valid number");
				}
				while(!squad.addPlayer(playersList.getAllPlayers().get(id))){
					System.out.println("Cannot add player, please choose another player");
					try {
						id = Integer.parseInt(scanner.nextLine());
					}
					catch (Exception ignored){ }
				}
			}
			squad.setOwner(loggedUser.getEmail());
			squad.setName(loggedUser.getName() + "'s Squad");
			squadDao = new SquadDaoFile();
			squadDao.addSquad(squad.getOwner(), squad.getName(), squad.getInitValue(), squad.getPlayers());
			UserDao userDao = new UserDaoFile();
			userDao.updateUser(loggedUser);
		}
	}
	
	public boolean addNewPlayer(String Name, String Nationality, String pos , String Club , int Cost)
	{
		Player player = new Player();
		player.setName(Name);
		player.setNationality(Nationality);
		Position position = playersList.pos_value(pos);
		if(position == null) {System.out.println("null"); return false;}
		else player.setPos(position);
		player.setCost(Cost);
		int newID = playersList.getAllPlayers().size();
		playersList.addPlayer(Name, Nationality, position, Club, Cost, newID);
		return true;
	}

}
