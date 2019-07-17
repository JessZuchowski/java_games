package java_games.mushroomMaze;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Slug extends MazeGameObject{

    private MazeObjectHandler handler;
    Random r = new Random();
    int choose = 0;
    int hp = 50;

    private BufferedImage[] slug_image = new BufferedImage[5];
    Animation animation;

    public Slug(int x, int y, ID id, MazeObjectHandler handler, SpriteSheet sheet) {
        super(x, y, id, sheet);
        this.handler = handler;

        slug_image[0] = sheet.getSpriteImage(5, 1, 32, 32);
        slug_image[1] = sheet.getSpriteImage(12, 1, 32, 32);
        slug_image[2] = sheet.getSpriteImage(6, 1, 32, 32);
        slug_image[3] = sheet.getSpriteImage(11, 1, 32, 32);
        slug_image[4] = sheet.getSpriteImage(7, 1, 32, 32);

        animation = new Animation(9, slug_image[2], slug_image[3], slug_image[4], slug_image[3], slug_image[2], slug_image[1], slug_image[0], slug_image[1], slug_image[2]);
    }

    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;

        //randomized movement
        choose = r.nextInt(100);

        for (int i = 0; i < handler.object.size(); i++) {
            MazeGameObject tempObject = handler.object.get(i);

            //wall collision with auto correct
            if (tempObject.getId() == ID.Wall) {

                if (getBoundsBubble().intersects(tempObject.getBounds())) {
                    //change velocity
                    x += (velocityX * 2) * -0.5;
                    y += (velocityY * 2) * -0.5;
                    velocityX *= -1;
                    velocityY *= -1;

                } else if (choose == 0) {
                    velocityX = (r.nextInt(2 - -2) + -2);
                    velocityY = (r.nextInt(2 - -2) + -2);
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
            handler.addObject(new FoodSpores(this.getX(), this.getY(), ID.FoodSpores, sheet));
        }

        animation.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        if (velocityX == 0 && velocityY == 0)
            g.drawImage(slug_image[2], x, y, null);
        else
            animation.drawAnimation(g, x, y, 0);
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
