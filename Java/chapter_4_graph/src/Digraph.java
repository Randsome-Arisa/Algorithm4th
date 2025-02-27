// digraph：有向图
// 加权有向图
public class Digraph {
    private final int vNum;
    private int eNum;
    private Bag<DirectedEdge>[] adj;   // adjacent

    public Digraph(int vNum) {
        this.vNum = vNum;   this.eNum = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[vNum];
        for (int v = 0; v < vNum; v++)
            adj[v] = new Bag<DirectedEdge>();
    }
    public int getvNum() {
        return vNum;
    }
    public int geteNum() {
        return eNum;
    }
    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        eNum++;
    }
    public void addEdge(int v, int w, double weight) {
        adj[v].add(new DirectedEdge(v, w, weight));
        eNum++;
    }
    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
        for (int v = 0; v < vNum; v++) {
            for (DirectedEdge e : adj[v]) {
                bag.add(e);
            }
        }
        return bag;
    }

    // 将所有边的方向变为反向
    public Digraph reverse() {
        Digraph reverseDigraph = new Digraph(vNum);
        for (int v = 0; v < vNum; v++) {
            for (DirectedEdge e : adj(v)) {
                DirectedEdge temp = new DirectedEdge(e.to(), e.from(), e.weight());
                reverseDigraph.addEdge(temp);
            }
        }
        return reverseDigraph;
    }
}
