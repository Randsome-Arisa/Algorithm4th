import java.util.Comparator;
/*返回最大值的优先队列
                    API
    MaxPQ()             创建一个优先队列
    MaxPQ(int maxN)      创建一个初始容量为max的优先队列
    MaxPQ(key[] a)      用a[]中元素创建一个优先队列
    void insert(key v)  插入一个元素
    key max()           返回最大元素
    key delMax()        删除并返回最大元素
    boolean isEmpty()   判空
    int size()          元素个数
*/
//基于堆的实现
public class MaxPQ<key extends Comparable<key>> {
    private key[] pq;   //基于堆的完全二叉树
    private int N = 0;  //不使用pq[0]
    public MaxPQ(int maxN) {
        pq = (key[]) new Comparable[maxN+1];
    }
    public MaxPQ(key[] a) {

    }
    public int size() {
        return N;
    }
    public boolean isEmpty() {
        return N == 0;
    }
    public void insert(key v) {
        pq[++N] = v;
        swim(N);
    }
    public key max() {
        return pq[1];
    }
    public key delMax() {
        key max = pq[1];
        exch(1, N--);
        pq[N+1] = null; //防止对象游离
        sink(1);
        return max;
    }
    private boolean less (int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }
    private void exch(int i, int j) {
        key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
    private void swim(int k) {
        while(k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k /= 2;
        }
    }
    private void sink(int k) {
        while(2*k <= N) {
            int j = 2*k;
            if(j < N && less(j, j+1))   //在k的两个子结点中选择较大的一个与其交换
                j++;
            if(!less(k, j))
                break;
            exch(k ,j);
            k = j;
        }
    }
}