package FPLapp.Event;

import java.util.ArrayList;
import FPLapp.Player.Player;
import FPLapp.Utility.myPair;

public class Event {
	int gameWeek;
	Match match;
	myPair<EventEnum, Player> event = new myPair<EventEnum, Player>();
	private ArrayList<EventObserver> observers = new ArrayList<EventObserver>();
	
	public void registerObserver(EventObserver eventOb)
	{
		observers.add(eventOb);
	}
	
	public void removeObserver(EventObserver eventOb)
	{
		observers.remove(observers.indexOf(eventOb));
	}
	
	public myPair<EventEnum, Player> getEvent()
	{
		return event;
	}
	
	public void createEvent(EventEnum evenum, Player player)
	{
		event.setValue(evenum, player);
		notifyObservers();
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
	
	public void getPlayerInfo() {};
	public void getMatchInfo() {};
	
}
