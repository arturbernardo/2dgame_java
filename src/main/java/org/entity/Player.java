package org.entity;

import org.game.GamePanel;
import org.game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        // camera pos
        int tileOffset = gp.tileSize / 2;
        this.screenX = (gp.screenWidth / 2) - tileOffset;
        this.screenY = (gp.screenHeight / 2) - tileOffset;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        // player position
        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 25;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/walking/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/walking/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/walking/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/walking/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/walking/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/walking/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/walking/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/walking/boy_right_2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {

        // only move and update sprite when key is pressed
        // probably will need to change it in the future
        // animation should change without the need for keys pressed
        if (keyH.rightPressed
        || keyH.downPressed
        || keyH.leftPressed
        || keyH.upPressed) {

            if (keyH.upPressed) {
                direction = "up";
                worldY -= speed;
            } else if (keyH.downPressed) {
                direction = "down";
                worldY += speed;
            } else if (keyH.leftPressed) {
                direction = "left";
                worldX -= speed;
            } else if (keyH.rightPressed) {
                direction = "right";
                worldX += speed;
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }

                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g) {
        //g.setColor(Color.white);
        //g.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) image = up1;
                if (spriteNum == 2) image = up2;
                break;
            case "down":
                if (spriteNum == 1) image = down1;
                if (spriteNum == 2) image = down2;
                break;
            case "left":
                if (spriteNum == 1) image = left1;
                if (spriteNum == 2) image = left2;
                break;
            case "right":
                if (spriteNum == 1) image = right1;
                if (spriteNum == 2) image = right2;
                break;
        }

        g.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}

