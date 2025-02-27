import java.util.Iterator;
public class Queue<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int N;  // 元素数
    private class Node{
        Item item;
        Node next;
    }
    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        return N;
    }
    public void enqueue(Item item){
        Node oldtail = tail;
        tail = new Node();
        tail.item = item;
        tail.next = null;
        if(isEmpty())
            head = tail;
        else
            oldtail.next = tail;
        N++;
    }
    public Item dequeue(){
        Item item = head.item;
        head = head.next;
        if(isEmpty())
            tail = null;
        N--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item> {
        private Node current = head;
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {}
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}