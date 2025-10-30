import greenfoot.*;

public class Platform extends Actor
{
    public Platform()
    {
        this(18, 18);
    }
    
    public Platform(int width, int height)
    {
        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(Color.GREEN);
        img.fillRect(0, 0, width, height);
        setImage(img);
    }
    
    public void act()
    {
    }
}   