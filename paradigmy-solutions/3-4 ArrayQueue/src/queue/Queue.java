package queue;

public interface Queue {
    //n = size queue, a = elements of queue
    // inv n >= 0 && a[i] != null for i = 1 .. n

    // pre x != null
    // post n' = n + 1, a[n'] = x
    // a'[i] = a[i] for i = 1 .. n
    public void enqueue(Object x);

    // pre n > 0
    // post R = a[1], n' = n,
    // a'[i] = a[i] for i = 1 .. n
    public Object element();

    // pre n > 0
    // post R = a[1], n' = n - 1,
    // a'[i] = a[i] for i = 1 .. n - 1
    public Object dequeue();

    // post R = n, n' = n,
    // a'[i] = a[i] for i = 1 .. n
    public int size();

    // post R = (size == 0), n' = n,
    // a'[i] = a[i] for i = 1 .. n
    public boolean isEmpty();

    // post n = 0
    public void clear();

    // post: R = a
    public Object[] toArray();


}