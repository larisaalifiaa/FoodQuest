import greenfoot.*;

public class MessageDisplay extends Actor
{
    private int displayTime = 0;
    private int maxDisplayTime = 60;
    
    public MessageDisplay(String message)
    {
        createImage(message);
    }
    
    public MessageDisplay(String message, int duration)
    {
        maxDisplayTime = duration;
        createImage(message);
    }
    
    private void createImage(String message)
    {
        int width = Math.max(400, message.length() * 15);
        int height = 100;
        
        GreenfootImage img = new GreenfootImage(width, height);
        
        img.setColor(new Color(0, 0, 0, 220));
        img.setColor(new Color(255, 215, 0));
        img.setColor(new Color(255, 255, 100));
        
        img.setColor(new Color(0, 0, 0, 150));
        img.setFont(new Font("Arial", true, false, 36));
        img.drawString(message, (width - message.length() * 17) / 2 + 3, 63);
        
        img.setColor(Color.WHITE);
        img.setFont(new Font("Arial", true, false, 36));
        img.drawString(message, (width - message.length() * 17) / 2, 60);
        
        setImage(img);
    }
    
    public void act()
    {
        displayTime++;
        
        if(displayTime < 10)
        {
            int transparency = displayTime * 25;
            getImage().setTransparency(transparency);
        }
        else if(displayTime > maxDisplayTime - 10)
        {
            int transparency = (maxDisplayTime - displayTime) * 25;
            getImage().setTransparency(transparency);
        }
        else
        {
            getImage().setTransparency(255);
        }
        
        if(displayTime >= maxDisplayTime)
        {
            getWorld().removeObject(this);
        }
    }
}