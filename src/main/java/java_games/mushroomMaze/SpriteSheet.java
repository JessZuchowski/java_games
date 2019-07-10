package java_games.mushroomMaze;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage image;

    public SpriteSheet (BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getSpriteImage(int column, int row, int width, int height) {
        return image.getSubimage((column * 32) - 32, (row * 32) - 32, width, height);
    }
}
