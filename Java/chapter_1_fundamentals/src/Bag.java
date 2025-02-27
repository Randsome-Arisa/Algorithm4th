/* 背包不支持删除
 *                   背包 API
 * ----------------------------------------------------
 * Bag()                        创建一个空背包
 * boolean isEmpty()            判空
 * int size()                   元素数量
 * void add()                   添加一个元素
 * ----------------------------------------------------
 */
import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
    }
    private Node first;
    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
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