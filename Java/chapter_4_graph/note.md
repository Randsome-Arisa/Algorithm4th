<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2023-01-16 23:01:09
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2023-01-18 14:58:43
 * @FilePath: \undefinedd:\Desktop\Documents\Java\Algorithms_4\chapter_4_graph\note.md
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
* DFS用递归的方式将所有顶点遍历一次，BFS则用队列遍历。
* 拓扑排序实际上就是顶点的逆后序排列，即在DFS递归之后将顶点压入栈。
```java
private void dfs(Digraph g, int v) {
    visited[v] = true;
    for (int i : g.adj(v)) {
        if (!visited[i])
            dfs(g, i);
        }
    // 遍历后压入栈
    reservePost.push(v);
}
```

## Kosaraju算法
用两次DFS求得有向图的强连通分量。（证明看不懂，就只记算法过程了）
先对原图进行一次DFS，获得其逆后序排列。即在DFS递归中越靠后完成递归的顶点越在栈的上面。
再对原图的逆图（所有边的方向反向）进行一次DFS，从一个顶点开始能搜索到的最大区块就是该点所在的强连通分量。

## Dijkstra算法
若源点s -> w的距离小于s -> v -> w，则更新s到w的距离和路径。这个过程叫做**relax**。
而对于无环有向图，仅仅将relax的顺序从随机改为拓扑顺序，则可以将时间复杂度从$O(ElogV)$降低到$O(E+V)$。并且优化后的代码也可以用来处理关键路径问题。
```java

```

## Bellman-Ford算法
首先举一个用到负权值的例子，在AOE网中，如果某项活动有deadline，例如④必须在②开始后的20个时间单位内开始，则有4->2的权值为-20。
再明确**负权重环**：指一个总权重（环上所有边的权值之和）为负的有向环。

那么考虑这样一个情况。若s->v的路径上有负权重环，则s->v的最短路径是没有意义的，因为可以利用此环构造出任意小的路径。因此在实际应用中，应该努力的方向不是如何在有负权重环的图中寻找最短路径，而是修正原图以得到没有负权重环的图。

Dijkstra算法只能处理无环无负权的有向图，因此要处理更一般的图，就需要用到Bellman-Ford算法。
```java

```