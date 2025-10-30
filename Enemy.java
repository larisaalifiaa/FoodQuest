import greenfoot.*;

public class Enemy extends Actor implements Movable
{
    protected int speed = 2;
    protected int direction = 1;
    protected int startX;
    protected int moveRange = 100;
    protected boolean facingRight = true;

    public Enemy()
    {
    }

    @Override
    protected void addedToWorld(World world)
    {
        startX = getX();
    }

    @Override
    public void act()
    {
        move();
    }

    @Override
    public void move()
    {
        setLocation(getX() + (speed * direction), getY());

        if (Math.abs(getX() - startX) >= moveRange)
        {
            direction *= -1;
            facingRight = !facingRight;
        }

        if (getX() <= 10 || getX() >= getWorld().getWidth() - 10)
        {
            direction *= -1;
            facingRight = !facingRight;
        }

        if (!facingRight)
        {
            getImage().mirrorHorizontally();
        }
    }

    public void setMoveRange(int range)
    {
        this.moveRange = range;
    }
}