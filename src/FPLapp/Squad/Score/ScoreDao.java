package FPLapp.Squad.Score;

import java.util.ArrayList;

public interface ScoreDao {
	ArrayList<Score> getAllScores();
    void updateScore(Score score);
    void addScore(Score score);
    boolean deleteScore(Score score);
}
