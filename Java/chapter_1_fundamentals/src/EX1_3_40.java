/*
实现前移编码策略：
输入一个字符串，使用链表保存这些字符
遇到没见过的字符时，插入到表头
遇到已有的字符时，将其删除，并将新的插入表头
 */

import java.util.Scanner;

class MoveToFront<Item> {
    private class Node {
        private Item item;
        private Node next;
        public Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
    private Node head;
    public boolean exist(Item item) {
        Node cur = head;
        while (cur != null) {
            if (cur.item == item)
                return true;
            cur = cur.next;
        }
        return false;
    }
    public void insertAtHead(Item item) {
        Node newhead = new Node(item, head);
    }
    public void delete(Item item) {
        Node cur = head;
        while (cur != null) {
            if (cur.next.item == item) {
                cur.next = cur.next.next;
                return;
            }
        }
    }
}
public class EX1_3_40<Item> {
    public static void main(String[] args) {
        MoveToFront mtf = new MoveToFront();
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!mtf.exist(c))
                mtf.insertAtHead(c);
            else {
                mtf.insertAtHead(c);
                mtf.delete(c);
            }
        }
        scan.close();
    }
}
