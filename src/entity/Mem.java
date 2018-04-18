package entity;

public class Mem{
    public int start;//开始位置
    public int length;//存储区域长度
    public boolean state;//true代表空闲，false代表占用

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Mem(int start, int length){
        this.start=start;
        this.length=length;
    }
    public Mem(int start,int length,boolean state){
        this.start=start;
        this.length=length;
        this.state=state;
    }

}