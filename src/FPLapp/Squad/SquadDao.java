package FPLapp.Squad;

import FPLapp.Player.Player;

import java.util.ArrayList;

public interface SquadDao {
    ArrayList<Squad> getAllSquads();
    void updateSquad(Squad squad);
    void addSquad(Squad squad);
    boolean deleteSquad(Squad squad);
    void addSquad(String userEmail, String Name, double initValue, ArrayList<Player> playersList);
}
