// Kosaraju算法求强连通分量
public class Kosaraju {
    private boolean[] visited;
    private int[] id;   // id[i] == x 表示i号顶点的id是x，id相同的顶点属于一个强连通分量
    private int count;  // 用于划分id，亦即强连通分量的数量

    private void dfs(Digraph g, int v) {
        visited[v] = true;
        id[v] = count;
        for (int w : g.adj(v)) {
            if (!visited[w]) dfs(g, w);
        }
    }

    public Kosaraju(Digraph g) {
        visited = new boolean[g.getvNum()];
        id = new int[g.getvNum()];

        // 先获得原图的逆后序排列
        DFS_Order dfs_order = new DFS_Order(g.reverse());
        // 然后再对其逆图进行DFS，从一个顶点开始能搜索到的最大区块就是该点所在的强连通分量
        for (int v : dfs_order.reversePost()) {
            if (!visited[v]) {
                dfs(g, v);
                count++;    // 一个强连通区域完毕
            }
        }
    }

    // 判断v和w是否是强连通的
    public boolean strongly(int v, int w) {
        return id[v] == id[w];
    }
}
