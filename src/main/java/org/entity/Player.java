package org.entity;

import org.game.GamePanel;
import org.game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "up";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/walking/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/walking/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/walking/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/walking/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/walking/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/walking/boy_left_1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/walking/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/walking/boy_right_1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed) {
            direction = "up";
            y -= speed;
        }
        else if (keyH.downPressed) {
            direction = "down";
            y += speed;
        }
        else if (keyH.leftPressed) {
            direction = "left";
            x -= speed;
        }
        else if (keyH.rightPressed) {
            direction = "right";
            x += speed;
        }
    }

    public void draw(Graphics2D g) {
        //g.setColor(Color.white);
        //g.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        switch (direction) {
            case "up": image = up1; break;
            case "down": image = down1; break;
            case "left": image = left1; break;
            case "right": image = right1; break;
        }

        g.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}

