package org.tile;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean collision = false;

    public Tile() {
    }

    public Tile(BufferedImage image) {
        this.image = image;
    }

    public Tile(boolean collision, BufferedImage image) {
        this.image = image;
        this.collision = collision;
    }

}
