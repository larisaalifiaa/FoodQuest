import greenfoot.*;

public class FlyingPan extends Enemy
{
    private int startY;
    private int moveRange = 100;

    private GreenfootImage[] flyFrames;
    private int animationCounter = 0;
    private int currentFrame = 0;

    public FlyingPan()
    {
        loadImages();
        speed = 3;
        direction = 1;
    }

    private void loadImages()
    {
        flyFrames = new GreenfootImage[3];
        flyFrames[0] = new GreenfootImage("flyingpan.png");
        flyFrames[1] = new GreenfootImage("flyingpan1.png");
        flyFrames[2] = new GreenfootImage("flyingpan2.png");
        setImage(flyFrames[0]);
    }

    @Override
    protected void addedToWorld(World world)
    {
        startY = getY();
    }

    @Override
    public void act()
    {
        moveVertical();
        updateAnimation();
    }

    private void moveVertical()
    {
        setLocation(getX(), getY() + (speed * direction));

        if (Math.abs(getY() - startY) > moveRange || getY() <= 50 || getY() >= getWorld().getHeight() - 50)
        {
            direction *= -1;
        }
    }

    private void updateAnimation()
    {
        animationCounter++;

        if (animationCounter % 5 == 0)
        {
            currentFrame = (currentFrame + 1) % flyFrames.length;
            setImage(flyFrames[currentFrame]);
        }
    }
}