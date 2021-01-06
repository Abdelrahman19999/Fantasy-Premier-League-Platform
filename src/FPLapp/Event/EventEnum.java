package FPLapp.Event;

public enum EventEnum {
	
	PLAYED_UPTO_60(1),
	PLAYED_MORE_60(2),
	GK_GOAL(6),
	DEF_GOAL(6),
	MF_GOAL(5),
	FWD_GOAL(4),
	ASSIST_GOAL(3),
	GK_SHEET(4),
	DEF_SHEET(4),
	MF_SHEET(1),
	GK_THREE_SAVE(1),
	GK_PENALTY_SAVE(5),
	PENALTY_MISS(-2),
	FIRST_PLACE(3),
	SECOND_PLACE(2),
	THIRD_PLACE(1),
	GK_TWO_CGOAL(-1),
	DEF_TWO_CGOAL(-1),
	YELLOW_CARD(-1),
	RED_CARD(-3),
	OWM_GOAL(-2);
	
	private int action;
	
	EventEnum(int _action)
	{
		action = _action;
	}
	
	public int getPoints()
	{
		return this.action;
	}

}
