package java_games.mushroomMaze;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Slug extends MazeGameObject{

    private MazeObjectHandler handler;
    Random r = new Random();
    int choose = 0;
    int hp = 50;

    private BufferedImage slug_image;

    public Slug(int x, int y, ID id, MazeObjectHandler handler, SpriteSheet sheet) {
        super(x, y, id, sheet);
        this.handler = handler;

        slug_image = sheet.getSpriteImage(6, 1, 32, 32);
    }

    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;

        //randomized movement
        choose = r.nextInt(10);

        for (int i = 0; i < handler.object.size(); i++) {
            MazeGameObject tempObject = handler.object.get(i);

            //wall collision with auto correct
            if (tempObject.getId() == ID.Wall) {

                if (getBoundsBubble().intersects(tempObject.getBounds())) {
                    //change velocity
                    x += (velocityX * 2) * -1;
                    y += (velocityY * 2) * -1;
                    velocityX *= -1;
                    velocityY *= -1;

                } else if (choose == 0) {
                    velocityX = (r.nextInt(4 - -4) + -4);
                    velocityY = (r.nextInt(4 - -4) + -4);
                }
            }
            if (tempObject.getId() == ID.Projectile) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    hp -= 5;
                    handler.removeObject(tempObject);
                }
            }
        }
        //when hp reach 0, turn slug into food
        if (hp <= 0) {
            handler.removeObject(this);
            handler.addObject(new Food(this.getX(), this.getY(), ID.Food, sheet));
        }
    }

    @Override
    public void render(Graphics g) {
//        g.setColor(Color.yellow);
//        g.fillRect(x, y, 32, 32);
        g.drawImage(slug_image, x, y, null);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    //create wider bubble around enemy object
    public Rectangle getBoundsBubble() {
        return new Rectangle(x - 15, y - 10, 64, 64);
    }
}
