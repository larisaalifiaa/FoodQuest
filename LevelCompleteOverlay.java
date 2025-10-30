import greenfoot.*;

public class LevelCompleteOverlay extends Actor
{
    private GreenfootSound winSound = new GreenfootSound("win.wav");
    private boolean soundPlayed = false;

    public LevelCompleteOverlay()
    {
        GreenfootImage img = new GreenfootImage("win1.png");
        setImage(img);
    }

    public void act()
    {
        if (!soundPlayed)
        {
            winSound.setVolume(80);
            winSound.play();        
            soundPlayed = true;     
        }
    }
}