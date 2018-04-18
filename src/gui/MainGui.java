package gui;

import entity.MyMemory;
import entity.form1;
import tools.Constant;
import tools.GCON;
import tools.beautify;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MainGui {
    private JPanel jp;
    private JPanel jpleft;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JPanel jpright;
    private JPanel jpcenter;
    private JComboBox comboBox1;
    private JButton beginButton;
    private JButton CLEARButton;
    private JButton RANDOWMANDCOMPAREButton;
    private JTextField newTotalMemory;
    private JButton updateTotalMemoryButton;
    private JTextArea textArea3;
    private JTextField textField1;
    private JButton newMemButton;
    private JButton delMem;
    private JTextField textField2;
    private JButton textArea3ClearButton;
    private JButton RANDOM20Button;

    /**
     * 更新textarea1和textarea2的内容
     */
    public void updateTextArea(){
        textArea1.setText(MyMemory.getSituation());
        textArea2.setText(MyMemory.getSituationWithUseful());
    }
    public MainGui() {
        init();
        /**
         * 监听更新总内存空间大小button
         */
        updateTotalMemoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GCON.setTotalMemory(Integer.parseInt(newTotalMemory.getText()));
                System.out.println("newtotalmemory:      "+newTotalMemory.getText()+"\n");
                textArea3.append("newtotalmemory:     "+newTotalMemory.getText()+"\n");
                MyMemory.clear();
                updateTextArea();
            }
        });
        /**
         * 监听设置分配内存button
         */
        beginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GCON.setMemAllocaWay(comboBox1.getSelectedIndex());
                System.out.println("newMemAllocaWay:     "+comboBox1.getSelectedIndex());
                textArea3.append("newMemAllocaWay:     "+comboBox1.getItemAt(comboBox1.getSelectedIndex())+"\n");

            }
        });
        /**
         * 监听开始申请内存块button
         */
        newMemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("申请内存按钮点击");
                if(MyMemory.getNewMemLast(Integer.parseInt(textField1.getText()))){
                    System.out.println("申请成功");
                    textArea3.append("内存大小： "+textField1.getText()+"  申请成功\n");

                }else{
                    System.out.println("申请失败");
                    textArea3.append("内存大小： "+textField1.getText()+"  申请失败\n");
                }
                updateTextArea();

            }
        });
        /**
         * 监听返回内存button
         */
        delMem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MyMemory.delMem(Integer.parseInt(textField2.getText()))){
                    System.out.println("删除内存成功");
                    textArea3.append("删除内存成功"+"  删除开始位置"+textField2.getText()+"\n");
                    updateTextArea();
                }else{
                    System.out.println("删除内存失败");
                    textArea3.append("删除内存失败\n");

                }
            }
        });
        /**
         * 监听Clear button
         */
        CLEARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyMemory.clear();
                updateTextArea();
                textArea3.append("清除成功\n");
            }
        });
        /**
         * 清空textarea3显示信息
         */
        textArea3ClearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea3.setText("");
            }
        });
        /**
         * 监听RANDOM 20 button
         * 生成20个随机内存块
         */
        RANDOM20Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyMemory.clear();
                Random ran=new Random();
                for(int i=0;i<20;i++){
                    MyMemory.getNewMemLast(ran.nextInt(GCON.totalMemory/5)+2);
                }
                updateTextArea();
                textArea3.append("随机20个内存块成功\n");
            }
        });
        /**
         * 监听randowm and compare button
         */
        RANDOWMANDCOMPAREButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                beautify.beautifyFunc();
//                jp.setVisible(false);
                JFrame frame = new JFrame("Main");
                frame.setContentPane(new Comparison().getContentPanel());
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                Dimension size = new Dimension(
                        (int) (Constant.screenSize.getWidth() * 1/2),
                        (int) (Constant.screenSize.getHeight() * 3/4));
                frame.setSize(size);
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
            }
        });
    }

    /**
     * 初始化
     */
    public void init(){

        updateTextArea();
        textArea3.append("Beginning...\n");
        newTotalMemory.setText(String.valueOf(GCON.totalMemory));

    }
    public Container getContentPanel(){
        return jp;
    }

    public static void main(String[] args) {

        beautify.beautifyFunc();
        JFrame frame = new JFrame("Main");
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
