import controller.MainWindow;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        run(new MainWindow(), 800, 700);
    }

    public static void run(JFrame frame, int width, int height) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);
    }

}
