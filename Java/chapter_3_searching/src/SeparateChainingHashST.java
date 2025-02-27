//基于拉链发的散列表
//哈希值相同的键存储到一个链表
public class SeparateChainingHashST<Key, Value> {
    private int N;  //键值对总数
    private int M;  //散列表大小
    private SequentialSearchST<Key, Value>[] st;    //存链表对象的数组
    public SeparateChainingHashST() {
        this(997);  //非素数数组会散列不均匀
    }
    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for(int i = 0; i < M; i++)
            st[i] = new SequentialSearchST();
    }
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;   //将符号位屏蔽（32位整数变为一个31位负整数）
    }
    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }
    public void put(Key key, Value val) {
        st[hash(key)].put(key, val);
    }
    /*public Iterable<Key> keys() {

    }*/
}
