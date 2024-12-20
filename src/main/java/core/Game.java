package core;

import javax.swing.JFrame;

public class Game extends JFrame {

    public Game(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        GamePanel gamePanel = new GamePanel(this);
        this.add(gamePanel);

        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
