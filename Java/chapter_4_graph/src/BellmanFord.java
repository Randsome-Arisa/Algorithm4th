public class BellmanFord {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private boolean[] onQueue;             // onQueue[v] == true表示顶点v在queue中
    private Queue<Integer> queue;          // 待relax的顶点队列
    private int cost;                      // number of calls to relax()
    private Iterable<DirectedEdge> cycle;  // 负权重环（若无则为null）

    // s为源点
    public BellmanFord(Digraph g, int s) {
        distTo  = new double[g.getvNum()];
        edgeTo  = new DirectedEdge[g.getvNum()];
        onQueue = new boolean[g.getvNum()];
        for (int v = 0; v < g.getvNum(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // Bellman-Ford算法
        queue = new Queue<Integer>();
        queue.enqueue(s);
        onQueue[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQueue[v] = false;
            relax(g, v);
        }
    }

    private void relax(Digraph g, int v) {
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQueue[w]) {
                    queue.enqueue(w);
                    onQueue[w] = true;
                }
            }
            /*
            if (++cost % g.getvNum() == 0) {
                findNegativeCycle();
                if (hasNegativeCycle()) return;  // 发现负权重环，停止
            }
            */
        }
    }

    /*private void findNegativeCycle() {
        int V = edgeTo.length;
        Digraph spt = new Digraph(V);
        for (int v = 0; v < V; v++)
            if (edgeTo[v] != null)
                spt.addEdge(edgeTo[v]);

        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
        cycle = finder.cycle();
    }*/

    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> getNegativeCycle() {
        return cycle;
    }
}
