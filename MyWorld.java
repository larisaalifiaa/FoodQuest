import greenfoot.*;
import java.util.List;

public class MyWorld extends World
{
    private StartButton startButton;
    private CreditButton creditButton;
    private InfoButton infoButton;
    private ScoreBoard scoreBoard;
    private LifeDisplay lifeDisplay;
    private LevelInfoDisplay levelInfoDisplay; 
    
    private boolean gameStarted = false;
    private boolean showingCredits = false;
    private boolean showingInfo = false;
    private boolean inMenu = true;
    private boolean gameOverTriggered = false;
    private boolean gameWinTriggered = false;
    private int winTimer = 0;
    
    private int score = 0;
    private int lives = 3;
    private int currentLevel = 1;
    private int totalFood = 0;
    private int collectedFood = 0;
    private boolean objectiveComplete = false;
    private int gameOverTimer = 0;
    
    private GreenfootSound introMusic;
    private boolean musicEnabled = true;
    
    public MyWorld() {
        super(800, 600, 1, false);
        
        try {
            introMusic = new GreenfootSound("intro.mp3");
        } catch (Exception e) {
            musicEnabled = false;
        }
        
        showMenu(); 
    }

    public void act() {
        if (showingCredits && Greenfoot.isKeyDown("escape")) {
            hideCredits();
        }

        if (showingInfo && Greenfoot.isKeyDown("escape")) {
            hideInfo();
        }
        
        if (gameOverTriggered) {
            gameOverTimer++;
            if (gameOverTimer >= 300) {
                Greenfoot.stop();
            }
        }
        
        if (gameWinTriggered) {
            winTimer++;
            if (winTimer >= 240) {
                Greenfoot.stop();
            }
        }
    }

    private void clearAllActors() {
        removeObjects(getObjects(Actor.class));
    }

    private void showMenu() {
        gameStarted = false;
        clearAllActors();

        if (introMusic != null && musicEnabled) {
            try {
                introMusic.playLoop();
            } catch (Exception e) {
            }
        }

        GreenfootImage bg = null;
        try {
            bg = new GreenfootImage("BackGround.png");
        } catch (RuntimeException ex) {
            bg = new GreenfootImage(800, 600);
            bg.setColor(Color.BLACK);
            bg.fill();
        }
        setBackground(bg);

        Font smallFont = new Font("Arial", false, false, 16);
        bg.setFont(smallFont);
        bg.setColor(new Color(80, 80, 80));
        bg.drawString("v1.0", getWidth() - 50, getHeight() - 20);

        int panelY = 270;

        startButton = new StartButton();
        addObject(startButton, getWidth() / 2, panelY + 75);

        creditButton = new CreditButton();
        addObject(creditButton, getWidth() / 2, panelY + 160);

        infoButton = new InfoButton();
        addObject(infoButton, 80, 50);

        inMenu = true;
        showingCredits = false;
        showingInfo = false;

        setPaintOrder(
            InfoScreen.class, CreditScreen.class,
            StartButton.class, CreditButton.class, InfoButton.class,
            GameOverScreen.class, WinScreen.class,
            LevelInfoDisplay.class,
            LifeDisplay.class, ScoreBoard.class,
            Actor.class
        );
    }

    public void showCredits() {
        if (!showingCredits) {
            showingCredits = true;
            addObject(new CreditScreen(), 400, 300);
        }
    }

    public void hideCredits() {
        showingCredits = false;
        removeObjects(getObjects(CreditScreen.class));
    }

    public void showInfo() {
        if (!showingInfo) {
            showingInfo = true;
            addObject(new InfoScreen(), 400, 300);
        }
    }

    public void hideInfo() {
        showingInfo = false;
        removeObjects(getObjects(InfoScreen.class));
    }

    private void prepare() {
        if (!gameStarted) return; 

        scoreBoard = new ScoreBoard();
        addObject(scoreBoard, 70, 50);

        lifeDisplay = new LifeDisplay();
        addObject(lifeDisplay, 730, 50);

        scoreBoard.updateScore(score);
        lifeDisplay.updateLives(lives);
    }

    public void startGame() {
        clearAllActors();
        
        if (introMusic != null && introMusic.isPlaying()) {
            introMusic.stop();
        }
        
        GreenfootImage bg = new GreenfootImage(800, 600);
        bg.setColor(Color.BLACK);
        bg.fill();
        setBackground(bg);

        inMenu = false;
        gameStarted = true;

        currentLevel = 1;
        score = 0;
        lives = 3;
        totalFood = 0;
        collectedFood = 0;
        objectiveComplete = false;
        gameOverTriggered = false;
        gameWinTriggered = false;
        gameOverTimer = 0;
        winTimer = 0;

        prepare();
        loadLevel(currentLevel);
    }

    private void loadLevel(int level) {
        if (!gameStarted) return;

        collectedFood = 0;
        objectiveComplete = false;
        totalFood = 0;

        clearWorld();

        addObject(new Player(), 100, 500);

        if (level == 1)      loadLevel1_GreenGarden();
        else if (level == 2) loadLevel2_SkyTowers();
        else if (level == 3) loadLevel3_DangerZone();

        levelInfoDisplay = new LevelInfoDisplay(currentLevel, totalFood);
        addObject(levelInfoDisplay, getWidth() / 2, 70); 
    }

    private void clearWorld()
    {
        removeAllObjects(Platform.class);
        removeAllObjects(Food.class);
        removeAllObjects(Enemy.class);
        removeAllObjects(Player.class);
        removeAllObjects(GoalPortal.class);
        removeAllObjects(LevelInfoDisplay.class);
    }

    private void removeAllObjects(Class cls)
    {
        removeObjects(getObjects(cls));
    }

    private void loadLevel1_GreenGarden() {
        GreenfootImage bg = null;
        try {
            bg = new GreenfootImage("BGLV1.png");
        } catch (RuntimeException ex) {
            bg = new GreenfootImage(800, 600);
            bg.setColor(Color.BLACK);
            bg.fill();
        }
        setBackground(bg);

        for(int x = 0; x < 800; x += 18) { addObject(new GrassPlatform(), x + 9, 591); }

        addPlatformRow(108, 144, 519, "brick");
        addPlatformRow(270, 360, 447, "brick");
        addPlatformRow(468, 576, 375, "grass");
        addPlatformRow(648, 738, 303, "grass");

        addObject(new Tomato(), 126, 501);
        addObject(new Lettuce(), 315, 429);
        addObject(new Cheese(), 522, 357);
        addObject(new Tomato(), 693, 285);
        addObject(new Lettuce(), 400, 573);
        totalFood = 5;

        addObject(new EnemyKnife(), 250, 573);

        addObject(new GoalPortal(), 693, 265);
    } 

    private void loadLevel2_SkyTowers()
    {
        GreenfootImage bg = null;
        try { bg = new GreenfootImage("BGLV21.png"); } catch (RuntimeException ex) {
            bg = new GreenfootImage(800, 600); bg.setColor(Color.BLACK); bg.fill();
        }
        setBackground(bg);

        for(int x = 0; x < 800; x += 18) { addObject(new GrassPlatform(), x + 9, 591); }

        addPlatformRow(90, 162, 519, "brick");
        addPlatformRow(144, 216, 465, "brick");
        addPlatformRow(198, 270, 411, "wood");
        addPlatformRow(252, 324, 357, "wood");

        addPlatformRow(360, 450, 465, "wood");
        addPlatformRow(396, 486, 375, "brick");

        addPlatformRow(540, 648, 321, "brick");
        addPlatformRow(594, 702, 249, "wood");
        addPlatformRow(666, 756, 519, "grass");

        addObject(new Cheese(), 126, 501);
        addObject(new Lettuce(), 180, 447);
        addObject(new Tomato(), 288, 339);
        addObject(new Cheese(), 423, 447);
        addObject(new Lettuce(), 441, 357);
        addObject(new Tomato(), 594, 303);
        addObject(new Cheese(), 648, 231);
        totalFood = 7;

        addObject(new EnemyKnife(), 300, 573);
        addObject(new SpikyBall(), 500, 573);
        addObject(new EnemyKnife(), 423, 447);
        addObject(new FlyingPan(), 288, 400);

        addObject(new GoalPortal(), 648, 213);
    }

    private void loadLevel3_DangerZone()
    {
        GreenfootImage bg = null;
        try { bg = new GreenfootImage("BGLV3.png"); } catch (RuntimeException ex) {
            bg = new GreenfootImage(800, 600); bg.setColor(Color.BLACK); bg.fill();
        }
        setBackground(bg);

        for(int x = 0; x < 180; x += 18) { addObject(new GrassPlatform(), x + 9, 591); }
        for(int x = 360; x < 540; x += 18) { addObject(new GrassPlatform(), x + 9, 591); }
        for(int x = 620; x < 800; x += 18) { addObject(new GrassPlatform(), x + 9, 591); }

        addPlatformRow(180, 252, 519, "brick");
        addPlatformRow(108, 180, 465, "wood");
        addPlatformRow(180, 252, 411, "brick");
        addPlatformRow(108, 180, 357, "wood");
        addPlatformRow(180, 252, 303, "brick");

        addPlatformRow(324, 396, 465, "wood");
        addPlatformRow(432, 504, 411, "wood");
        addPlatformRow(360, 432, 339, "brick");

        addPlatformRow(576, 684, 483, "brick");
        addPlatformRow(612, 720, 393, "wood");
        addPlatformRow(648, 756, 303, "brick");
        addPlatformRow(684, 756, 231, "wood");

        addObject(new Cheese(), 90, 573);
        addObject(new Lettuce(), 216, 501);
        addObject(new Tomato(), 144, 447);
        addObject(new Cheese(), 216, 393);
        addObject(new Lettuce(), 144, 339);
        addObject(new Tomato(), 360, 447);
        addObject(new Cheese(), 468, 393);
        addObject(new Lettuce(), 396, 321);
        addObject(new Tomato(), 630, 465);
        addObject(new Cheese(), 720, 213);
        totalFood = 9; 

        addObject(new EnemyKnife(), 450, 573);
        addObject(new SpikyBall(), 700, 573);
        addObject(new EnemyKnife(), 216, 501);
        addObject(new FlyingPan(), 360, 380);
        addObject(new FlyingPan(), 630, 340);
        addObject(new SpikyBall(), 144, 339);

        addObject(new GoalPortal(), 720, 195);
    }

    private void addPlatformRow(int startX, int endX, int y, String type)
    {
        for(int x = startX; x <= endX; x += 18)
        {
            Platform platform = null;

            if(type.equals("grass")) { platform = new GrassPlatform(); }
            else if(type.equals("brick")) { platform = new BrickPlatform(); }
            else if(type.equals("wood")) { platform = new WoodPlatform(); }

            if(platform != null) { addObject(platform, x, y); }
        }
    }

    public void addScore(int points)
    {
        score += points;
        scoreBoard.updateScore(score);
    }

    public void foodCollected()
    {
        collectedFood++;
        if (levelInfoDisplay != null) {
            levelInfoDisplay.updateCollected(collectedFood);
        }

        if(collectedFood >= totalFood && !objectiveComplete)
        {
            objectiveComplete = true;
            activatePortal();
        }
    }

    private void activatePortal()
    {
        List<GoalPortal> portals = getObjects(GoalPortal.class);

        if(portals.size() > 0)
        {
            GoalPortal portal = portals.get(0);
            portal.activate();
        }
    }

    public void nextLevel() {
        currentLevel++;
        if (currentLevel > 3) {
            gameWin();
        } else {
            loadLevel(currentLevel);
        }
    }

    public boolean isObjectiveComplete()
    {
        return objectiveComplete;
    }

    public void loseLife()
    {
        lives--;
        lifeDisplay.updateLives(lives);

        if(lives <= 0)
        {
            gameOver();
        }
    }

    private void gameOver()
    {
        if (!gameOverTriggered) {
            if (introMusic != null && introMusic.isPlaying()) {
                introMusic.stop();
            }
            
            GameOverScreen gameOver = new GameOverScreen(score);
            addObject(gameOver, 400, 300);
            gameOverTriggered = true;
            gameOverTimer = 0;
        }
    }

    private void gameWin()
    {
        if (!gameWinTriggered) {
            if (introMusic != null && introMusic.isPlaying()) {
                introMusic.stop();
            }
            
            try {
                GreenfootSound winSound = new GreenfootSound("win.wav");
                winSound.setVolume(80);
                winSound.play();
            } catch (Exception e) {

            }
            
            WinScreen winScreen = new WinScreen();
            addObject(winScreen, 400, 300);
            gameWinTriggered = true;
            winTimer = 0;
        }
    }

    public int getScore() { return score; }

    public int getCurrentLevel() { return currentLevel; }
}