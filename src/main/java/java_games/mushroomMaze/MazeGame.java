package java_games.mushroomMaze;

import org.checkerframework.checker.units.qual.C;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class MazeGame extends Canvas implements  Runnable {

    //serialization? private static final long serialVersionUID = 1L;

    private boolean isRunning = false;
    private Thread thread;
    private MazeObjectHandler handler;
    private Camera camera;
    private SpriteSheet sheet;

    private BufferedImage maze = null;
    private BufferedImage sprite_sheet = null;
    private BufferedImage floor = null;

    public int nutrients = 100;
    public int hp = 100;

    //constructor
    public MazeGame() {
        new MazeWindow(1000, 563, "Mushroom Maze", this);
        start();

        handler = new MazeObjectHandler();
        camera = new Camera(0,0);

        //key listener
        this.addKeyListener(new MazeKeyInput(handler));

        //load maze and sprite sheet images
        BufferedImageLoader loader =  new BufferedImageLoader();
        maze = loader.loadImage("/maze1.png");
        sprite_sheet = loader.loadImage("/spritesheet.png");

        sheet = new SpriteSheet(sprite_sheet);
        floor = sheet.getSpriteImage(5, 2, 32, 32);

        //mouse listener
        this.addMouseListener(new MazeMouseInput(handler, camera, this, sheet));

        loadMaze(maze);

    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();

    }

    private void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        //thread to call game loop and render
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    //method to update game (60 times per second)
    public void tick() {

        //loop through objects, find player object for camera parameters
        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ID.Player) {
                camera.tick(handler.object.get(i));
            }
        }

        handler.tick();
    }

    //method to render game (nano seconds)
    public void render() {
        //preload frames behind window
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        //BEGIN DRAWING TO GAME

        //background
//        g.setColor(Color.gray);
//        g.fillRect(0, 0, 1000, 563);

        //everything between g2d.translates is translated
        g2d.translate(-camera.getX(), -camera.getY());

        //draw floor tiles
        for (int xx = 0; xx < 30 * 72; xx+=32) {
            for (int yy = 0; yy < 30 * 72; yy+=32) {
                g.drawImage(floor, xx, yy, null);
            }
        }

        handler.render(g); //needs to be under bg

        g2d.translate(camera.getX(), camera.getY());

        //render health bar
        g.setColor(Color.gray);
        g.fillRect(5, 5, 200, 32);
        g.setColor(Color.orange);
        g.fillRect(5, 5, hp * 2, 32);
        g.setColor(Color.black);
        g.drawRect(5, 5, 200, 32);

        //render nutrient bar
        g.setColor(Color.cyan);
        g.drawString("Nutrients: " + nutrients, 5, 50);

        //END DRAWING TO GAME
        g.dispose();
        bs.show();

    }

    //loading maze
    private void loadMaze(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255)
                    handler.addObject(new Wall(xx * 32, yy * 32, ID.Wall, sheet));

                if (blue == 255 && green == 0)
                    handler.addObject(new Mushroom(xx * 32, yy * 48, ID.Player, handler, this, sheet));

                if (green == 255 && blue == 0)
                    handler.addObject(new Slug(xx * 32, yy * 32, ID.Enemy, handler, sheet));

                if ( green == 255 && blue == 255)
                    handler.addObject(new Food(xx * 32, yy * 32, ID.Food, sheet));
            }
        }
    }


    public static void main (String args[]) {
        new MazeGame();
    }
}
