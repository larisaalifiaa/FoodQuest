import greenfoot.*;

public class CreditButton extends Actor implements Clickable
{
    private GreenfootSound clickSound;
    
    public CreditButton()
    {
        setImage("Credit.png");
        GreenfootImage image = getImage();
        image.scale(image.getWidth() / 2, image.getHeight() / 2);
        setImage(image);
        
        try {
            clickSound = new GreenfootSound("start.mp3");
        } catch (Exception e) {
            clickSound = null;
        }
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
        if (clickSound != null) {
            clickSound.play();
        }
        
        MyWorld world = (MyWorld) getWorld();
        world.showCredits();
    }
}