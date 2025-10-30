import greenfoot.*;

public class GameOverScreen extends Actor
{
    private int finalScore;
    private GreenfootSound habisNyawaSound = new GreenfootSound("habisnyawa.wav");
    private GreenfootSound gameOverSound = new GreenfootSound("gameover.mp3");

    private boolean habisNyawaPlayed = false;
    private boolean gameOverPlayed = false;
    private int soundCounter = 0;
    private final int SOUND_DELAY = 60;
    private final int GAMEOVER_DURATION = 900; 

    public GameOverScreen(int score)
    {
        finalScore = score;
        createImage();
    }

    private void createImage()
    {
        GreenfootImage img = new GreenfootImage(400, 200);
        img.setColor(new Color(0, 0, 0, 200));
        img.fillRect(0, 0, 400, 200);

        img.setColor(Color.RED);
        img.setFont(new Font("Arial", true, false, 40));
        img.drawString("GAME OVER", 80, 80);

        img.setColor(Color.WHITE);
        img.setFont(new Font("Arial", false, false, 24));
        img.drawString("Final Score: " + finalScore, 110, 130);
        img.drawString("Press Ctrl+R to Restart", 90, 170);

        setImage(img);
    }

    public void act()
    {
        if (!habisNyawaPlayed)
        {
            habisNyawaSound.play();
            habisNyawaPlayed = true;
        }

        if (habisNyawaPlayed && !gameOverPlayed && soundCounter >= SOUND_DELAY)
        {
            gameOverSound.play();
            gameOverPlayed = true;
        }

        soundCounter++;

        if (gameOverPlayed && !gameOverSound.isPlaying() && soundCounter >= GAMEOVER_DURATION)
        {
            Greenfoot.stop();
        }

        if (Greenfoot.isKeyDown("r"))
        {
            Greenfoot.setWorld(new MyWorld());
        }
    }
}
