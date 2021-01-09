package FPLapp.Squad.Score;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreDaoFile implements ScoreDao {
	
	private ArrayList<Score> Scores;
	
	public ScoreDaoFile()
	{
		Scores = new ArrayList<Score>();
		loadScores();
	}

	public void loadScores() 
	{
		try
		{
			File db = new File("src\\FPLapp\\Database\\scores.txt");
			Scanner reader = new Scanner(db);
			while(reader.hasNextLine())
			{
				Score score = new Score();
				score.setGameweek(Integer.parseInt(reader.nextLine()));
				score.setOwner(reader.nextLine());
				score.setScore(Integer.parseInt(reader.nextLine()));
				addScore(score);
			}
			reader.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void saveScores() {
		try {
			Writer fileWriter = new FileWriter("src\\FPLapp\\Database\\scores.txt", false);
			for(Score score : Scores)
			{
				fileWriter.write(score.getGameweek() + "\n");
				fileWriter.write(score.getOwner() + "\n");
				fileWriter.write(score.getScore() + "\n");
			}
			fileWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public ArrayList<Score> getAllScores() {
		return Scores;
	}

	@Override
	public void updateScore(Score score) {
		boolean found = false;
		for(int i = 0; i < Scores.size(); i++)
		{
			Score scr = Scores.get(i);
			if(scr.getGameweek() == score.getGameweek() && scr.getOwner().compareTo(score.getOwner()) == 0)
			{
				int points = scr.getScore();
				score.setScore(points + score.getScore());
				Scores.set(i, score);
				found = true;
				saveScores();
				break;
			}
		}
		if(!found)
		{
			addScore(score.getGameweek(), score.getOwner(), score.getScore());
		}
		
	}

	@Override
	public void addScore(Score score) {
		Scores.add(score);
	}

	@Override
	public boolean deleteScore(Score score) {
		for(Score scr : Scores)
		{
			if(scr.getGameweek() == score.getGameweek() && scr.getOwner().compareTo(score.getOwner()) == 0)
			{
				Scores.remove(scr);
				return true;
			}
		}
		return false;
	}
	
	public void addScore(int gameweek, String owner, int sc)
	{
		Score score = new Score();
		score.setGameweek(gameweek);
		score.setOwner(owner);
		score.setScore(sc);
		Scores.add(score);
		saveScores();
	}

}
