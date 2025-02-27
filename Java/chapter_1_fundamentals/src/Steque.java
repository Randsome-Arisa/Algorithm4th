/****************************************************
 * 以栈为目标的队列steque。
 *                        API
 * -----------------------------------------------
 * boolean isEmpty()            判空
 * int size()                   steque中元素的数量
 * void push(Item item)         从队首入队（插队）
 * void enqueue(Item item)      从队尾入队
 * Item pop()                   排在队首的出队
 * -----------------------------------------------
 ****************************************************/
public class Steque<Item> {
    private class Node {
        Item item;
        Node next;
    }
    private Node head;
    private Node tail;
    private int N;
    public boolean isEmpty() {
        return N == 0;
    }
    public int size() {
        return N;
    }
    public void push(Item item) {
        Node newhead = new Node();
        newhead.item = item;
        newhead.next = head;
        head = newhead;
        N++;
    }
    public void enqueue(Item item) {
        Node newtail = new Node();
        newtail.item = item;
        tail.next = newtail;
        tail = newtail;
        N++;
    }
    public Item pop() {
        Item item = head.item;
        head = head.next;
        N--;
        return item;
    }
}