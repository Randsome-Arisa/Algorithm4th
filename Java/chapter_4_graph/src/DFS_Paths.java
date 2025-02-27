public class DFS_Paths {
    private boolean[] visited;
    private int[] edgeTo;   // 从起点到i号顶点的上一个顶点是edgeTo[i]
    private final int s;    // 起点

    public DFS_Paths(Graph g, int s) {
        this.s = s;
        visited = new boolean[g.getvNum()];
        edgeTo = new int[g.getvNum()];
        dfs(g, s);
    }

    // 遍历所有可以访问的顶点
    public void dfs(Graph g, int v) {
        visited[v] = true;
        for (int i : g.adj(v)) {
            if (!visited[i]) {
                edgeTo[i] = v;
                dfs(g, i);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return visited[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<Integer>();
        // 从终点回溯回去
        for (int i = v; i != s; i = edgeTo[i])
            path.push(i);
        path.push(s);
        return path;
    }
}
