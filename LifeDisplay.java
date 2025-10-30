import greenfoot.*;

public class LifeDisplay extends Actor {
    private int lives;

    public LifeDisplay() {
        updateLives(3);
    }

    public void updateLives(int newLives) {
        lives = newLives;

        String filename;
        if (lives >= 3) filename = "lifee1.png";
        else if (lives == 2) filename = "lifee2.png";
        else if (lives == 1) filename = "lifee3.png";
        else filename = "lifee0.png";

        try {
            GreenfootImage img = new GreenfootImage(filename);
            img.scale(80, 24);
            setImage(img);
        } catch (Exception e) {
            GreenfootImage fallback = new GreenfootImage("Lives: " + lives, 24, Color.WHITE, new Color(0, 0, 0, 0));
            setImage(fallback);
        }
    }
}
