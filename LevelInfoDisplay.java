import greenfoot.*;

public class LevelInfoDisplay extends Actor
{
    private int level;
    private int collected;
    private int totalFood;

    public LevelInfoDisplay(int level, int totalFood)
    {
        this.level = level;
        this.collected = 0;
        this.totalFood = totalFood;
        updateImage();
    }

    public void updateCollected(int newCollected)
    {
        this.collected = newCollected;
        updateImage();
    }

    private void updateImage()
    {

        int index = level * 10 + collected;
        String filename = "lv" + index + ".png";

        try {
            GreenfootImage img = new GreenfootImage(filename);
            img.scale(300, 80); 
            setImage(img);
        } catch (Exception e) {
            System.out.println("⚠ Gagal load gambar: " + filename);
            GreenfootImage fallback = new GreenfootImage(300, 80);
            fallback.setColor(Color.RED);
            fallback.fillRect(0, 0, 300, 80);
            fallback.setColor(Color.WHITE);
            fallback.drawString("Missing: " + filename, 10, 40);
            setImage(fallback);
        }
    }
}
