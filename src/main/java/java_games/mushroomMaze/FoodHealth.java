package java_games.mushroomMaze;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FoodHealth extends MazeGameObject{

    private BufferedImage foodHealth_image;

    public FoodHealth(int x, int y, ID id, SpriteSheet sheet) {
        super(x, y, id, sheet);

        foodHealth_image = sheet.getSpriteImage(11, 2, 32, 32);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(foodHealth_image, x, y, null);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
