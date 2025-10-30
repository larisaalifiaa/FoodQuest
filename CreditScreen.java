import greenfoot.*;

public class CreditScreen extends Actor {
    private GreenfootImage overlay;
    
    public CreditScreen() {
        overlay = new GreenfootImage(1, 1);
        setImage(overlay);
    }
    
    @Override
    protected void addedToWorld(World world) {
        int width = world.getWidth();
        int height = world.getHeight();
        overlay = new GreenfootImage(width, height);
        overlay.setColor(new Color(0, 0, 0, 180));
        overlay.fillRect(0, 0, width, height);
        
        GreenfootImage petunjuk = new GreenfootImage("CreditOverlay.png");
        
        int x = (width - petunjuk.getWidth()) / 2;
        int y = (height - petunjuk.getHeight()) / 2;
        overlay.drawImage(petunjuk, x, y);
        
        String message = "Tekan ESC untuk keluar";
        overlay.setColor(Color.WHITE);
        overlay.setFont(new Font("Arial", 16));
        int textX = x + 50;
        int textY = y + petunjuk.getHeight() - 100; 
        overlay.drawString(message, textX, textY);
        
        setImage(overlay);
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("escape")) {
            getWorld().removeObject(this);
        }
    }
}