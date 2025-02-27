import javax.sound.sampled.Line;

//基于线性探测的符号表
//碰撞发生时直接检测散列表下一个位置
public class LinearProbingHashST<Key, Value> {
    private int N;  //键值对总数
    private int M = 16; //
    private Key[] keys;
    private Value[] vals;
    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }
    public LinearProbingHashST(int cap) {
        M = cap;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) & M;
    }
    private void resize() {

    }
    public void put(Key key, Value val) {
        if(N >= M/2)
            resize(2*M);
        int i;
        for(i = hash(key); keys[i] != null; i = (i + 1) & M)
            if(keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        keys[i] = key;
        vals[i] = val;
        N++;
    }
    public Value get(Key key) {
        for(int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if(keys[i].equals(key))
                return vals[i];
        return null;
    }
    public boolean contains(Key key) {
        for(int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if(keys[i].equals(key))
                return true;
        return false;
    }
    public void delete(Key key) {
        if(!contains(key))
            return;
        int i = hash(key);
        while(!key.equals(keys[i]))
            i = (i + 1) % M;
        keys[i] = null;
        vals[i] = null;
        //只是将删除的键值设置为null是不够的，因为这会使其之后的元素无法被查找
        //将被删除键值对的右侧重新插入散列表
        i = (i + 1) % M;
        while(keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }
        N--;
        if(N > 0 && N == M/8)
            resize(M/2);
    }
    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<Key, Value>(cap);
        for(int i = 0; i < M; i++)
            if(keys[i] != null)
                t.put(keys[i], vals[i]);
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }
}
