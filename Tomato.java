import greenfoot.*;

public class Tomato extends Food
{
    private GreenfootImage[] frames;
    private int animationCounter = 0;
    private int currentFrame = 0;
    
    public Tomato()
    {
        loadImages();
    }
    
    private void loadImages()
    {
        frames = new GreenfootImage[2];
        frames[0] = new GreenfootImage("tomato.png");
        frames[1] = new GreenfootImage("tomatoidle1.png");

        setImage(frames[0]);
    }
    
    public void act()
    {
        updateAnimation();
    }
    
    private void updateAnimation()
    {
        animationCounter++;
        
        if(animationCounter % 15 == 0)
        {
            currentFrame = (currentFrame + 1) % 2;
            setImage(new GreenfootImage(frames[currentFrame]));
        }
        
    }
}