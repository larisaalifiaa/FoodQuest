import greenfoot.*;

/**
 * Layar Menu Utama Game
 */
public class MainMenuWorld extends World {
    private GreenfootSound menuMusic;

    public MainMenuWorld() {
        super(800, 600, 1);
        setBackground("BackGround.png");

        addObject(new StartButton(), 400, 350);
        addObject(new CreditButton(), 400, 430);
        addObject(new InfoButton(), 80, 50);

        menuMusic = new GreenfootSound("intro.mp3");
        menuMusic.playLoop();
    }

    public void act() {
    }

    public void stopMusic() {
        if (menuMusic != null && menuMusic.isPlaying()) menuMusic.stop();
    }
}
