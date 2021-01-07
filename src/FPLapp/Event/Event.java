package FPLapp.Event;

import java.util.ArrayList;
import FPLapp.Player.Player;
import FPLapp.Utility.myPair;

public class Event {
	int gameWeek;
	private Match match;
	private myPair<EventEnum, Player> state = new myPair<EventEnum, Player>();
	private ArrayList<EventObserver> observers = new ArrayList<EventObserver>();
	
	public Event(int _gameWeek)
	{
		gameWeek = _gameWeek;
	}
	
	public void registerObserver(EventObserver eventOb)
	{
		observers.add(eventOb);
	}
	
	public void removeObserver(EventObserver eventOb)
	{
		observers.remove(observers.indexOf(eventOb));
	}
	
	public myPair<EventEnum, Player> getState()
	{
		return state;
	}
	
	public Boolean createEvent(EventEnum evenum, Player player)
	{
		try 
		{
			if(match.getOngoing())
			{
				state.setValue(evenum, player);
				notifyObservers();
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	private void notifyObservers()
	{
		for(EventObserver observer : observers)
		{
			observer.update();
		}
	}
	
	public void createMatch(String team1, String team2)
	{
		match = new Match(team1, team2);
	}
	
	public Boolean startMatch()
	{
		try
		{
			match.start();
			return true;
		}
		catch(Exception E)
		{
			return false;
		}
	}
	
	public Boolean endMatch()
	{
		try
		{
			match.end();
			return true;
		}
		catch(Exception E)
		{
			return false;
		}
	}
	
	public ArrayList<Player> getMatchPlayers() {
		if(match.getOngoing())
		{
			return match.getParticipants();
		}
		else return null;
	};
	
	public Boolean getMatchState() {
		try
		{
			return match.getOngoing();
		}
		catch(Exception e)
		{
			return false;
		}
	};
	
}
