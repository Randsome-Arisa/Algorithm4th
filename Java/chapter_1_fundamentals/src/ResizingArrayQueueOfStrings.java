public class ResizingArrayQueueOfStrings<Item> {
    private Item[] queue;
    private int max = 2;
    private int N;
    private int head = 0;
    private int tail = 0;
    public ResizingArrayQueueOfStrings() {
        queue = (Item[]) new Object[max];   //创造泛型数组的方法
    }
    public boolean isEmpty() {
        return N == 0;
    }
    public int size() {
        return N;
    }
    public void resize(int border) {
        //调整数组大小
        Item[] temp = (Item[]) new Object[border];
        for(int i = head; i < head + N; i++) {
            temp[i - head] = queue[i % queue.length];
        }
        queue = temp;
    }
    public void enqueue(Item item) {
        if(N >= max) {
            max <<= 1;  //max扩大一倍
            resize(max);
        }
        queue[tail % max] = item;
        tail++;
        N++;
    }
    public Item dequeue() {
        Item item = queue[head % max];
        N--;
        head++;
        if(N < max / 4) {
            max >>= 1;  //max缩小一倍
            resize(max);
        }
        return item;
    }
    public static void main(String[] args) {
        ResizingArrayQueueOfStrings<String> q = new ResizingArrayQueueOfStrings<String>();
        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        q.enqueue("d");
        String s = q.dequeue();
        System.out.println(s);
        s = q.dequeue();
        System.out.println(s);
        s = q.dequeue();
        System.out.println(s);
        s = q.dequeue();
        System.out.println(s);
    }
}