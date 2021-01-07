package FPLapp.Player;

import java.util.ArrayList;

import FPLapp.Event.Event;
import FPLapp.Event.EventObserver;

public class PlayerManager extends EventObserver {
	
	private PlayerDao playerDAO = new PlayerDaoFile();
	private ArrayList<Player> players = new ArrayList<Player>();
	
	public PlayerManager(Event event) {
        this.event = event;
        this.event.registerObserver(this);
    }

	@Override
	public void update() {
		// Retrieve the state, then add the EventEnum value to Player point, then save changes with PlayerDAO.
		int index = this.event.getState().second.getID();
		players = playerDAO.getAllPlayers();
		Player player = players.get(index);
		int points = player.getPoints() + this.event.getState().first.getPoints();
		player.setPoints(points);
		playerDAO.updatePlayer(player);
	}

}
