import greenfoot.*;

public class SpikyBall extends Enemy
{
    private int rotationSpeed = 5;
    
    public SpikyBall()
    {
        setImage("spikyball.png");
    }
    
    public void act()
    {
        moveAndRotate();
    }
    
    private void moveAndRotate()
    {
        setLocation(getX() + (speed * direction), getY());
        
        turn(rotationSpeed);
        
        if(getX() <= 20 || getX() >= getWorld().getWidth() - 20)
        {
            direction *= -1;
        }
        
        if(isTouching(Platform.class))
        {
            direction *= -1;
        }
    }
}