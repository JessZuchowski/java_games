package java_games.mushroomMaze;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Mushroom extends MazeGameObject{

    private MazeObjectHandler handler;
    private MazeMouseInput mouseInput;
    public  MazeGame game;

    //array of images
    private BufferedImage[] mushroom_image = new BufferedImage[18];
    Animation animationDown;
    Animation animationUp;
    Animation animationLeft;
    Animation animationRight;
    Animation animationSpore;

    public Mushroom(int x, int y, ID id, MazeObjectHandler handler, MazeGame game, SpriteSheet sheet) {
        super(x, y, id, sheet);
        this.handler = handler;
        this.game = game;

        //down images
        mushroom_image[0] = sheet.getSpriteImage(1, 1, 32, 48);
        mushroom_image[1] = sheet.getSpriteImage(2, 1, 32, 48);
        mushroom_image[2] = sheet.getSpriteImage(3, 1, 32, 48);
        //up images
        mushroom_image[3] = sheet.getSpriteImage(8, 1, 32, 48);
        mushroom_image[4] = sheet.getSpriteImage(9, 1, 32, 48);
        mushroom_image[5] = sheet.getSpriteImage(10, 1, 32, 48);
        //left images
        mushroom_image[6] = sheet.getSpriteImage(13, 1, 32, 48);
        mushroom_image[7] = sheet.getSpriteImage(14, 1, 32, 48);
        mushroom_image[8] = sheet.getSpriteImage(15, 1, 32, 48);
        mushroom_image[9] = sheet.getSpriteImage(16, 1, 32, 48);
        mushroom_image[10] = sheet.getSpriteImage(17, 1, 32, 48);
        //right images
        mushroom_image[11] = sheet.getSpriteImage(18, 1, 32, 48);
        mushroom_image[12] = sheet.getSpriteImage(19, 1, 32, 48);
        mushroom_image[13] = sheet.getSpriteImage(20, 1, 32, 48);
        mushroom_image[14] = sheet.getSpriteImage(21, 1, 32, 48);
        mushroom_image[15] = sheet.getSpriteImage(22, 1, 32, 48);
        //spore image
        mushroom_image[16] = sheet.getSpriteImage(4, 1, 32, 48);
        mushroom_image[17] = sheet.getSpriteImage(23, 1, 32, 48);

        //set speed and images for animations
        animationDown = new Animation(4, mushroom_image[0], mushroom_image[1],
                mushroom_image[0], mushroom_image[2]);
        animationUp = new Animation(4, mushroom_image[3], mushroom_image[4],
                mushroom_image[3], mushroom_image[5]);
        animationLeft = new Animation(5, mushroom_image[6], mushroom_image[7],
                mushroom_image[8], mushroom_image[7], mushroom_image[6],
                mushroom_image[9], mushroom_image[10], mushroom_image[9]);
        animationRight = new Animation(5, mushroom_image[11], mushroom_image[12],
                mushroom_image[13], mushroom_image[12], mushroom_image[11],
                mushroom_image[14], mushroom_image[15], mushroom_image[14]);
        animationSpore = new Animation(4, mushroom_image[16], mushroom_image[17]);
    }

    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;
        
        collision();

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

        //run animations
        animationDown.runAnimation();
        animationUp.runAnimation();
        animationLeft.runAnimation();
        animationRight.runAnimation();
        animationSpore.runAnimation();
    }

    private void collision() {

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
            //enemy collision damage
            if (tempObject.getId() == ID.Enemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    game.hp--;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (velocityX == 0 && velocityY == 0)
            animationSpore.drawAnimation(g, x, y, 0);
        else if (velocityY == -2)
            animationUp.drawAnimation(g, x, y, 0);
        else if (velocityY == 2)
            animationDown.drawAnimation(g, x, y, 0);
        else if (velocityX == -2)
            animationLeft.drawAnimation(g, x, y, 0);
        else if (velocityX == 2)
            animationRight.drawAnimation(g, x, y, 0);
    }

    @Override
    public Rectangle getBounds() {

        return new Rectangle(x, y, 32, 48);
    }
}
