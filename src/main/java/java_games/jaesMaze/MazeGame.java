package java_games.jaesMaze;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class MazeGame extends Canvas implements  Runnable {

    //serialization? private static final long serialVersionUID = 1L;

    private boolean isRunning = false;
    private Thread thread;
    private MazeObjectHandler handler;

    //constructor
    public MazeGame() {
        new MazeWindow(1000, 700, "Jae's Maze", this);
        start();

        handler = new MazeObjectHandler();
        //key listener for canvas
        this.addKeyListener(new MazeKeyInput(handler));

        handler.addObject(new Mushroom(500, 350, ID.Player, handler));

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
        //BEGIN DRAWING TO GAME

        //background
        g.setColor(Color.gray);
        g.fillRect(0, 0, 1000, 700);

        handler.render(g); //needs to be under bg


        //END DRAWING TO GAME
        g.dispose();
        bs.show();

    }

    public static void main (String args[]) {
        new MazeGame();
    }
}
