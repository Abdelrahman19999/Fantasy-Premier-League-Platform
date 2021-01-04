package FPLapp.Squad;
import FPLapp.Player.*;

import java.util.ArrayList;

public class Squad {
    private String name;
    private double initValue = 0.0;
    private double totalValue = 100.0;
    private String owner;
    private int ID;
    private ArrayList<Player> players = new ArrayList<>();

    protected void reloadPlayer(Player p){
        players.add(p);
    }
    public void setPlayer(String playerName){
        PlayerDao playerDao = new PlayerDaoFile();
        for (Player p : playerDao.getAllPlayers()){
            if(p.getName().compareTo(playerName) == 0)
                this.reloadPlayer(p);
        }
    }
    public void removePlayer(Player p){
        players.remove(p);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public double getInitValue() {
        return initValue;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getID() {
        return ID;
    }

    public void setInitValue(double value) {
        initValue = value;
    }
}
