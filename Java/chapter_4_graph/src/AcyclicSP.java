// Acyclic shortest path 无环最短路径
public class AcyclicSP {
    private DirectedEdge[] edgeTo;  // edgeTo[w] == v表示路径是v->w
    private double[] distTo;         // disTo[v] == x表示到x的路径权值为x

    public AcyclicSP(Digraph g, int s) {
        distTo = new double[g.getvNum()];
        edgeTo = new DirectedEdge[g.getvNum()];

        for (int v = 0; v < g.getvNum(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // 按照拓扑顺序relax
        Topological topological = new Topological(g);
        for (int v : topological.getTopoOrder()) {
            for (DirectedEdge e : g.adj(v))
                relax(e);
        }
    }

    // 若源点s -> w的距离小于s -> v -> w，则更新s到w的距离和路径。这个过程叫做relax。
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
}
