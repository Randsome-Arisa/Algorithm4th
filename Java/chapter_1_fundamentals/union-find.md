# 动态连通性问题
## 问题描述
输入一列整数对，其中每个整数都表示一个某种类型的对象，一对整数p,q可以理解为p和q是相连的。“相连”是一种**等价**关系（即满足自反性，对称性，传递性）。当且仅当两个对象相连时他们属于同一个等价类。编写一个程序，如果输入的p,q不是相连的，则将他们相连，否则忽略。
## union-find算法
把输入的整数叫做**触点**，用一个以**触点为索引**的数组id[]作为数据结构。定义union-find算法的API。
`public class UF`
|方法|描述|
|----|---|
|UF(int N)|初始化N个触点|
|void union(int p, int q)|在p和q之间添加一条链接|
|int find(int p)|p所在的连通分量的标识符|
|boolean connected(int p, int q)|如果p,q存在链接则返回true|
|int count()|连通分量的数量|
```java
public class UF {
    private int[] id;
    private int count;  //连通分量数
    public UF(int N) {
        count = N;
        id = new int[N];
        for(int i = 0; i < N; i++)
            id[i] = i;
    }
    public int count() {
        return count;
    }
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    //下面两个方法根据实现而不同
    public int find(int p) {}
    public void union(int p, int q) {}
}
```
## 实现
union-find算法有三种实现。分别是quick-find,quick-union以及加权quick-union。
他们均通过id[]数组来确定两个触点是否位于相同的连通分量。
### quick-find算法
这种实现是保证**当且仅当id[p] == id[q]时p和q是连通的。** 即所有连通的触点的id[]值都相等。每个触点的id[]值都是他们所在连通分量的标识符。
当p,q不相连时，p所在的连通分量中所有触点的id[]值都相等，q所在的连通分量中所有触点的id[]值也都等于另一个值。要将这两个触点相连，就要把他们的id[]值变成同一个值。因此就需要访问整个数组，将所有与id[p]相等的值都改为id[q]（反之亦可）。
```java
public int find(int p) {
    return id[p];
}
public void union(int p, int q) {
    int pID = find(p);
    int qID = find(q);
    //如果相连则忽略
    if(pID == qID)
        return;
    for(int i = 0; i < N; i++)
        if(id[i] == pID)
            id[i] = qID;
    count--;
}
```
假设有10个触点的轨迹。左表表示输入的p,q，右表表示触点的id[]值。
|p q|0 1 2 3 4 5 6 7 8 9|
|---|-------------------|
|4 3|0 1 2 3 3 5 6 7 8 9|
|3 8|0 1 2 8 8 5 6 7 8 9|
find()操作只需要访问一次数组，而union()操作访问数组的次数在N+3到2N+1之间。因为每次union都需要调用2次find()，然后最好的情况是没有其他触点和p相连，因此只需要判断N-1次是否相等。最坏的情况是每个都与p相连，判断后再赋值需要访问2（N-1）次数组。
解决动态连通性问题需要调用N-1次union，则时间复杂度是O(n²)。
### quick-union算法
这种实现赋予id[]数组不同的含义：每个触点的id[]值都是与他相连的另一个触点的名称，直到最后到达一个根触点，它的id[]值就是自己。（即树的结构）。**当且仅当两个触点的根触点相同时他们是连通的**。在连接p,q时，只需要将p的根触点的id[]值改为q的根触点（反之亦可）。
```java
public int find(int p) {
    //一直上溯到根触点
    while(p != id[p])
        p = id[p];
    return p;
}
public void union(int p, int q) {
    int pRoot = find(p);
    int qRoot = find(q);
    //如果相连则忽略
    if(pRoot == qRoot)
        return;
    id[pRoot] = qRoot;
    count--;
}
```
|p q|0 1 2 3 4 5 6 7 8 9|
|---|-------------------|
|4 3|0 1 2 3 3 5 6 7 8 9|
|3 8|0 1 2 8 3 5 6 7 8 9|
find()最好的情况只要访问一次数组，而最坏的情况需要访问2N+1次。quick-union的时间成本依赖于输入，因此不好分析，只需要直到最坏情况下它的时间复杂度也是O(n²)即可。
### 加权quick-union算法
这种实现需要添加一个数组`private int[] sz`用于保存各个根触点对应的连通分量的大小，在构造函数中将所有触点的sz[]值初始化为1。这样就可以通过比较各个根触点的sz[]值来永远将小树连接到大树。
```java
public int find(int p) {
    //与quick-union的find()方法一样
}
public void union(int p, int q) {
    int pRoot = find(p);
    int qRoot = find(q);
    //如果相连则忽略
    if(pRoot == qRoot)
        return;
    if(sz[pRoot] < sz[qRoot]) {
        //将p归到q的连通分量中
        id[pRoot] = qRoot;
        sz[qRoot] += sz[pRoot];
    }
    else {
        id[qRoot] = pRoot;
        sz[pRoot] += sz[qRoot];
    }
    count--;
}
```
加权quick-union算法各个操作的时间复杂度是O(logN)。
### 最优算法
对加权quick-union算法进行路径压缩是目前的最优解法。理想情况下每个触点都直接链接到它的根触点，但又不能像quick-find那样大规模的修改id[]值，因此可以在检查节点的同时将他们直接连接到根触点。所得到的结果是几乎完全扁平化的树。
```java
public int find(int p) {
    int root = p;
    while(root != id[root])
        root = id[root];
    while(p != root)
        id[p] = root;
    return root;
}
```