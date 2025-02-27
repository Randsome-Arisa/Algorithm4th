public class Heap {
    //堆排序
    public void sort(Comparable[] a) {
        int n = a.length;
        //构造堆，从k = n/2开始，最底下的一半视为大小为1的堆
        for(int k = n/2; k >= 1; k--)
            sink(a, k, n);
        //堆排序
        while(n > 1) {
            exch(a, 1, n--);    //将最大的元素沉到最低（数组右端），然后堆的大小-1
            sink(a, 1, n);
        }
    }
    private boolean less (Comparable[]a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }
    private void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    //用于堆排序的sink()，n是数组大小
    private void sink(Comparable[] a, int k, int n) {
        while(2*k < n) {
            int j = 2*k;
            if(j < n && less(a, j, j+1))   //在k的两个子结点中选择较大的一个与其交换
                j++;
            if(!less(a, k, j))
                break;
            exch(a, k ,j);
            k = j;
        }
    }
    private static void show(Comparable[] a) {
        for(int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }
    public boolean isSorted(Comparable[] a) {
        for(int i = 1; i < a.length; i++)
            if(less(a, i, i-1))
                return false;
        return true;
    }
}