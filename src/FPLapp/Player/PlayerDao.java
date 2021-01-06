package FPLapp.Player;

import java.util.ArrayList;

public interface PlayerDao {
     ArrayList<Player> getAllPlayers();
     void updatePlayer(Player player);
     void addPlayer(Player player);
     boolean deletePlayer(Player player);
     void addPlayer(String Name, String Nationality, PositionEnum pos , String Club , int Cost , int ID);
     PositionEnum pos_value(String s);
}
