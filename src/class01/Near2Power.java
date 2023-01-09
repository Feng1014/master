package src.class01;

/**
 * 返回大于等于n，且最接近n的2的某次方的值
 */
public class Near2Power {

    public static int near2Power(int n) {
        n--;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n < 0 ? 1 : n + 1;
    }

    public static void main(String[] args) {
        int n = 120;
        System.out.println(near2Power(n));
    }
}
