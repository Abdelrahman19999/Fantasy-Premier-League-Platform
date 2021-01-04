package FPLapp.Squad;

import FPLapp.Player.*;
import FPLapp.User.*;

public class SquadManager {
    private Squad squad;
    public SquadManager(Squad squad , User user){
        squad.setOwner(user.getEmail());
        squad.setName(user.getName() + "'s Squad");
        this.squad = squad;
    }

    protected boolean isApplicable(Player p){
        if(p.getCost() + squad.getInitValue() > squad.getTotalValue())
            return false;
        else if (squad.getPlayers().size() >= 15)
            return false;
        else {
            String pClub = p.getClub();
            int cnt = 0;
            int posCnt = 0;
            for (Player player : squad.getPlayers()){
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
            squad.getPlayers().add(p);
            squad.setInitValue(squad.getInitValue() + p.getCost());
            return true;
        }
        else
            return false;
    }

    public void removePlayer(Player p){
        squad.getPlayers().remove(p);
        squad.setInitValue(squad.getInitValue() - p.getCost());
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

    public Squad getSquad() {
        return squad;
    }

    public void setSquad(Squad squad) {
        this.squad = squad;
    }

}
