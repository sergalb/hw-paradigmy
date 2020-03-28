package queue;

public class LinkedQueue extends AbstractQueue {
    private class Node {
        private Node next;
        private Object value;

        public Node(Node next, Object value) {
            this.next = next;
            this.value = value;
        }
    }

    private Node head;
    private Node tail;

    public LinkedQueue () {
        head = new Node(null, null);
        tail = new Node(null, null);
    }

    @Override
    public void enqueueImpl(Object x) {
        if (isEmpty()) {
            tail = new Node(null, x);
            head = new Node(tail, null);
            System.out.println();
        } else {
            tail.next = new Node(null, x);
            tail = tail.next;
        }
    }

    public Object elementImpl() {
        return head.next.value;
    }


    public Object dequeueImpl() {
        Object tmp = element();
        head = head.next;
        return tmp;
    }

    public void clearImpl() {
        tail = new Node(null, null);
        head = new Node(null, null);
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node node = head.next;
        for (int i = 0; i < size; i++) {
            array[i] = node.value;
            node = node.next;
        }
        return array;
    }
}
