package java_games.mushroomMaze;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mushroom extends MazeGameObject{

    private MazeObjectHandler handler;
    public  MazeGame game;

    private BufferedImage mushroom_image;

    public Mushroom(int x, int y, ID id, MazeObjectHandler handler, MazeGame game, SpriteSheet sheet) {
        super(x, y, id, sheet);
        this.handler = handler;
        this.game = game;

        mushroom_image = sheet.getSpriteImage(1, 1, 32, 48);
    }

    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;
        
        wallCollision();

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

    private void wallCollision() {

        for (int i = 0; i < handler.object.size(); i ++) {
            MazeGameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Wall) {

                if (getBounds().intersects(tempObject.getBounds())) {

                    x += velocityX * -1;
                    y += velocityY * -1;
                }
            }
            //remove food object and add nutrients
            if (tempObject.getId() == ID.Food) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    game.nutrients += 5;
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
//        g.setColor(Color.orange);
//        g.fillRect(x, y, 32, 48);
        g.drawImage(mushroom_image, x, y, null);

    }

    @Override
    public Rectangle getBounds() {

        return new Rectangle(x, y, 32, 48);
    }
}
