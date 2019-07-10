package java_games.mushroomMaze;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Food extends MazeGameObject{

    private BufferedImage food_image;

    public Food(int x, int y, ID id, SpriteSheet sheet) {
        super(x, y, id, sheet);

        food_image = sheet.getSpriteImage(8, 1, 32, 32);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
//        g.setColor(Color.pink);
//        g.fillRect(x, y, 32,32);
        g.drawImage(food_image, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
