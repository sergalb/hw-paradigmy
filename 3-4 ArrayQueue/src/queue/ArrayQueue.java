package queue;

public class ArrayQueue extends AbstractQueue {
    private Object[] elements;
    private int tail;
    private int head;

    public ArrayQueue() {
        elements = new Object[16];
        size = 0;
        tail = -1;
        head = 0;
    }

    public ArrayQueue(int capacity) {
        elements = new Object[capacity];
        size = 0;
        tail = -1;
        head = 0;
    }

    public void enqueueImpl(Object x) {
        ensureCapacity(size + 1);
        tail++;
        tail %= elements.length;
        elements[tail] = x;
    }


    public Object elementImpl() {
        return elements[head];
    }


    public Object dequeueImpl() {
        Object tmp = element();
        head++;
        head %= elements.length;
        return tmp;
    }

    public void clearImpl() {
        head = 0;
        tail = -1;
    }

    private Object[] getElements(int lengthResult) {
        Object[] newElements = new Object[lengthResult];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(head + i) % elements.length];
        }
        return newElements;
    }

    @Override
    public Object[] toArray() {
        return getElements(size);
    }

    private void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }
        Object[] newElements = getElements(2 * capacity);
        head = 0;
        tail = size - 1;
        elements = newElements;
    }
}
