import greenfoot.*;
public class StartButton extends Actor implements Clickable
{
    private GreenfootSound startSound = new GreenfootSound("start.mp3");
    public StartButton()
    {
        setImage("Start.png");
        GreenfootImage image = getImage();
        int scaledWidth = image.getWidth() / 2;
        int scaledHeight = image.getHeight() / 2;
        image.scale(scaledWidth, scaledHeight);
        setImage(image);
    }
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            onClick();
        }
    }
    @Override
    public void onClick()
    {
        startSound.play();
        MyWorld world = (MyWorld) getWorld();
        world.startGame();
    }
}