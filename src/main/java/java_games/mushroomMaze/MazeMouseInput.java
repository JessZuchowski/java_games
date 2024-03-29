package java_games.mushroomMaze;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MazeMouseInput extends MouseAdapter {

    private MazeObjectHandler handler;
    private Camera camera;
    private MazeGame game;
    private SpriteSheet sheet;

    public MazeMouseInput (MazeObjectHandler handler, Camera camera, MazeGame game, SpriteSheet sheet) {
        this.handler = handler;
        this.camera = camera;
        this.game = game;
        this.sheet = sheet;
    }

    public void mousePressed(MouseEvent e) {

        int mx = (int) (e.getX() + camera.getX());
        int my = (int) (e.getY() + camera.getY());

        for (int i = 0; i < handler.object.size(); i ++) {
            MazeGameObject tempObject = handler.object.get(i);

            //can only disperse spores if stores are available
            if (tempObject.getId() == ID.Player && game.spores >=1) {
                handler.addObject(new SporeProjectile(tempObject.getX() + 16, tempObject.getY() + 24, ID.Projectile, handler, mx, my, sheet));
                handler.addObject(new SporeProjectile(tempObject.getX() + 15, tempObject.getY() + 20, ID.Projectile, handler, mx, my, sheet));
                game.spores--;
                handler.addObject(new SporeProjectile(tempObject.getX() + 16, tempObject.getY() + 16, ID.Projectile, handler, mx, my, sheet));
                handler.addObject(new SporeProjectile(tempObject.getX() + 15, tempObject.getY() + 12, ID.Projectile, handler, mx, my, sheet));
                game.spores--;
            }
        }
    }
}
