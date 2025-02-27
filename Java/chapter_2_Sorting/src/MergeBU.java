public class MergeBU {
    private static Comparable[] aux;    //辅助数组
    //归并（数组以中间为分界线，左右两个子数组分别有序）
    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k++)
            aux[k] = a[k];
        for(int k = lo; k <= hi; k++) {
            if(i > mid) //左半边用尽
                a[k] = aux[j++];
            else if(j > hi) //右半边用尽
                a[k] = aux[i++];
            else if(less(aux[j], aux[i]))   //右半边小
                a[k] = aux[j++];
            else    //左半边小
                a[k] = aux[i++];
        }
    }
    //自底向上归并排序，代码量小，且更适合于用链表存储的数据
    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for(int sz = 1; sz < N; sz *= 2)    //sz表示当前子数组的大小
            for(int lo = 0; lo < N-sz; lo += sz*2 ) //lo表示子数组的lo索引
                //当sz=k时，就把两两大小为k的子数组归并为各个大小为2k的数组
                merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
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
}