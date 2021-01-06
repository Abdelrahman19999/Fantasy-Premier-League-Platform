package FPLapp.Event;

import java.util.ArrayList;
import FPLapp.Player.Player;
import FPLapp.Player.PlayerDao;
import FPLapp.Player.PlayerDaoFile;

public class Match {
	private Boolean ongoing;
	private String club1;
	private String club2;
	
	private ArrayList<Player> participants = new ArrayList<Player>();
	private PlayerDao playerDAO = new PlayerDaoFile();
	
	public ArrayList<Player> getParticipants()
	{
		return participants;
	}
	
	public Boolean getOngoing()
	{
		return ongoing;
	}
	
	private void load_participants()
	{
		ArrayList<Player> players = playerDAO.getAllPlayers();
		for(Player player : players)
		{
			if(player.getClub().compareTo(club1) == 0 || player.getClub().compareTo(club2) == 0)
			{
				participants.add(player);
			}
		}
	}
	
	public Match(String _club1, String _club2)
	{
		ongoing = false;
		club1 = _club1;
		club2 = _club2;
	}
	
	
	public void start()
	{
		ongoing = true;
		load_participants();
	}
	
	public void end()
	{
		ongoing = false;
	}

}
