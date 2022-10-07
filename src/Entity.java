import java.awt.*;
import java.awt.image.BufferedImage;

// Store variables that will be used in player, monster, and NPC classes
public class Entity {

    public int worldX, worldY;
    public int speed;

    // Describes an image with an accessible buffer of image data. (Use this to store out image files)
    public BufferedImage up1, up2, down1, down2, leftright1, leftright2, rightleft1, rightleft2;
    public String direction;

    // Make the spaceship like walk
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;

}