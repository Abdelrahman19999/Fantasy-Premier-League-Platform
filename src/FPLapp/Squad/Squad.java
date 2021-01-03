package FPLapp.Squad;
import FPLapp.Player.Player;
import FPLapp.Player.PlayerDao;
import FPLapp.Player.PlayerDaoFile;

import java.util.ArrayList;

public class Squad {
    private String name;
    private double initValue = 0.0;
    private double totalValue = 100.0;
    private String owner;
    private int ID;
    private ArrayList<Player> players = new ArrayList<>();

    protected boolean isApplicable(Player p){
        if(p.getCost() + initValue > totalValue)
            return false;
        else if (players.size() >= 15)
            return false;
        else {

            String pClub = p.getClub();
            int cnt = 0;
            int posCnt = 0;
            for (Player player : players){
                if(player.getClub().compareTo(pClub) == 0){
                    cnt++;
                    if(cnt >= 3) return false;
                }
                if(player.getPos().compareTo(p.getPos()) == 0){
                    posCnt++;
                }
                if(player.getName().compareTo(p.getName()) == 0) {
                    return false;
                }
            }
            switch (p.getPos()){
                case GK:
                    if(posCnt >= 2) return false;
                    break;
                case DF:
                case MF:
                    if(posCnt >= 5) return false;
                    break;
                case FW:
                    if(posCnt >= 3) return false;
            }
        }
        return true;
    }
    public boolean addPlayer(Player p){
        if(isApplicable(p)) {
            players.add(p);
            initValue += p.getCost();
            return true;
        }
        else
            return false;
    }
    rotected void reloadPlayer(Player p){
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
    public boolean replacePlayer(Player pold, Player pnew){
        removePlayer(pold);
        if(addPlayer(pnew))
            return true;
        else{
            addPlayer(pold);
            return false;
        }
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
