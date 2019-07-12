package java_games.mushroomMaze;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Wall extends MazeGameObject {

    private BufferedImage wall_image;

    public Wall(int x, int y, ID id, SpriteSheet sheet) {
        super(x, y, id, sheet);

        wall_image = sheet.getSpriteImage(6, 2, 32, 32);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
//        g.setColor(Color.darkGray);
//        g.fillRect(x, y, 32, 32);
        g.drawImage(wall_image, x, y, null);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
