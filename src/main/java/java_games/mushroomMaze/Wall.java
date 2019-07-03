package java_games.mushroomMaze;

import java.awt.*;

public class Wall extends MazeGameObject {
    public Wall(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(x, y, 20, 20);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 20, 20);
    }
}
