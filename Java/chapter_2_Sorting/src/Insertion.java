import java.util.Random;
public class Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;
        //如果先把最小的值放到数组的第一位，就可以免去下面内循环里j > 0的判断，但经验证这样会变慢。
        /*for(int i = 1; i < N; i++)
            if(less(a[i], a[0]))
                exch(a, i, 0);*/
        for(int i = 1; i < N; i++)
            for(int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }
    public static void sort(Comparable[] a, int lo, int hi) {
        for(int i = lo+1; i < hi+1; i++)
            for(int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }
    private static boolean less (Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    private static void exch(Comparable[]a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    private static void show(Comparable[] a) {
        for(int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }
    public static boolean isSorted(Comparable[] a) {
        for(int i = 1; i < a.length; i++)
            if(less(a[i], a[i-1]))
                return false;
        return true;
    }
    public static void main(String[] args) {
        Random random = new Random();
        int n = 100000;
        Integer[] a = new Integer[n];
        for(int i = 0; i < n; i++)
            a[i] = random.nextInt(100);
        show(a);
        long starTime=System.currentTimeMillis();
        Insertion.sort(a);
        long endTime=System.currentTimeMillis();
        show(a);
        long time = endTime - starTime;
        System.out.println(time);
    }
}