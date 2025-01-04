package org.tile;

import org.game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        loadMap("/maps/01.txt");
        getTileImage();
    }

    public void loadMap(String map) {
        try {
            InputStream stream = getClass().getResourceAsStream(map);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));

            int col = 0;
            int row = 0;

            String line;
            while ((line = br.readLine()) != null) {
                String[] lineArray = line.split(" ");
                for (String tileNum : lineArray) {
                    // improve this parse
                    mapTileNum[col][row] = Integer.parseInt(tileNum);
                    col++;
                }
                col= 0;
                row++;
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/grass00.png")));
            tile[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/wall.png")));
            tile[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/water00.png")));
            tile[3] = new Tile(ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/earth.png")));
            tile[4] = new Tile(ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/tree.png")));
            tile[5] = new Tile(ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/road00.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < gp.maxWorldCol; i++) {
            for (int j = 0; j < gp.maxWorldRow; j++) {

                // (i * gp.tileSize) is to position on the map
                // (- gp.player.worldX + gp.player.screenX) will subtract the excess and add it to the screen
                // gp.player.screenX offset the player to the center

                int worldX = i * gp.tileSize;
                int worldY = j * gp.tileSize;
                int x = worldX - gp.player.worldX + gp.player.screenX;
                int y = worldY - gp.player.worldY + gp.player.screenY;

                // only draws whats inside the screen

                int borderExtraTile = 1 * gp.tileSize;

                if (worldX + borderExtraTile > gp.player.worldX - gp.player.screenX &&
                    worldX - borderExtraTile < gp.player.worldX + gp.player.screenX &&
                    worldY + borderExtraTile > gp.player.worldY - gp.player.screenY &&
                    worldY - borderExtraTile < gp.player.worldY + gp.player.screenY) {

                    g.drawImage(tile[mapTileNum[i][j]].image, x, y, gp.tileSize, gp.tileSize, null);
                }

            }
        }
    }
}
