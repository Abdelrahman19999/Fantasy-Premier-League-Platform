package FPLapp.Event;

import java.util.ArrayList;

public class Event {
	int gameWeek;
	private ArrayList<EventObserver> observers;
	
	public void registerObserver(EventObserver eventOb)
	{
		observers.add(eventOb);
	}
	
	public void removeObserver(EventObserver eventOb)
	{
		observers.remove(observers.indexOf(eventOb));
	}
	
	public void notifyObserver()
	{
		for(EventObserver ob : observers)
		{
			ob.update();
		}
	}
	
	public void getPlayerInfo() {};
	public void getMatchInfo() {};
	
}
