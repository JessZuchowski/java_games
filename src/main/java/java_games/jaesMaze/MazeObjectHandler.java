package java_games.jaesMaze;

import java.awt.*;
import java.util.LinkedList;

public class MazeObjectHandler {

    //list of objects
    LinkedList<MazeGameObject> object = new LinkedList<>();

    //key press defaults
    public boolean up = false, down = false, left = false, right = false;

    //for tick and render:
    //store each object in temporary object, get id from LL, call
    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            MazeGameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            MazeGameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public void addObject(MazeGameObject tempObject) {
        object.add(tempObject);
    }

    public void removeObject(MazeGameObject tempObject) {
        object.remove(tempObject);
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
