package FPLapp.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;



public class PlayerDaoFile implements PlayerDao{

	private ArrayList<Player> Players;

	public PlayerDaoFile() {
        Players = new ArrayList<Player>();
		loadPlayers();
	}

	@Override
    public ArrayList<Player> getAllPlayers() {
        return Players;
    }

	@Override
    public void updatePlayer(Player player) {
    	Players.set(player.getID(), player);
    }

	@Override
    public void addPlayer(Player player) {
    	Players.add(player);
    	savePlayers();
    }

	public void addPlayer(String Name, String Nationality, Position pos , String Club , int Cost , int ID)
	{
		Player player = new Player();
		player.setName(Name);
		player.setNationality(Nationality);
		player.setPos(pos);
		player.setClub(Club);
		player.setCost(Cost);
		player.setID(ID);
		Players.add(player);
		savePlayers();
	}

	@Override
    public boolean deletePlayer(Player player) {
        try
        {
        	Players.remove(player.getID());
        	savePlayers();
        	return true;
        }
        catch(Exception e)
        {
        	return false;
        }
    }

	public Position pos_value(String s) {

		if(s=="DF")return  Position.DF ;

		if(s=="FW")return  Position.FW ;

		if(s=="GK")return  Position.GK ;

		if(s=="MF")return  Position.MF ;
	}

	public void loadPlayers()
	{
		try {
			File db = new File("src\\FPLapp\\Database\\Players.txt");
			Scanner reader = new Scanner(db);
			while(reader.hasNextLine())
			{
				Player player = new Player();
				player.setName(reader.nextLine());
				player.setNationality(reader.nextLine());
				player.setPos(pos_value(reader.nextLine()));
				player.setClub(reader.nextLine());
				player.setCost(Integer.parseInt(reader.nextLine()));
				player.setID(Integer.parseInt(reader.nextLine()));
				addPlayer(player);
			}
			reader.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void savePlayers()
	{
		try {
			Writer fileWriter = new FileWriter("src\\FPLapp\\Database\\Players.txt", false);
			for(Player player : Players)
			{
				fileWriter.write(player.getName() + "\n");
				fileWriter.write(player.getNationality() + "\n");
				fileWriter.write(player.getPos().toString() + "\n");
				fileWriter.write(player.getClub() + "\n");
				fileWriter.write(player.getCost() + "\n");
				fileWriter.write(player.getID() + "\n");
			}
			fileWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
