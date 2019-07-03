package java_games.mushroomMaze;

import javax.swing.*;
import java.awt.*;

public class MazeWindow {

    //constructor
    public MazeWindow(int width, int height, String title, MazeGame game) {

        //window frame
        JFrame frame = new JFrame(title);

        //set frame size
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        //add game class with these settings
        frame.add(game);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
