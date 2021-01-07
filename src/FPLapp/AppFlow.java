package FPLapp;

import FPLapp.Event.Event;
import FPLapp.Event.EventEnum;
import FPLapp.Player.*;
import FPLapp.Squad.*;
import FPLapp.User.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AppFlow {


	private User loggedUser;
	private PlayerDao playersDAO = new PlayerDaoFile();
	private SquadDao squadDao;
	private Event event;
	private EventEnum evenum;
	private Scanner sc;
	
	public AppFlow(User user)
	{
		loggedUser = user;
	}
	
	public void initiateEvent(int gameweek)
	{
		event = new Event(gameweek);
		new PlayerManager(event);
	}
	
	public void createMatch(String team1, String team2)
	{
		event.createMatch(team1, team2);
	}
	
	public Boolean startMatch()
	{
		if(event.getMatchState() == false)
		{
			if(event.startMatch())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public Boolean endMatch()
	{
		if(event.getMatchState() == true)
		{
			if(event.endMatch())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public Boolean createEvent()
	{
		if(event.getMatchState() == false) return false;
		sc = new Scanner(System.in);
		int i = 1;
		for(EventEnum events : EventEnum.values())
		{
			System.out.println(i + "- " + events.getName());
			i++;
		}
		System.out.println("Enter your choice: ");
		int choice = Integer.parseInt(sc.nextLine());
		if(choice < 1 || choice > 21) return false;
		for(EventEnum events : EventEnum.values())
		{
			if(events.getOrder() == (choice-1))
			{
				evenum = events;
			}
		}
		ArrayList<Player> participants = event.getMatchPlayers();
		for(i = 0; i < participants.size(); i++)
		{
             System.out.println((i+1) + "- " + participants.get(i).getName() + "("+participants.get(i).getPos()+")");
        }
		System.out.println("Enter player to associate with event: ");
		choice = Integer.parseInt(sc.nextLine());
		if(choice < 1 || choice > participants.size()) return false;
		Player player = participants.get(choice-1);
		event.createEvent(evenum, player);
		return true;
	}

	public void addSquad(int[] IdsList) {
		loggedUser.setHasSquad("true");
		Squad squad = new Squad();
		SquadManager squadManager = new SquadManager(squad ,loggedUser);
		for(int i = 0; i < 15; i++){
			squadManager.addPlayer(playersDAO.getAllPlayers().get(IdsList[i]));
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
		PositionEnum position = playersDAO.pos_value(pos);
		if(position == null) {System.out.println("null"); return false;}
		else player.setPos(position);
		player.setCost(Cost);
		int newID = playersDAO.getAllPlayers().size();
		playersDAO.addPlayer(Name, Nationality, position, Club, Cost, newID);
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
		return playersDAO.getAllPlayers();
	}
}
