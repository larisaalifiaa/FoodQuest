import greenfoot.*;

public class Player extends Actor implements Jumpable
{
    private int velocityY = 0;
    private int velocityX = 0;
    private boolean isOnGround = false;
    private final int GRAVITY = 1;
    private final int JUMP_STRENGTH = -17;
    private final int MOVE_SPEED = 5;

    private GreenfootImage playerIdle;
    private GreenfootImage playerRun;
    private int animationCounter = 0;
    private boolean facingRight = true;

    private GreenfootSound jumpSound = new GreenfootSound("jump.wav");
    private GreenfootSound coinSound = new GreenfootSound("coin.wav");
    private GreenfootSound hitSound = new GreenfootSound("hit.wav"); 

    public Player()
    {
        loadImages();
    }

    private void loadImages()
    {
        playerIdle = new GreenfootImage("player.png");
        playerRun = new GreenfootImage("player_run.png");
        setImage(playerIdle);
    }

    public void act()
    {
        if(getWorld() == null) return;

        handleInput();
        applyGravity();
        checkCollisions();
        collectItems();
        checkEnemyCollision();
        updateAnimation();
    }

    private void handleInput()
    {
        if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a"))
        {
            velocityX = -MOVE_SPEED;
            facingRight = false;
        }
        else if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d"))
        {
            velocityX = MOVE_SPEED;
            facingRight = true;
        }
        else
        {
            velocityX = 0;
        }

        setLocation(getX() + velocityX, getY());

        if((Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) && isOnGround)
        {
            velocityY = JUMP_STRENGTH;
            isOnGround = false;
            jumpSound.play();
        }
    }

    private void updateAnimation()
    {
        animationCounter++;

        if(velocityX != 0)
        {
            if(animationCounter % 10 < 5)
            {
                setImage(new GreenfootImage(playerRun));
            }
            else
            {
                setImage(new GreenfootImage(playerIdle));
            }
        }
        else
        {
            setImage(new GreenfootImage(playerIdle));
        }

        if(!facingRight)
        {
            getImage().mirrorHorizontally();
        }
    }

    private void applyGravity()
    {
        velocityY += GRAVITY;

        if(velocityY > 20)
        {
            velocityY = 20;
        }

        setLocation(getX(), getY() + velocityY);
    }

    private void checkCollisions()
    {
        Actor platform = getOneObjectAtOffset(0, getImage().getHeight()/2, Platform.class);

        if(platform != null && velocityY >= 0)
        {
            int platformTop = platform.getY() - platform.getImage().getHeight()/2;
            int playerBottom = getImage().getHeight()/2;
            setLocation(getX(), platformTop - playerBottom);

            velocityY = 0;
            isOnGround = true;
        }
        else if(platform == null && velocityY >= 0)
        {
            isOnGround = false;
        }

        if(getY() > getWorld().getHeight() + 50)
        {
            ((MyWorld)getWorld()).loseLife();
            resetPosition();
        }

        if(getX() < 15)
        {
            setLocation(15, getY());
        }
        else if(getX() > getWorld().getWidth() - 15)
        {
            setLocation(getWorld().getWidth() - 15, getY());
        }
    }

    private void collectItems()
    {
        if(getWorld() == null) return;

        Actor food = getOneIntersectingObject(Food.class);
        if(food != null)
        {
            getWorld().removeObject(food);
            coinSound.play();
            ((MyWorld)getWorld()).addScore(10);
            ((MyWorld)getWorld()).foodCollected();
        }
    }

    private void checkEnemyCollision()
    {
        if(getWorld() == null) return;

        Actor enemy = getOneIntersectingObject(Enemy.class);
        if(enemy != null)
        {
            hitSound.play();
            ((MyWorld)getWorld()).loseLife();
            resetPosition();
        }
    }

    private void resetPosition()
    {
        setLocation(100, 500);
        velocityY = 0;
        velocityX = 0;
        isOnGround = false;
        facingRight = true;
    }
    @Override
public void jump()
{
    if (isOnGround)
    {
        velocityY = JUMP_STRENGTH;
        isOnGround = false;
        jumpSound.play();
    }
}

@Override
public boolean isOnGround()
{
    return isOnGround;
}
}