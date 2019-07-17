package java_games.mushroomMaze;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FoodSpores extends MazeGameObject{

    private BufferedImage foodSpore_image;

    public FoodSpores(int x, int y, ID id, SpriteSheet sheet) {
        super(x, y, id, sheet);

        foodSpore_image = sheet.getSpriteImage(7, 2, 32, 32);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(foodSpore_image, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
