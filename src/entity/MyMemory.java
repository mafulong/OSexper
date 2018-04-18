package entity;

import tools.GCON;

import java.util.LinkedList;
import java.util.Random;

public class MyMemory {
    /**
     * 内存单元类：成员包括开始位置和存储区域块大小
     */

    public static LinkedList<Mem> myArray;

    public static int[] arr;
    public MyMemory() {
        init();
    }

    public static void init(){

        myArray=new LinkedList<Mem>();
        Mem mem=new Mem(0,GCON.totalMemory,true);
        myArray.add(mem);
    }

    /**
     * 申请新的内存块 FF申请方式
     * @param length 要申请块的大小
     * @return 成功返回true,申请失败返回false
     */
    public static boolean getNewMem(int length){
        if(length>GCON.totalMemory||length<=0)
            return false;
        for(int i=0;i<myArray.size();i++){
            if(myArray.get(i).state&&myArray.get(i).length>=length){
                myArray.add(i+1,new Mem(myArray.get(i).start+myArray.get(i).length-length,length,false));
                myArray.get(i).length-=length;
                if(myArray.get(i).length==0)
                    myArray.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * WF方式申请内存
     * @param length
     * @return
     */
    public static boolean getNewMemWF(int length){
        if(length>GCON.totalMemory||length<=0)
            return false;
        int index=0,maxLen=0;
        for(int i=0;i<myArray.size();i++){
            if(myArray.get(i).state&&myArray.get(i).length>=maxLen){
                index=i;
                maxLen=myArray.get(i).length;
            }
        }
        if(maxLen<length)
            return false;
        else{
            myArray.add(index+1,new Mem(myArray.get(index).start+myArray.get(index).length-length,length,false));
            myArray.get(index).length-=length;
            if(myArray.get(index).length==0)
                myArray.remove(index);
            return true;
        }
    }
    /**
     * BF方式申请内存
     * @param length
     * @return
     */
    public static boolean getNewMemBF(int length){
        if(length>GCON.totalMemory||length<=0)
            return false;
        int index=0,minLen=GCON.totalMemory+1;
        for(int i=0;i<myArray.size();i++){
            if(myArray.get(i).state&&myArray.get(i).length>=length&&myArray.get(i).length<=minLen){
                index=i;
                minLen=myArray.get(i).length;
            }
        }
        if(minLen==GCON.totalMemory+1)
            return false;
        if(minLen>=length){
            myArray.add(index+1,new Mem(myArray.get(index).start+myArray.get(index).length-length,length,false));
            myArray.get(index).length-=length;
//            System.out.println(index+" "+myArray.get(index).length);
            if(myArray.get(index).length==0)
                myArray.remove(index);
            return true;
        }
        return false;
    }

    public static boolean getNewMemLast(int length){
        if(GCON.getMemAllocaWay()==0){
            return getNewMem(length);
        }else if(GCON.getMemAllocaWay()==1){//bf
            return getNewMemBF(length);
        }else if(GCON.getMemAllocaWay()==2){//wf
            return getNewMemWF(length);
        }
        return false;
    }
    /**
     * 给内存的首地址，删除这个首地址的内存，就是把这个内存的占用状态改为空闲状态，并紧凑
     * @param start 内存首地址
     * @return 成功返回true，失败返回false
     */
    public static boolean delMem(int start){
        for(int i=0;i<myArray.size();i++){
            if(myArray.get(i).start==start&&myArray.get(i).state==false){
                myArray.get(i).state=true;
                //往上紧凑
                if(i<myArray.size()-1&&myArray.get(i+1).state){
                    myArray.get(i).length+=myArray.get(i+1).length;
                    myArray.remove(i+1);
                }
                //往下紧凑
                if(i>0&&myArray.get(i-1).state){
                    myArray.get(i-1).length+=myArray.get(i).length;
                    myArray.remove(i);
                }
                return true;

            }
        }

        return false;
    }

    /**
     * 打印分区信息
     */
    public static void arrayPrint(){
//        System.out.println(myArray.size());
        System.out.println();
        for(int i=0;i<myArray.size();i++){
            System.out.printf("%d %d %s ||",myArray.get(i).start,myArray.get(i).length,myArray.get(i).state?"空闲":"占用");
        }
        System.out.println();
    }

    /**
     * 返回分区表，包含所有状态
     * @return
     */
    public static String getSituation(){
        String str=" 开始位置 \t长度\t状态\n";
        for(int i=0;i<myArray.size();i++){
            String str1=String.format("%-3d\t%-3d\t%s\n",myArray.get(i).start,myArray.get(i).length,myArray.get(i).state?"空闲":"占用");
            str+=str1;
        }
        return str;
    }

    /**
     * 返回空闲分区表
     * @return
     */
    public static String getSituationWithUseful(){
        String str=" 开始位置 \t长度\t状态\n";
        for(int i=0;i<myArray.size();i++){
            if(myArray.get(i).state){
                String str1=String.format("%-3d\t%-3d\t%s\n",myArray.get(i).start,myArray.get(i).length,myArray.get(i).state?"空闲":"占用");
                str+=str1;
            }
        }
        return str;
    }

    /**
     * 内存全部初始化
     */
    public static void clear(){
        if(myArray!=null)
            myArray.clear();
        init();
    }

    /**
     * 返回空闲分区数量
     * @return 空闲分区数量
     */
    public static int getUsefulNum(){
        int res=0;
        for(int i=0;i<myArray.size();i++){
            if(myArray.get(i).state)
                res++;
        }
        return res;
    }
    public static void initNew(){
        Random ran=new Random();
        if(myArray!=null)
            myArray.clear();
        boolean flag=true;
        int start=0;
        for(int i=0;i<10;i++){
            int t=ran.nextInt((int)((double)GCON.totalMemory/6))+2;
            if(start+t<GCON.totalMemory-1){
                myArray.add(new Mem(start,t,flag));
            }else{
                myArray.add(new Mem(start,GCON.totalMemory-start,flag));
                start+=t;
                break;

            }
            flag=!flag;
            start+=t;

        }
        if(start<GCON.totalMemory-1)
            myArray.add(new Mem(start,GCON.totalMemory-start,flag));
    }


    public static void initNew(int[] arr1){
        arr=arr1;
        Random ran=new Random();
        if(myArray!=null)
            myArray.clear();
        boolean flag=true;
        int start=0;
        for(int i=0;i<arr.length;i++){
            int t=arr[i];
            if(start+t<GCON.totalMemory-1){
                myArray.add(new Mem(start,t,flag));
            }else{
                myArray.add(new Mem(start,GCON.totalMemory-start,flag));
                start+=t;
                break;

            }
            flag=!flag;
            start+=t;


        }
        if(start<GCON.totalMemory-1)
            myArray.add(new Mem(start,GCON.totalMemory-start,flag));
    }
}
