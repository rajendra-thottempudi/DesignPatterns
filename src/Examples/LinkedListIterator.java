package Examples;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

class ListNode {
    int val;
    ListNode prev;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode: " + val;
    }
}

class LinkedList implements Iterable<ListNode> {
    private ListNode head;
    private ListNode tail;

    public LinkedList() {
        buildList();
    }

    private void buildList() {
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = tail;
        tail.prev = head;
    }

    public void add(int val) {
        ListNode newNode = new ListNode(val);
        newNode.prev = tail.prev;
        newNode.next = tail;
        tail.prev.next = newNode;
        tail.prev = newNode;
    }

    @Override
    public Iterator<ListNode> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<ListNode> {
        private ListNode current = head.next;

        @Override
        public boolean hasNext() {
            return current != null && current.next != null;
        }

        @Override
        public ListNode next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            ListNode returnNode = current;
            current = current.next;
            return returnNode;
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(10));
        }

        for (ListNode node : list) {
            System.out.println(node);
        }
    }
}

