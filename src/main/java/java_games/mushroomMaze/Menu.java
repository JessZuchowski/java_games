package java_games.mushroomMaze;

import java.awt.*;

public class Menu {

    public Rectangle playButton = new Rectangle(MazeGame.WIDTH / 2 + 120, 150, 100, 50);
    public Rectangle helpButton = new Rectangle(MazeGame.WIDTH / 2 + 120, 250, 100, 50);
    public Rectangle quitButton = new Rectangle(MazeGame.WIDTH / 2 + 120, 350, 100, 50);

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Font font1 = new Font("arial", Font.BOLD, 50);
        g.setFont(font1);
        g.setColor(Color.black);
        g.drawString("Mushroom Maze", MazeGame.WIDTH / 2, 100);

        g2d.draw(playButton);
        g2d.draw(helpButton);
        g2d.draw(quitButton);
    }
}
