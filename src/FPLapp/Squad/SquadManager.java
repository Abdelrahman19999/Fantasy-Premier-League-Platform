package FPLapp.Squad;

import java.util.ArrayList;

import FPLapp.Event.Event;
import FPLapp.Event.EventObserver;
import FPLapp.Player.Player;
import FPLapp.Squad.Score.*;

public class SquadManager extends EventObserver {
	
	private SquadDao squadDAO = new SquadDaoFile();
	private ScoreDao scoreDAO = new ScoreDaoFile();
	
	private ArrayList<Squad> squads = squadDAO.getAllSquads();
	private ArrayList<Player> players;
	
	public SquadManager(Event event) {
        this.event = event;
        this.event.registerObserver(this);
    }
	
	@Override
	public void update() {
		int gk = this.event.getGameweek();
		int PlayerID = this.event.getState().second.getID();
		int points = this.event.getState().first.getPoints();
		
		for(Squad squad : squads)
		{
			players = squad.getPlayers();
			for(Player player : players)
			{
				if(player.getID() == PlayerID)
				{
					Score score = new Score(gk, squad.getOwner(), points);
					scoreDAO.updateScore(score);
				}
			}
		}
	}
}
