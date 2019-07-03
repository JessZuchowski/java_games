package java_games.mushroomMaze;

import java.awt.*;

public class Camera {

    private float x, y;

    public Camera(float x, float y) {
        this.x  = x;
        this.y = y;
    }

    public void tick(MazeGameObject object) {

        //lock coordinates to player object
        x += ((object.getX() - x) - 1000 / 2) * 0.05f;
        y += ((object.getY() - y) - 700 / 2) * 0.05f;

        //set parameters to window size
        if (x <= 0 ) x = 0;
        if (x >= 1000) x = 1000; //needs adjusting
        if (y <= 0) y = 0;
        if (y >= 700) y = 700; //needs adjusting


    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
