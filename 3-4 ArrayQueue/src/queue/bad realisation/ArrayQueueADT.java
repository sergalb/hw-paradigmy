package queue;

public class ArrayQueueADT {
    private Object[] elements = new Object[16];
    private int size;
    private int tail;
    private int head;
    //n = size queue, a = elements of queue
    // inv n >= 0 && a[i] != null for i = 1 .. n

    public ArrayQueueADT() {
        elements = new Object[16];
        size = 0;
        tail = -1;
        head = 0;
    }

    public ArrayQueueADT(int capacity) {
        elements = new Object[capacity];
        size = 0;
        tail = -1;
        head = 0;
    }

    // pre x != null
    // post n' = n + 1, a[n'] = x
    // a'[i] = a[i] for i = 1 .. n
    public static void enqueue(ArrayQueueADT queue, Object x) {
        ensureCapacity(queue, queue.size + 1);
        queue.tail++;
        queue.tail %= queue.elements.length;
        //System.out.println(tail);
        queue.elements[queue.tail] = x;
        queue.size++;
    }

    // pre n > 0

    // post R = a[1], n' = n,
    public static Object element(ArrayQueueADT queue) {
        return queue.elements[queue.head];
    }
    // a'[i] = a[i] for i = 1 .. n
    // pre n > 0

    // post R = a[1], n' = n - 1,
    public static Object dequeue(ArrayQueueADT queue) {
        Object tmp = queue.elements[queue.head];
        queue.head++;
        queue.head %= queue.elements.length;
        queue.size--;
        return tmp;
    }
    // a'[i] = a[i] for i = 1 .. n - 1
    // post R = n, n' = n,

    // a'[i] = a[i] for i = 1 .. n
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }
    // post R = (size == 0), n' = n,

    // a'[i] = a[i] for i = 1 .. n
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }
    // post n = 0

    public static void clear(ArrayQueueADT queue) {
        queue.size = 0;
        queue.head = 0;
        queue.tail = -1;
        queue.elements = new Object[queue.elements.length];
    }

    // post: R = a
    public static Object[] toArray(ArrayQueueADT queueADT) {
        return getElements(queueADT, queueADT.size);
    }

    private static Object[] getElements(ArrayQueueADT queueADT, int lengthResult) {
        Object[] newElements = new Object[lengthResult];
        for (int i = 0; i < queueADT.size; i++) {
            newElements[i] = queueADT.elements[(queueADT.head + i) % queueADT.elements.length];
        }
        return newElements;
    }

    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity <= queue.elements.length) {
            return;
        }
        Object[] newElements = getElements(queue, capacity * 2);
        queue.head = 0;
        queue.tail = queue.size - 1;
        queue.elements = newElements;
    }
}
    