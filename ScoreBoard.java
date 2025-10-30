import greenfoot.*;

public class ScoreBoard extends Actor
{
    private int score = 0;

    public ScoreBoard()
    {
        updateScore(0);
    }

    public void updateScore(int newScore)
    {
        score = newScore;

        int displayScore = Math.min(score, 210);
        String filename = "score" + displayScore + ".png";

        GreenfootImage img = new GreenfootImage(filename);

        img.scale(70, 25);

        setImage(img);
    }

    public int getScore()
    {
        return score;
    }
}