import entity.MyMemory;
import gui.MainGui;
import tools.Constant;
import tools.beautify;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void init(){
        MyMemory.init();
//        for(int i=0;i<10;i++){
//            MyMemory.getNewMemWF(i);
//        }
    }
    public static void main(String[] args){
        init();
        beautify.beautifyFunc();
        JFrame frame = new JFrame("Memory Allocation");
        frame.setContentPane(new MainGui().getContentPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Dimension size = new Dimension(
                (int) (Constant.screenSize.getWidth() * 3 / 4),
                (int) (Constant.screenSize.getHeight() * 3/ 4));
        frame.setSize(size);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
