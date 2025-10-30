import greenfoot.*;

public class MovingPlatform extends Platform {
    private int leftBound;
    private int rightBound;
    private int speed;
    private int direction = 1;

    public MovingPlatform(int left, int right, int y, int speed) {
        this.leftBound = left;
        this.rightBound = right;
        this.speed = speed;
        setImage(new GreenfootImage("moving_platform.png")); 
    }

    public void act() {
        setLocation(getX() + speed * direction, getY());
        if (getX() <= leftBound || getX() >= rightBound) {
            direction *= -1;
        }
    }
}
