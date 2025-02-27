/***********************************************
 * 双向队列，同时支持在两端添加或删除。
 *                    API
 * -------------------------------------------
 * boolean isEmpty()        判空
 * int size()               双向队列中元素数量
 * void pushLeft()          向左端添加一个元素
 * void pushRight()         向右端添加一个元素
 * Item popLeft()           从左端删除一个元素
 * Item popRight()          从右端删除一个元素
 * -------------------------------------------
 **********************************************/
 public class Deque<Item> {
    private DoubleNode<Item> deque;
    public boolean isEmpty() {
        return deque.isEmpty();
    }
    public int size() {
        return deque.size();
    }
    public void pushLeft(Item item) {
        deque.insertAtFirst(item);
    }
    public void pushRight(Item item) {
        deque.insertAtLast(item);
    }
    public Item popLeft() {
        return deque.getFirst();
    }
    public Item popRight() {
        return deque.getLast();
    }
}