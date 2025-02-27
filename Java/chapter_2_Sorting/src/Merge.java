public class Merge {
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
        //如果按降序将a[]的后半部分复制到aux[]，就可以省去内循环中某半边是否用尽的代码，但这样排序的结果是不稳定的
        /*
        for(int k = lo; k < mid+1; k++)
            aux[k] = a[k];
        for(int k = hi, midk = mid+1; k > mid; k--, midk++)
            aux[midk] = a[k];
        for(int k = lo; k <= hi; k++) {
            if(less(aux[j], aux[i]))   //右半边小
                a[k] = aux[j++];
            else    //左半边小
                a[k] = aux[i++];
        */
        }
    }
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }
    //自顶向下递归归并排序
    private static void sort(Comparable[]a , int lo, int hi) {
        if(hi <= lo)
            return;
        //使用插入排序处理小规模的子数组（如长度小于15）可缩短运行时间
        if(hi-lo < 15) {
            Insertion.sort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        //如果a[mid] <= a[mid+1]就认为是有序并跳过merge。可以把任意有序的子数组的运行时间变为线性
        if(less(a[mid], a[mid+1]))
            return;
        merge(a, lo , mid, hi);
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