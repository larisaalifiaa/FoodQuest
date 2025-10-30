import greenfoot.*;

public class WinScreen extends Actor
{
    public WinScreen()
    {
        int width = 800;
        int height = 600;

        GreenfootImage baseImage = new GreenfootImage(width, height);
        baseImage.setColor(new Color(0, 0, 0, 180));
        baseImage.fillRect(0, 0, width, height);

        GreenfootImage winImage = new GreenfootImage("win.png");
        int x = (width - winImage.getWidth()) / 2;
        int y = (height - winImage.getHeight()) / 2;
        baseImage.drawImage(winImage, x, y);

        setImage(baseImage);
    }
}