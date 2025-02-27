/*
 * 随机队列，删除一个元素时，随机交换某个元素和末位元素，然后删除并返回末位元素。
 *                   随机队列 API
 * ----------------------------------------------------
 * RandomQueue()                创建一条空的随机队列
 * boolean isEmpty()            判空
 * void enqueue(Item item)      添加一个元素
 * Item dequeue()               删除并随机返回一个元素
 * Item sample()                随机返回一个元素但不删除
 * ----------------------------------------------------
 */
import java.util.Iterator;
import java.util.Random;
public class RandomQueue<Item> implements Iterable<Item> {
    private int max = 2;
    private int N;
    private Item[] queue;
    public RandomQueue() {
        queue = (Item[]) new Object[max];
    }
    public boolean isEmpty() {
        return N == 0;
    }
    /*public int size() {
        return N;
    }*/
    public void enqueue(Item item) {
        if(N >= max) {
            max <<= 1;
            Item[] newque = (Item[]) new Object[max];
            for(int i = 0; i < N; i++)
                newque[i] = queue[i];
            queue = newque;
        }
        queue[N++] = item;
    }
    public Item dequeue() {
        Random dice = new Random();
        //随机交换某个元素和末位元素
        Item temp;
        int k = dice.nextInt(N);
        temp = queue[k];
        queue[k] = queue[N-1];
        queue[N-1] = temp;
        N--;
        if(N <= max/4) {
            max >>= 1;
            Item[] newque = (Item[]) new Object[max];
            for(int i = 0; i < N; i++)
                newque[i] = queue[i];
            queue = newque;
        }
        return temp;    //temp储存的就是末位元素
    }
    public Item sample() {
        Random dice = new Random();
        Item temp;
        int k = dice.nextInt(N);
        temp = queue[k];
        queue[k] = queue[N-1];
        queue[N-1] = temp;
        return temp;
    }
    public Iterator<Item> iterator() {
        Random dice = new Random();
        Item temp;  //用于交换
        int k;
        for(int i = 0; i < N; i++) {
            k = dice.nextInt(N);
            temp = queue[i];
            queue[i] = queue[k];
            queue[k] = temp;
        }
        return new RandomQueueIterator();
    }
    private class RandomQueueIterator implements Iterator<Item> {
        private int j;
        public boolean hasNext() {
            return j < N;
        }
        public void remove() {}
        public Item next() {
            Item item = queue[j++];
            return item;
        }
    }
    public static void main(String[] args) {
        RandomQueue<String> card = new RandomQueue<>();
        card.enqueue("Spade A");
        card.enqueue("Heart A");
        card.enqueue("Diamond A");
        card.enqueue("Club A");
        String[] gentleman = new String[4];
        int i = 0;
        for(String s: card)
            gentleman[i++] = s;
        for(i = 0; i < 4; i++) {
            System.out.println(gentleman[i]);
        }
    }
}