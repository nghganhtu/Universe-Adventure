import javax.swing.JPanel;
import java.awt.*;

// This class inherited JPanel class
public class GamePanels  extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 Tile, 16 pixel
    final int scale = 3; // We create 16 pixel, but it will scale it to 48 pixel on the screen
    public final int tileSize = originalTileSize * scale; // The size on the screen currently is 48 pixels
    public final int maxScreenCol = 16; // 16 tiles horizontally
    public final int maxScreenRow = 12; // 12 tile vertically, 3/4
    public final int screenWidth = tileSize * maxScreenCol; // 1440 pixels width
    public final int screenHeight = tileSize * maxScreenRow; // 720 pixels height

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // to keep The game running until we stop it
    CollisionChecker cChecker = new CollisionChecker(this);

    Player player = new Player(this, keyH);

    // Set player's default position
    // int playerX = 100; // X increase from left to right
    // int playerY = 100; // Y increase from up to down
    // int playerSpeed = 4; // the distance after moving is 4 pixels
    public GamePanels() {

        // Set the size of this class (JPanel)
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK); // Set the background of the buttons
        this.setDoubleBuffered(true); // Set to true to all drawing from this component will be done in an offscreen painting buffer
        this.addKeyListener(keyH); // To this game panel recognize the key input
        this.setFocusable(true); // This game panel can be "focused" to received key input
    }
    // instantiate this game thread
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

//    @Override   // SLEEP METHOD
//    public void run() {
//
//      double drawInterval = 1000000000/FPS; // 1 billion = 1 nanosecond.
        // Draw interval mean the screen is drawn every 0.016s = draw screen 60 times per second.
//        double nextDrawTime = System.nanoTime() + drawInterval;

//        while(gameThread != null){
            // System.out.println("The game loop is running");

            // 1. UPDATE: update information such as character positions
//            update();
            // 2 DRAW: draw the screen with the updated information
    // repaint(); // Even during a short key touch, the update() get's called so many times and add 4 (speed) to playerY each time

//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000;
//                if(remainingTime < 0){
//                    remainingTime = 0;
//                }
//                Thread.sleep((long) remainingTime); // sleep method accepts the number as millisecond, hence, convert the remaining time from nano to milli
//                nextDrawTime += drawInterval;
//            } catch (InterruptedException e) {
                // Auto - generated catch block
//                e.printStackTrace();
//            }
//
//        }
//    }

    public void run(){ // Delta method

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0; // To check FPS
        int drawCount = 0; // To check FPS

        while(gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta --;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    // Player hit the key input, the update method update player's coordinate, after update, the paint component draw a rectangle with updated position
    public void update(){

        player.update();
    }
    // A class "Graphics" that has many functions to draw objects on the screen.
    public void paintComponent(Graphics g) { // call this Class as g like call numpy as np.

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; // to use the extended class

        tileM.draw(g2);

        player.draw(g2);

        g2.dispose(); // dispose of this graphics context and release any system resources that it is using

    }
}

