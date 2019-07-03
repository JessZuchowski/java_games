package java_games.jaesMaze;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;

public class MazeKeyInput extends KeyAdapter {

    MazeObjectHandler handler;

    public MazeKeyInput(MazeObjectHandler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        //loop through all objects
        for (int i = 0; i < handler.object.size(); i++) {
            MazeGameObject tempObject = handler.object.get(i);

            //handle objects by ID
            if (tempObject.getId() == ID.Player) {
                //move player (ASWD, arrow pad, numeric key pad)
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP || key == KeyEvent.VK_KP_UP) handler.setUp(true);
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_KP_DOWN) handler.setDown(true);
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_KP_LEFT) handler.setLeft(true);
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_KP_RIGHT) handler.setRight(true);

            }
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        //loop through all objects
        for (int i = 0; i < handler.object.size(); i++) {
            MazeGameObject tempObject = handler.object.get(i);

            //handle objects by ID
            if (tempObject.getId() == ID.Player) {
                //stop player
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP || key == KeyEvent.VK_KP_UP) handler.setUp(false);
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_KP_DOWN) handler.setDown(false);
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_KP_LEFT) handler.setLeft(false);
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_KP_RIGHT) handler.setRight(false);

            }
        }
    }
}