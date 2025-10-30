import greenfoot.*;

public class Food extends Actor implements Collectible
{
    public void act()
    {
        if (isTouching(Player.class))
        {
            Player player = (Player)getOneIntersectingObject(Player.class);
            collect(player);
        }
    }

    @Override
    public void collect(Player player)
    {
        World world = getWorld();
        if (world instanceof GameWorld)
        {
            ((GameWorld)world).collectFood();
        }

        getWorld().removeObject(this);
    }
}