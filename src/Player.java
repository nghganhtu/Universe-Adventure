import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends  Entity{

    GamePanels gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;


    public Player(GamePanels gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize/2);
        screenY = gp.screenHeight / 2 - (gp.tileSize/2);
        solidArea = new Rectangle(8, 16, 32, 32);

        setDefaultValues();
        getPlayerImage();
    }
     // Set player's default values
    public void setDefaultValues() {

        worldX = gp.tileSize * 10;
        worldY = gp.tileSize * 10;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/PlayerMove/down to up. 1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/PlayerMove/down to up. 2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/PlayerMove/up to down. 1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/PlayerMove/up to down. 2.png"));
            leftright1 = ImageIO.read(getClass().getResourceAsStream("/PlayerMove/left to right. 1.png"));
            leftright2 = ImageIO.read(getClass().getResourceAsStream("/PlayerMove/left to right. 2.png"));
            rightleft1 = ImageIO.read(getClass().getResourceAsStream("/PlayerMove/right to left. 1.png"));
            rightleft2 = ImageIO.read(getClass().getResourceAsStream("/PlayerMove/right to left. 2.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {
            // This sprite counter increases only when pressing these keys one of these keys
            // update the position of the players
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "right to left";
            } else if (keyH.rightPressed == true) {
                direction = "left to right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "right to left":
                        worldX -= speed;
                        break;
                    case "left to right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {

        // g2.setColor(Color.WHITE);

        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "right to left":
                if(spriteNum == 1){
                    image = rightleft1;
                }
                if(spriteNum == 2){
                    image = rightleft2;
                }
                break;
            case "left to right":
                if(spriteNum == 1){
                    image = leftright1;
                }
                if(spriteNum == 2){
                    image = leftright2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); // Draw an image on the screen
    }


}