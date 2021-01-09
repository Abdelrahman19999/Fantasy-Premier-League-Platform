package FPLapp.Squad.Score;

public class Score {
	private int gameweek;
	private String owner;
	private int score;
	
	public Score(int gameweek, String owner, int score)
	{
		this.gameweek = gameweek;
		this.owner = owner;
		this.score = score;
	}
	
	public Score() {}
	
	public int getGameweek() {
		return gameweek;
	}
	public void setGameweek(int gameweek) {
		this.gameweek = gameweek;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

}
