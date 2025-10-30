import greenfoot.*;

public class GameWorld extends World
{
    private int foodCollected = 0;

    public GameWorld()
    {
        super(800, 600, 1);
    }

    public void collectFood()
    {
        foodCollected++;
        System.out.println("Food collected: " + foodCollected);
    }
}