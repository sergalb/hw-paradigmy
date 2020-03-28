package queue;

public abstract class AbstractQueue implements Queue{
    protected int size;

    public int size() { return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = dequeue();
            enqueue(array[i]);
        }
        return array;
    }
    protected abstract void enqueueImpl(Object x);

    public void enqueue(Object x) {
        assert (x != null) : "x must be notNull";
        enqueueImpl(x);
        size++;
    }

    protected abstract Object elementImpl();

    public Object element() {
        assert (isEmpty()) : "Queue is empty";
        return elementImpl();
    }

    protected abstract Object dequeueImpl();

    public Object dequeue() {
        assert (isEmpty()) : "Queue is empty";
        Object tmp = dequeueImpl();
        size--;
        return tmp;
    }

    protected abstract void clearImpl();

    public void clear() {
        size = 0;
        clearImpl();
    }
}
