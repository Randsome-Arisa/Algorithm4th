//顺序查找（基于无序链表）
public class SequentialSearchST<Key, Value> {
    private class Node {
        Key key;
        Value val;
        Node next;
        public Node(final Key key, final Value val, final Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    private Node first;
    private int N;

    public Value get(final Key key) {
        for (Node x = first; x != null; x = x.next)
            // 命中
            if (key.equals(x.key))
                return x.val;
        // 未命中
        return null;
    }

    public void put(final Key key, final Value val) {
        if (val == null) {
            delete(key);
            return;
        }
        for (Node x = first; x != null; x = x.next)
            // 命中，更新
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        // 未命中，新建结点
        first = new Node(key, val, first);
        N++;
    }

    public void delete(final Key key) {
        /*
         * 延时删除 put(key, null);
         */
        // 即时删除
        Node pre = first;
        // 在头结点命中
        if (pre.key.equals(key)) {
            first = pre.next;
            pre.next = null;
            N--;
            return;
        }
        // 在中间命中或未命中
        Node x = pre.next;
        while (x != null && !x.key.equals(key)) {
            pre = pre.next;
            x = x.next;
        }
        // 未命中
        if (x == null)
            return;
        // 在中间命中
        else {
            pre.next = x.next;
            x.next = null;
            N--;
        }
    }

    public boolean contains(final Key key) {
        return get(key) != null;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public static void main(final String[] args) {
        final SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        st.put("A", 1);
        st.put("B", 2);
        final int a = st.get("A");
        System.out.println(a);
    }
}