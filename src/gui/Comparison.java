package gui;

import entity.Mem;
import entity.MyMemory;
import tools.Constant;
import tools.GCON;
import tools.beautify;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class Comparison {
    private JPanel jp;
    private JPanel jpBottom;
    private JTextField a2TextField;
    private JTextField a4TextField;
    private JTextField a20TextField;
    private JButton compareButton;
    private JTextArea textArea1;
    private JButton randomNew;
    private JButton clearButton;
    private JPanel jpTop;
    private JTextArea textArealeft;
    private JTextArea textArearight;
    private JTextArea textAreaCenter;
    private JButton clearResultButton;
    private JButton reset;
    private JPanel jpbottom;
    public int [] arr2={3,4,3,3,4,6,3,4,7,4,8,5,8,9,4,4,3,4};
    public Comparison() {
        compareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        /**
         * 监听随机生成按钮
         */
        randomNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearResult();
                textArea1.append("------------------------------------------\n");
                int a=Integer.parseInt(a2TextField.getText());
                int b=Integer.parseInt(a4TextField.getText());
                int n=Integer.parseInt(a20TextField.getText());
                Random ran=new Random();
                String [] arr={" FF"," BF","WF"};
                //生成随机数组
                int [] arr1=new int[n];
                textArea1.append("随机结果为:\n");
                for(int i=0;i<n;i++){
                    int next=ran.nextInt(b-1)+a;
                    arr1[i]=next;
                    textArea1.append(String.valueOf(next)+ " ");
                }
                textArea1.append("\n");
                //开始性能测试
//                System.out.println("myarray:  "+myArray1.size());
                for(GCON.memAllocaWay=0;GCON.memAllocaWay<3;GCON.memAllocaWay++){
                    MyMemory.initNew(arr2);
                    MyMemory.arrayPrint();

                    for(int i=0;i<n;i++){
//                        textArea1.append(String.valueOf(next)+ " ");
                        if(MyMemory.getNewMemLast(arr1[i])){
                            textArea1.append(arr1[i]+"   申请分配成功\n");
                        }else{
                            textArea1.append(arr1[i]+"   申请分配失败\n");
                        }
                    }
                    if(GCON.memAllocaWay==0){
                        textArealeft.append(MyMemory.getSituation());
                    }else if(GCON.memAllocaWay==1){
                        textAreaCenter.append(MyMemory.getSituation());
                    }else if(GCON.memAllocaWay==2){
                        textArearight.append(MyMemory.getSituation());
                    }
                    int usefulNum=MyMemory.getUsefulNum();
                    textArea1.append(arr[GCON.memAllocaWay]+"    空闲分区数量为\t"+usefulNum+"\n");

                }
                GCON.setMemAllocaWay(0);

            }
        });
        /**
         * 清空操作结果面板内容
         */
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
            }
        });
        /**
         * 清空上面的三个面板内容
         */
        clearResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearResult();
            }
        });
        /**
         * 初始化
         */
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyMemory.initNew(arr2);
                textArea1.append(MyMemory.getSituation());
                textArea1.append("已经重置\n");
            }
        });
    }
    public void clearResult(){
        textArealeft.setText("");
        textAreaCenter.setText("");
        textArearight.setText("");
    }

    public Container getContentPanel(){
        return jp;
    }
    public static void main(String[] args) {

        beautify.beautifyFunc();
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Comparison().getContentPanel());
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
