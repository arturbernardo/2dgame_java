package org.game;

import org.entity.Entity;

public class CollisionHandler {

    GamePanel gp;
    public CollisionHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int xLeft = entity.worldX + entity.solidArea.x;
        int xRight = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int yTop = entity.worldY + entity.solidArea.y;
        int yBottom = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int leftCol = xLeft/gp.tileSize;
        int rightCol = xRight/gp.tileSize;
        int topRow = yTop/gp.tileSize;
        int bottomRow = yBottom/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction){
            case "up":
                topRow = (yTop - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[leftCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][topRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                    entity.collisionOn = true;
                break;
            case "down":
                bottomRow = (yBottom + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[leftCol][bottomRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][bottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                    entity.collisionOn = true;
                break;
            case "left":
                leftCol = (xLeft - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[leftCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[leftCol][bottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                    entity.collisionOn = true;
                break;
            case "right":
                rightCol = (xRight + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[rightCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][bottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                    entity.collisionOn = true;
                break;
        }


    }
}
