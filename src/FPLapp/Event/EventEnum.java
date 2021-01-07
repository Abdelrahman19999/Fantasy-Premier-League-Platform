package FPLapp.Event;

public enum EventEnum {
	
	PLAYED_UPTO_60(1,0,"Played up to 60 minutes"),
	PLAYED_MORE_60(2,1,"Played more than 60 minutes"),
	GK_GOAL(6,2,"Goalkeeper goal"),
	DEF_GOAL(6,3,"Defender goal"),
	MF_GOAL(5,4,"Midfielder goal"),
	FWD_GOAL(4,5,"Forward goal"),
	ASSIST_GOAL(3,6,"Goal assist"),
	GK_SHEET(4,7,"Goalkeeper clean sheet"),
	DEF_SHEET(4,8,"Defender clean sheet"),
	MF_SHEET(1,9,"Midfielder clean sheet"),
	GK_THREE_SAVE(1,10,"Goalkeeper saved three balls"),
	GK_PENALTY_SAVE(5,11,"Goalkeeper saved a penalty"),
	PENALTY_MISS(-2,12,"Player missed a penalty"),
	FIRST_PLACE(3,13,"First place player"),
	SECOND_PLACE(2,14,"Second place player"),
	THIRD_PLACE(1,15,"Third place player"),
	GK_TWO_CGOAL(-1,16,"Goalkeeper conceded two goals"),
	DEF_TWO_CGOAL(-1,17,"Defender conceded two goals"),
	YELLOW_CARD(-1,18,"Player got a yellow card"),
	RED_CARD(-3,19,"Player got a red card"),
	OWN_GOAL(-2,20,"Player scored an own goal");
	
	private int action;
	private int order;
	private String name;
	
	EventEnum(int _action, int _order, String _name)
	{
		action = _action;
		order = _order;
		name = _name;
	}
	
	public int getPoints()
	{
		return this.action;
	}
	
	public int getOrder()
	{
		return this.order;
	}
	
	public String getName()
	{
		return this.name;
	}

}
