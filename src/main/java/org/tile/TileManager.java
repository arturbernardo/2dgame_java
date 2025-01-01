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
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        loadMap();
        getTileImage();
    }

    public void loadMap() {
        try {
            InputStream stream = getClass().getResourceAsStream("/maps/allGrass.txt");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < gp.maxScreenCol; i++) {
            for (int j = 0; j < gp.maxScreenRow; j++) {
                g.drawImage(tile[mapTileNum[i][j]].image, gp.tileSize*i, gp.tileSize*j, gp.tileSize, gp.tileSize, null);
            }
        }
    }
}
