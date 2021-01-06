package FPLapp.Player;

import FPLapp.Event.Event;
import FPLapp.Event.EventObserver;

public class PlayerManager extends EventObserver {
	
	public PlayerManager(Event event) {
        this.event = event;
        this.event.registerObserver(this);
    }

	@Override
	public void update() {
		// Retrieve the state, then add the EventEnum value to Player point, then save changes with PlayerDAO.
		
	}

}
