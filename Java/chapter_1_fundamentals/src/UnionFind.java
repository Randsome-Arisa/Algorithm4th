/*
动态连通性问题：
输入一列整数对，每个整数对(p,q)表示p和q是相连的，每个点称作触点，每个相连的触点的集合称作连通分量
输出所有不能相连的整数对（即连通分量）的数量

                            union-find算法的API
**********************************************************************************
    UF(int N)                               初始化N个触点
    void union(int p, int q)                连接p和q
    int find(int p)                         p所在连通分量的标识符
    boolean connected(int p, int q)         如果p和q在同一个连通分量中则返回true
    int getCount()                          连通分量的数量
 */

import edu.princeton.cs.algs4.*;

// 算法各个实现的基类
class UF {
    protected int[] id;   // 以每个触点为索引的连通分量的id
    protected int count;  // 连通分量的数量
    public int getCount() {return count;}
}

// union-find算法的各个实现只有这两个方法不同
interface union_find {
    public abstract int find(int p);
    public abstract void union(int p, int q);
    // 根据id[]来确定两个触点是否位于同一个连通分量
    public default boolean connected(int p, int q) {return find(p) == find(q);}
}

// 第一种实现：quick-find算法，O(n²)
// 保证id[p] == id[q]时p和q连接，即要保证同一个连通分量中所有触点的id[]值都相同
class QuickFind extends UF implements union_find {
    public QuickFind(int N) {
        count = N;
        id = new int[N];
        // 初始化，为每个连通分量分配一个id
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    @Override
    public int find(int p) {
        return id[p];
    }

    @Override
    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if (pID == qID)
            return;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID)
                id[i] = qID;
        }
        count--;
    }
}

// 第二种实现：quick-union算法，最坏情况下也是O(n²)
// 相当于构造一个森林，每一个连通分量都是一棵树
// 每个触点所对应的id[]值都是它所在的连通分量里的另一个触点（或者它自己，即为根触点）
// 当两个触点的根触点相同时，说明他们在同一个连通分量中
class QuickUnion extends UF implements union_find {
    public QuickUnion(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    @Override
    public int find(int p) {
        while (p != id[p])
            p = id[p];
        return p;

        /*
        int temp = p;
        // 找到根触点
        while (p != id[p])
            p = id[p];
        // 把沿途的触点都直接链接到根触点p上
        while (temp != id[temp])
            id[temp] = p;
        return p;
         */
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        id[pRoot] = qRoot;
        count--;
    }
}

// 第三种实现：加权quick-union算法，O(logn)
// 相较于quick-union算法，改进之处在于会记录每棵树的大小，只将较小的书链接到较大的树上
class WeightedQuickUnion extends QuickUnion implements union_find {
    private int[] size; // 每个连通分量（树）的大小
    public WeightedQuickUnion(int N) {
        super(N);
        size = new int[N];
        for (int i = 0; i < N; i++)
            size[i] = 1;
    }

    @Override
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j)
            return;
        if (size[i] < size[j]) {
            id[i] = j;
            size[j] += size[i];
        }
        else {
            id[j] = i;
            size[i] += j;
        }
        count--;
    }
}
public class UnionFind {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickUnion uf = new QuickUnion(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.getCount() + " components");
    }
}