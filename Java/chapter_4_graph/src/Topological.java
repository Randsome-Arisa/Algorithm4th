/*
 * DAG的拓扑排序（假设该图是DAG）
 */
public class Topological {
    private boolean visited[];
    private Iterable<Integer> topoOrder;   // 拓扑排序即顶点的逆后序排列，亦即用DFS的方法递归后将其压入栈

    public Topological(Digraph g) {
        DFS_Order dfs_order = new DFS_Order(g);
        topoOrder = dfs_order.reversePost();
    }

    public void print() {
        for (int i : topoOrder) {
            System.out.print(i + " ");
        }
    }

    public Iterable<Integer> getTopoOrder() {
        return topoOrder;
    }

    public boolean isDAG() {
        return topoOrder != null;
    }

    public static void main(String[] args) {
        // p374的图
        Digraph dag = new Digraph(13);
        dag.addEdge(0, 1, 0);  dag.addEdge(0, 5, 0);  dag.addEdge(0, 6, 0);
        dag.addEdge(2, 0, 0);  dag.addEdge(2, 3, 0);
        dag.addEdge(3, 5, 0);
        dag.addEdge(5, 4, 0);
        dag.addEdge(6, 4, 0);  dag.addEdge(6, 9, 0);
        dag.addEdge(7, 6, 0);
        dag.addEdge(8, 7, 0);
        dag.addEdge(9, 10, 0); dag.addEdge(9, 11, 0); dag.addEdge(9, 12, 0);
        dag.addEdge(11, 12, 0);

        Topological topo = new Topological(dag);
        topo.print();
    }
}
