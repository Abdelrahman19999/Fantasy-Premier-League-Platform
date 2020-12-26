package FPLapp.Player;

import java.util.ArrayList;

public interface PlayerDao {
     ArrayList<Player> getAllPlayers();
     void updatePlayer(Player player);
     void addPlayer(Player player);
     boolean deletePlayer(Player player);
     void addPlayer(String Name, String Nationality, Position pos , String Club , int Cost , int ID);
     Position pos_value(String s);
}
