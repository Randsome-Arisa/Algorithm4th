//二分查找（基于有序数组）
//用两个数组分别储存键值
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;
    @SuppressWarnings("unchecked")  //用这个注解关闭对泛型数组转型的警告
    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Comparable[capacity];
    }
    public Value get(Key key) {
        if(isEmpty())
            return null;
        int i = rank(key);
        //i<N只是用于确保后面的keys[i]不越界
        if(i < N && keys[i].compareTo(key) == 0)
            return vals[i];
        else
            return null;
    }
    public void put(Key key, Value val) {
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        //比i大的向后挪一格
        for(int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;  vals[i] = val;
        N++;
    }
    public void delete(Key key) {
        int i = rank(key);
        //命中
        if(i < N && keys[i].compareTo(key) == 0) {
            for(int j = i; j < N-1; j++) {
                keys[j] = keys[j+1];
                vals[j] = vals[j+1];
            }
            N--;
        }
    }
    public boolean contains(Key key) {
        return get(key) != null;
    }
    public boolean isEmpty() {
        return N == 0;
    }
    public Key min() {
        return keys[0];
    }
    public Key max() {
        return keys[N-1];
    }
    public void deleteMin() {
        delete(select(0));
    }
    public void deleteMax() {
        delete(select(N-1));
    }
    public Key floor(Key key) {
        int i = rank(key);
        if(i >= N)
            return keys[N-1];
        else if(i > 0)
            return keys[i];
        else if(keys[i].equals(key))
            return keys[i];
        else
            return null;
    }
    public Key ceiling(Key key) {
        int i = rank(key);
        if(i < N)
            return keys[i];
        else
            return null;
    }
    public int rank(Key key) {
        ///无论lo,hi和表中其他键的相对大小，rank()总能给出正确的数量
        int lo = 0, hi = N-1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0)
                hi = mid - 1;
            else if(cmp > 0)
                lo = mid + 1;
            else
                return mid;
        }
        //当key比表中所有键都大时，lo == N。反之lo == 0
        //这保证了key不在表中时的情况不会出现错误
        return lo;
    }
    public Key select(int k) {
        return keys[k];
    }
    public int size() {return N;}
    public int size(Key lo, Key hi) {
        //lo > hi
        if(lo.compareTo(hi) > 0)
            return 0;
        //表中有和hi一样大的键
        else if(contains(hi))
            return rank(hi) - rank(lo) + 1;
        //表中没有和hi一样大的键
        else
            return rank(hi) - rank(lo);
    }
}