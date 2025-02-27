/*
 * 用邻接表表示图，用一个Bag数组表示顶点数组，每个顶点的背包里都装与其相邻的顶点
 */

public class Graph {
    private final int vNum;
    private int eNum;
    private Bag<Integer>[] adj;   // adjacent
    public Graph(int vNum) {
        this.vNum = vNum;   this.eNum = 0;
        adj = (Bag<Integer>[]) new Bag[vNum];
        for (int v = 0; v < vNum; v++)
            adj[v] = new Bag<Integer>();
    }
    public int getvNum() {
        return vNum;
    }
    public int geteNum() {
        return eNum;
    }
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        eNum++;
    }
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
