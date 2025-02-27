// DFS遍历的3种顶点排列，用于之后的其他高级算法
public class DFS_Order {
    private boolean[] visited;
    private Queue<Integer> pre;         // 前序排列
    private Queue<Integer> post;        // 后序排列
    private Stack<Integer> reversePost; // 逆后序排列

    private void dfs(Digraph g, int v) {
        pre.enqueue(v);

        visited[v] = true;
        for (int i : g.adj(v)) {
            if (!visited[i]) {
                dfs(g, i);
            }
        }

        post.enqueue(v);
        reversePost.push(v);
    }

    public DFS_Order(Digraph g) {
        pre = new Queue<Integer>();
        post = new Queue<Integer>();
        reversePost = new Stack<Integer>();
        visited = new boolean[g.getvNum()];

        for (int i = 0; i < g.getvNum(); i++) {
            if (!visited[i])
                dfs(g, i);
        }
    }

    public Iterable<Integer> pre() {
        return pre;
    }
    public Iterable<Integer> post() {
        return post;
    }
    public Iterable<Integer> reversePost() {
        return reversePost;
    }

}
