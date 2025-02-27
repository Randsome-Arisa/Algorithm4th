/*
                                                    API
            boolean isRed(Node x)                           指向该结点的链接的颜色
            private Node rotateLeft(Node h)                 向左旋转（逆时针）
            private Node rotateRight(Node h)                向右旋转（顺时针）
            private void flipColors(Node h)                 转换一个结点的两个红色子结点的颜色
            private int size()                              此子树中结点总数
            private Node put(Node h, Key key, Value val)    插入
*/
public class RedBlackBST<Key extends Comparable<Key>, Value>{
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node {
        Key key;
        Value val;
        Node left, right;
        int N;  //此子树中结点总数
        boolean color;
        Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }
    private boolean isRed(Node x) {
        if(x == null)
            return false;
        return x.color == RED;
    }
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }
    private void flipColors(Node h) {
        //父节点由黑变红
        h.color = RED;
        //子结点由红变黑
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
    private int size(Node x) {
        if(x == null)
            return 0;
        else
            return x.N;
    }
    public void put(Key key, Value val) {
        root = put(root, key, val);
        //根结点设为黑色
        root.color = BLACK;
    }
    private Node put(Node h, Key key, Value val) {
        if(h == null)
            return new Node(key, val, 1, RED);
        int cmp = key.compareTo(h.key);
        if(cmp < 0)
            h.left = put(h.left, key, val);
        else if(cmp > 0)
            h.right = put(h.right, key, val);
        else
            h.val = val;
        //当红链接是右链接时，左旋转
        if(isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        //当左子结点和它的左子结点都是红链接时，右旋转
        if(isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        //当左右子结点都是红链接时
        if(isRed(h.left) && isRed(h.right))
            flipColors(h);
        //注意上面三个的顺序不能调换
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }
}
