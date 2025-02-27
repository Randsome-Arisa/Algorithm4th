/*
 *                  随机背包 API
 * ------------------------------------
 * RandomBag()                  创建一个空的随机背包
 * boolean isEmpty()            背包是否为空
 * int size()                   背包中元素数量
 * void add(Item item)          添加一个元素
 */
import java.util.Iterator;
import java.util.Random;
public class RandomBag<Item> implements Iterable<Item> {
    private int max = 2;
    private int N;
    private Item[] bag;
    public RandomBag() {
        bag = (Item[]) new Object[max];
    }
    public boolean isEmpty() {
        return N == 0;
    }
    public int size() {
        return N;
    }
    public void add(Item item) {
        if(N >= max) {
            max <<= 1;
            Item[] newbag = (Item[]) new Object[max];
            for(int i = 0; i < N; i++)
                newbag[i] = bag[i];
            bag = newbag;
        }
        bag[N++] = item;
    }
    public Iterator<Item> iterator() {
        Random dice = new Random();
        Item temp;  //用于交换
        int k;
        for(int i = 0; i < N; i++) {
            k = dice.nextInt(N);
            temp = bag[i];
            bag[i] = bag[k];
            bag[k] = temp;
        }
        return new BagIterator();
    }
    private class BagIterator implements Iterator<Item> {
        private int j;
        public boolean hasNext() {
            return j < N;
        }
        public void remove() {}
        public Item next() {
            Item item = bag[j++];
            return item;
        }
    }
    public static void main(String[] args) {
        RandomBag<String> bag = new RandomBag<String>();
        bag.add("Hello");
        bag.add("World");
        bag.add("!");
        for(int i = 0; i < 5; i++) {
            for(String s: bag) {
                System.out.print(s);
            }
            System.out.println();
        }
    }
}