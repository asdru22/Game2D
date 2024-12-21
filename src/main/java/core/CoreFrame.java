package core;

import io.Sound;

import javax.swing.JFrame;

public class CoreFrame extends JFrame {

    public CoreFrame(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        //Sound.soundOff();

        CorePanel corePanel = new CorePanel(this);
        this.add(corePanel);

        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
