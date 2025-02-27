/**********
 * 双向链表
 *********/
public class DoubleNode<Item> {
    private class Node {
        Item item;
        Node pre;
        Node next;
    }
    private Node first;
    private Node last;
    private int N;
    public boolean isEmpty() {
        return N == 0;
    }
    public int size() {
        return N;
    }
    public void insertAtFirst(Item item) {
        //插入到头结点
        Node newfirst = new Node();
        newfirst.item = item;
        newfirst.pre = null;
        newfirst.next = first;
        first.pre = newfirst;
        first = newfirst;
        N++;
    }
    public void insertAtLast(Item item) {
        //插入到尾结点
        Node newlast = new Node();
        newlast.item = item;
        newlast.pre = last;
        newlast.next = null;
        last.next = newlast;
        last = newlast;
        N++;
    }
    public void insertBefore(int k, Item item) {
        //插入到第k个结点之前
        if(k == 1)
            insertAtFirst(item);
        else {
            Node current = first;
            for(int i = 1; i < k; i++)
                current = current.next;
            Node newone = new Node();
            newone.item = item;
            newone.pre = current.pre;
            newone = newone.pre.next;
            newone.next = current;
            newone = current.pre;
            N++;
        }
    }
    public void insertAfter(int k, Item item) {
        //插入到第k个结点之后
        if(k == N)
            insertAtLast(item);
        else {
            Node current = first;
            for(int i = 1; i < k; i++)
                current = current.next;
            Node newone = new Node();
            newone.item = item;
            newone.next = current.next;
            newone = newone.next.pre;
            newone.pre = current;
            current.next = newone;
            N++;
        }
    }
    public void deleteFirst() {
        first = first.next;
        first.pre = null;
        N--;
    }
    public void deleteLast() {
        last = last.pre;
        last.next = null;
        N--;
    }
    public void delete(int k) {
        //删除第k个结点
        if(k == 1)
            deleteFirst();
        else if(k == N)
            deleteLast();
        else {
            Node current = first;
            for(int i = 1; i < k; i++)
                current = current.next;
            current.next.pre = current.pre;
            current.pre.next = current.next;
            current.pre = null;
            current.next = null;
            N--;
        }
    }
    public boolean find(Item item) {
        //item在链表中则返回true
        Node current = first;
        while(current != null) {
            if(current.item == item)
                return true;
            current = current.next;
        }
        return false;
    }
    public void remove(Item item) {
        //删除所有值是item的结点
        Node current = first;
        while(current != null) {
            if(current.item == item) {
                if(current == first) {
                    deleteFirst();
                    current = first;
                }
                else if(current == last) {
                    deleteLast();
                    break;
                }
                else {
                    current.next.pre = current.pre;
                    current.pre = null;
                    current = current.next;
                    current.pre.next.next = null;
                    current.pre.next = current;
                    N--;
                }
            }
            else
                current = current.next;
        }
    }
    public Item getFirst() {
        Item item = first.item;
        return item;
    }
    public Item getLast() {
        Item item = last.item;
        return item;
    }
}