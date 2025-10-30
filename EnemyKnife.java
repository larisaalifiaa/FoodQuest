import greenfoot.*;

public class EnemyKnife extends Enemy
{
    private GreenfootImage[] walkFrames;
    private int animationCounter = 0;
    private int currentFrame = 0;

    public EnemyKnife()
    {
        loadImages();
        speed = 2;
        setMoveRange(150); 
    }

    private void loadImages()
    {
        walkFrames = new GreenfootImage[3];
        walkFrames[0] = new GreenfootImage("enemyknife.png");
        walkFrames[1] = new GreenfootImage("enemyknife1.png");
        walkFrames[2] = new GreenfootImage("enemyknife2.png");
        setImage(walkFrames[0]);
    }

    @Override
    public void act()
    {
        super.act();
        updateAnimation();
    }

    private void updateAnimation()
    {
        animationCounter++;

        if (animationCounter % 5 == 0)
        {
            currentFrame = (currentFrame + 1) % 3;
            setImage(new GreenfootImage(walkFrames[currentFrame]));

            if (!facingRight)
            {
                getImage().mirrorHorizontally();
            }
        }
    }
}