package ui;

import javax.swing.*;
import java.awt.*;

// CODE ATTRIBUTE: https://stackoverflow.com/questions/8701716/how-to-remove-title-bar-in-jframe On removing title bar
// And https://docs.oracle.com/javase/tutorial/uiswing/examples/misc/SplashDemoProject/src/misc/SplashDemo.java
// On pausing program
// Represents the splash screen displayed at the start of the Cat Shelter App
public class SplashScreen extends JFrame {

    //EFFECTS: Displays a gif for a few seconds before closing the window.
    public SplashScreen() {
        this.setUndecorated(true); // Removes title bar
        this.setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        ImageIcon gif = new ImageIcon("./data/Photo/catshelter.gif");
        JLabel splashGif = new JLabel(gif);

        this.add(splashGif);
        this.setSize(480, 480);

        try {
            Thread.sleep(2700); // pauses program for 2.7 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dispose();
    }
}
