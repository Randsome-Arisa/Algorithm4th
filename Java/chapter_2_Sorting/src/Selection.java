import java.util.Random;
public class Selection {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for(int i = 0; i < N; i++) {
            int min = i;    //最小元素的索引
            for(int j = i + 1; j < N; j++)
                if(less(a[j], a[min]))
                    min = j;
            exch(a, i, min);
        }
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
        int n = 16;
        Integer[] a = new Integer[n];
        for(int i = 0; i < n; i++)
            a[i] = random.nextInt(100);
        show(a);
        Selection.sort(a);
        show(a);
    }
}