package java_games.jaesMaze;

import java.awt.*;

public class Mushroom extends MazeGameObject{

    MazeObjectHandler handler;

    public Mushroom(int x, int y, ID id, MazeObjectHandler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;

        //set movement handlers
        //else if to prevent lag
        if (handler.isUp()) velocityY = -2;
        else if (!handler.isDown()) velocityY = 0;

        if (handler.isDown()) velocityY = 2;
        else if (!handler.isUp()) velocityY = 0;

        if (handler.isLeft()) velocityX = -2;
        else if (!handler.isRight()) velocityX = 0;

        if (handler.isRight()) velocityX = 2;
        else if (!handler.isLeft()) velocityX = 0;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(x, y, 20, 30);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 20, 30);
    }
}
