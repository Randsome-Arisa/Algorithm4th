import java.util.Queue;
public class BST<Key extends Comparable<Key>, Value> {
    private class Node {
        private Key key;    //键
        private Value val;    //值
        private Node left, right;   //左右子树
        private int N;  //以该结点为根的子树中结点总数
        public Node(Key key, Value val, int N) {
            this.key = key; this.val = val; this.N = N;
        }
    }
    private Node root;
    //整棵树的结点数
    public int size() {
        return size(root);
    }
    //以任意结点为根的子树的结点数
    private int size(Node x) {
        if(x == null)
            return 0;
        else
            return x.N;
    }
    //查找
    public Value get(Key key) {
        return get(root, key);
    }
    //因为用递归的方法实现，所以要写私有方法，下面同理
    private Value get(Node x, Key key) {
        if(x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            return get(x.left, key);
        else if(cmp > 0)
            return get(x.right, key);
        else
            return x.val;
    }
    //用递归的方法插入
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }
    private Node put(Node x, Key key, Value val) {
        //如果存在key为键的结点则更新它，否则插入新的结点
        if(x == null)
        //树为空时
            return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            x.left = put(x.left, key, val);
        else if(cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;
        //无论是更新还是插入结点，该结点的N总等于其左右子树结点数+1
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    public Key min() {
        return min(root).key;
    }
    private Node min(Node x) {
        //如果根结点的左子树为空，那么它就是最小结点，否则就是它左子树的最小键
        if(x.left == null)
            return x;
        return min(x.left);
    }
    public Key max() {
        return max(root).key;
    }
    private Node max(Node x) {
        if(x.right == null)
            return x;
        return max(x.right);
    }
    public Key floor(Key key) {
        Node x = floor(root, key);
        if(x == null)
            return null;
        return x.key;
    }
    private Node floor(Node x, Key key) {
        if(x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0)
            return x;
        //如果key小于根结点，那么小于等于key的最大键一定在根结点的左子树中
        if(cmp < 0)
            return floor(x.left, key);
        //如果key大于根结点，那么小于等于key的最大键可能在右子树中，也可能就是根结点
        Node t = floor(x.right, key);
        if(t != null)
            return t;
        else
            return x;
    }
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if(x == null)
            return null;
        return x.key;
    }
    private Node ceiling(Node x, Key key) {
        if(x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0)
            return x;
        if(cmp > 0)
            return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if(t != null)
            return t;
        else
            return x;
    }
    public Key select(int k) {
        return select(root, k).key;
    }
    private Node select(Node x, int k) {
        if(x == null)
            return null;
        int t = size(x.left);
        //如果左子树的结点数大于k，就递归地在左子树中查找
        if(t > k)
            return select(x.left, k);
        //如果小于k，就在右子树中查找排名为k-t-1的键
        else if(t < k)
            return select(x.right, k-t-1);
        else
            return x;
    }
    public int rank(Key key) {
        return rank(key, root);
    }
    private int rank(Key key, Node x) {
        if(x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            return rank(key, x.left);
        else if(cmp > 0)
            return 1+size(x.left) + rank(key, x.right);
        else
            return size(x.left);
    }
    public void deleteMin() {
        root = deleteMin(root);
    }
    //找到最小结点，将其右子树链接到其父结点的左结点即可。
    private Node deleteMin(Node x) {
        if(x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    public void deleteMax() {
        root = deleteMax(root);
    }
    private Node deleteMax(Node x) {
        if(x.right == null)
            return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    public void delete(Key key) {
        root = delete(root, key);
    }
    private Node delete(Node x, Key key) {
        if(x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            x.left = delete(x.left, key);
        else if(cmp > 0)
            x.right = delete(x.right, key);
        else {
            if(x.right == null)
                return x.left;
            else if(x.left == null)
                return x.right;
            //对于左右结点都不为空的结点，删除后用其后继节点（其右子树中的最小结点）填补它的位置
            Node t = x;
            x = min(t.right);   //将x指向其后继结点
            //填补位置
            x.right = deleteMin(t.right);   //deleteMin(t.right)最后返回的就是t.right
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    public Iterable<Key> keys() {
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if(x == null)
            return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        //中序遍历
        if(cmplo < 0)
            keys(x.left, queue, lo, hi);
        if(cmplo <= 0 && cmphi >= 0)
            queue.add(x.key);
        if(cmphi > 0)
            keys(x.right, queue, lo, hi);
    }
}