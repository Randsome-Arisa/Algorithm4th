import java.util.Random;
public class Quick {
    public static void sort(Comparable[] a) {
        //打乱数组
        Random random = new Random();
        for(int i = 0; i < a.length; i++) {
            int k = random.nextInt(a.length);
            exch(a, i, k);
        }
        sort(a, 0, a.length-1);
    }
    private static void sort(Comparable[]a, int lo, int hi) {
        /*
        if(hi <= lo)
            return;
        */
        //使用插入排序处理小规模的子数组（如长度小于15）可缩短运行时间
        if(hi <= lo+15) {
            Insertion.sort(a, lo, hi);
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }
    private static int partition(Comparable[]a, int lo, int hi) {
        int i = lo, j = hi+1;   //左右两个指针
        Comparable v = a[lo];   //切分元素
        /*
        三取样切分
        采用这种方法时注意int i = lo-1而不是lo，将切分元素归位时exch(a, lo, j)中的lo应该改为切分元素的索引
        if(hi-lo > 1) { //至少有3个元素
            Comparable[] three = new Comparable[3];
            three[0] = a[0];    three[1] = a[1];    three[2] = a[2];
            Insertion.sort(three);
            v = three[1];   //三取样切分，取其中位数
        }
        else
            v = a[lo];
        */
        while(true) {
            while(less(a[++i], v))
                if(i == hi)
                    break;
            while(less(v, a[--j]))
                if(j == lo)
                    break;
            if(i >= j)
                break;
            exch(a, i, j);
        }
        exch(a, lo, j); //将切分元素放到正确的位置
        return j;   //返回切分位置
    }
    /*
    Dijkstra的三项切分快速排序能将和切分元素相等的元素归位，使得它们不会被递归处理。
    对于有大量重复元素的数组效率有所提高。
    */
    private static void sort3way(Comparable[]a, int lo, int hi) {
        //使用插入排序处理小规模的子数组（如长度小于15）可缩短运行时间
        if(hi <= lo+15) {
            Insertion.sort(a, lo, hi);
            return;
        }
        //维护三个指针lt,gt,i
        //lt使得a[lo~lt-1]的元素小于v，gt使得a[gt+1~hi]的元素都大于v，i使得a[lt~i-1]的元素都等于v，a[i~gt]的元素都未确定。
        int lt = lo, i = lo+1, gt = hi;
        Comparable v = a[lo];
        while(i <= gt) {
            int cmp = a[i].compareTo(v);
            if(cmp < 0)
                //a[i] < v 将a[lt]和a[i]交换，并将lt和i一起右移一位
                exch(a, lt++, i++);
            else if(cmp > 0)
                //a[i] > v 将a[i]和a[gt]交换，并将gt左移一位
                exch(a, i, gt--);
            else
                i++;
        }
        sort3way(a, lo, lt-1);
        sort3way(a, gt+1, hi);
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
        Quick.sort3way(a, 0, a.length-1);
        long endTime=System.currentTimeMillis();
        show(a);
        System.out.println(Quick.isSorted(a));
        long time = endTime - starTime;
        System.out.println(time);
    }
}