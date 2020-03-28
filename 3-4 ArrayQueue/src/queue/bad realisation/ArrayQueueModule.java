package queue;

public class ArrayQueueModule {
    private static Object[] elements = new Object[16];
    private static int size = 0;
    private static int tail = -1;
    private static int head = 0;
    // n = size queue, a = elements of queue
    // inv n >= 0 && a[i] != null for i = 1 .. n

    // pre x != null
    // post n' = n + 1, a[n'] = x
    // a'[i] = a[i] for i = 1 .. n
    public static void enqueue(Object x) {
        ensureCapacity(size + 1);
        tail++;
        tail %= elements.length;
        elements[tail] = x;
        size++;
    }

    // pre n > 0

    // post R = a[1], n' = n,
    public static Object element() {
        return elements[head];
    }
    // a'[i] = a[i] for i = 1 .. n
    // pre n > 0

    // post R = a[1], n' = n - 1,
    public static Object dequeue() {
        Object tmp = elements[head];
        head++;
        head %= elements.length;
        size--;
        return tmp;
    }
    // a'[i] = a[i] for i = 1 .. n - 1
    // post R = n, n' = n,

    // a'[i] = a[i] for i = 1 .. n
    public static int size() {
        return size;
    }
    // post R = (size == 0), n' = n,

    // a'[i] = a[i] for i = 1 .. n
    public static boolean isEmpty() {
        return size == 0;
    }
    // post n = 0

    public static void clear() {
        size = 0;
        head = 0;
        tail = -1;
    }

    // post: R = a
    public static Object[] toArray() {
        return getElements(size);
    }

    private static Object[] getElements(int lengthResult) {
        Object[] newElements = new Object[lengthResult];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(head + i) % elements.length];
        }
        return newElements;
    }

    private static void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }
        Object[] newElements = getElements(2 * capacity);
        head = 0;
        tail = size - 1;
        elements = newElements;
    }
}
