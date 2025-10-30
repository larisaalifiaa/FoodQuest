import greenfoot.*;

public class InfoButton extends Actor implements Clickable {
    public InfoButton() {
        GreenfootImage img = new GreenfootImage("petunjuk.png");
        img.scale(60, 60);
        setImage(img);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            onClick();
        }
    }

    @Override
    public void onClick() {
        Greenfoot.playSound("start.mp3");
        if (getWorld() instanceof MyWorld) {
            ((MyWorld)getWorld()).showInfo();
        }
    }
}