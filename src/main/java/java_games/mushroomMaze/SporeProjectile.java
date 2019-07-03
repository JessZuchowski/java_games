package java_games.mushroomMaze;

import java.awt.*;

public class SporeProjectile extends MazeGameObject {

    private MazeObjectHandler handler;

    public SporeProjectile(int x, int y, ID id, MazeObjectHandler handler, int mx, int my) {
        super(x, y, id);
        this.handler = handler;

        velocityX = (mx - x) / 40;
        velocityY = (my - y) / 40;
    }

    @Override
    public void tick() {

        x += velocityX;
        y += velocityY;

        //wall collision removes projectile object
        for (int i = 0; i < handler.object.size(); i++) {
            MazeGameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Wall) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(this);
                }
            }
        }

    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.cyan);
        g.fillOval(x, y, 3, 3);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 3, 3);
    }
}
