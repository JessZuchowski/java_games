package java_games.mushroomMaze;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MazeMouseInput extends MouseAdapter {

    private MazeObjectHandler handler;
    private Camera camera;

    public MazeMouseInput (MazeObjectHandler handler, Camera camera) {
        this.handler = handler;
        this.camera = camera;
    }

    public void mousePressed(MouseEvent e) {

        int mx = (int) (e.getX() + camera.getX());
        int my = (int) (e.getY() + camera.getY());

        for (int i = 0; i < handler.object.size(); i ++) {
            MazeGameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                handler.addObject(new SporeProjectile(tempObject.getX() + 16, tempObject.getY() + 24, ID.Projectile, handler, mx, my));
                handler.addObject(new SporeProjectile(tempObject.getX() + 15, tempObject.getY() + 20, ID.Projectile, handler, mx, my));
                handler.addObject(new SporeProjectile(tempObject.getX() + 16, tempObject.getY() + 16, ID.Projectile, handler, mx, my));
                handler.addObject(new SporeProjectile(tempObject.getX() + 15, tempObject.getY() + 12, ID.Projectile, handler, mx, my));
            }
        }
    }
}
