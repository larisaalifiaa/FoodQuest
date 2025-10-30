import greenfoot.*;

public class GoalPortal extends Actor implements Teleportable
{
    private boolean isActive = false;
    private int animationCounter = 0;
    private boolean transitionStarted = false;

    public GoalPortal()
    {
        updateImage();
    }

    public void act()
    {
        animationCounter++;
        if (animationCounter % 15 == 0)
        {
            updateImage();
        }

        if (isActive && !transitionStarted)
        {
            checkPlayerCollision();
        }
    }

    private void checkPlayerCollision()
    {
        Actor player = getOneIntersectingObject(Player.class);

        if (player != null)
        {
            transitionStarted = true;
            teleport(player);
        }
    }

    @Override
    public void teleport(Actor actor)
    {
        if (getWorld() == null || actor == null) return;

        MyWorld world = (MyWorld) getWorld();

        if (actor.getWorld() != null)
        {
            world.removeObject(actor);
        }

        Greenfoot.delay(10);

        world.nextLevel();
    }

    public void activate()
    {
        if (!isActive)
        {
            isActive = true;
            updateImage();
        }
    }

    public boolean isActive()
    {
        return isActive;
    }

    private void updateImage()
    {
        GreenfootImage img = new GreenfootImage(70, 90);

        if (isActive)
        {
            int pulse = (animationCounter % 30 < 15) ? 0 : 20;

            img.setColor(new Color(0, 150, 255, 100 + pulse));
            img.fillOval(5, 5, 60, 80);

            img.setColor(new Color(50, 200, 255, 150 + pulse));
            img.fillOval(12, 12, 46, 66);

            img.setColor(new Color(150, 230, 255, 200 + pulse));
            img.fillOval(20, 20, 30, 50);

            img.setColor(Color.WHITE);
            img.fillOval(27, 30, 16, 30);
        }
        else
        {
            img.setColor(new Color(60, 60, 60, 180));
            img.fillOval(5, 5, 60, 80);

            img.setColor(new Color(80, 80, 80, 120));
            img.fillOval(12, 12, 46, 66);

            img.setColor(new Color(100, 100, 100, 80));
            img.fillOval(20, 20, 30, 50);

            img.setColor(new Color(150, 50, 50));
            img.drawLine(25, 30, 45, 60);
            img.drawLine(45, 30, 25, 60);
        }

        setImage(img);
    }
}