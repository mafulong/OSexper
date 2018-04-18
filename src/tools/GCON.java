package tools;

public class GCON {
    public static int totalMemory=17;
    public static int memAllocaWay=0;

    public static int getMemAllocaWay() {
        return memAllocaWay;
    }

    public static void setMemAllocaWay(int memAllocaWay) {
        GCON.memAllocaWay = memAllocaWay;
    }

    public static void setTotalMemory(int totalMemory) {
        GCON.totalMemory = totalMemory;
    }
}
