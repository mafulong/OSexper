package entity;

import javax.swing.*;
import tools.*;
import java.awt.*;

public class form1 {
    private JPanel jp1;

    public Container getContentPanel(){
        return jp1;
    }
    public static void main(String[] args) {
        beautify.beautifyFunc();
//        JFrame frame = new JFrame("form1");
//        frame.setContentPane(new form1().jp1);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);

        JFrame frame = new JFrame("Form");
        frame.setContentPane(new form1().getContentPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Dimension size = new Dimension(
                (int) (Constant.screenSize.getWidth() * 1 / 2),
                (int) (Constant.screenSize.getHeight() * 1/ 2));
        frame.setSize(size);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
