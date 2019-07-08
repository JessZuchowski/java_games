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

    private BufferedImage maze = null;

    //constructor
    public MazeGame() {
        new MazeWindow(1000, 700, "Mushroom Maze", this);
        start();

        handler = new MazeObjectHandler();
        camera = new Camera(0,0);

        //key and mouse listeners
        this.addKeyListener(new MazeKeyInput(handler));
        this.addMouseListener(new MazeMouseInput(handler, camera));

        //load maze image
        BufferedImageLoader loader =  new BufferedImageLoader();
        maze = loader.loadImage("/maze1.png");

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
        double amountofTicks = 60.0;
        double ns = 1000000000 / amountofTicks;
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
        g.setColor(Color.gray);
        g.fillRect(0, 0, 1000, 700);

        //everything between g2d.translates is translated, keep bg above
        g2d.translate(-camera.getX(), -camera.getY());

        handler.render(g); //needs to be under bg

        g2d.translate(camera.getX(), camera.getY());

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
                    handler.addObject(new Wall(xx * 20, yy * 20, ID.Wall));

                if (blue == 255)
                    handler.addObject(new Mushroom(xx * 20, yy * 30, ID.Player, handler));

                if (green == 255)
                    handler.addObject(new Slug(xx * 30, yy * 20, ID.Enemy, handler));
            }
        }
    }


    public static void main (String args[]) {
        new MazeGame();
    }
}
