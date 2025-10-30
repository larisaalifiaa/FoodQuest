import greenfoot.*;

public class InfoScreen extends Actor {
    private GreenfootImage overlay;

    public InfoScreen() {
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

        GreenfootImage petunjuk = new GreenfootImage("Screen.png");
        int scaledWidth = (int)(petunjuk.getWidth() * 0.45);
        int scaledHeight = (int)(petunjuk.getHeight() * 0.45);
        petunjuk.scale(scaledWidth, scaledHeight);

        int x = (width - scaledWidth) / 2;
        int y = (height - scaledHeight) / 2 - 20;
        overlay.drawImage(petunjuk, x, y);

        String message = "Tekan ESC untuk keluar";
        overlay.setColor(Color.WHITE);
        overlay.setFont(new Font("Arial", 16));

        int textX = 80;
        int textY = y + scaledHeight + 30;
        overlay.drawString(message, textX, textY);

        setImage(overlay);
    }

    public void act() {
        if (Greenfoot.isKeyDown("escape")) {
            getWorld().removeObject(this);
        }
    }
}