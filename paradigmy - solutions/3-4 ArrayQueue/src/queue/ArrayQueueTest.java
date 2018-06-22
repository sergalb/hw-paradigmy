package queue;

public class ArrayQueueTest {
    /*public static void fillADT(ArrayQueueADT Queue) {
        for (int i = 0; i < 10; i++) {
            ArrayQueueADT.enqueue(Queue, i);
        }
    }

    public static void dumpADT(ArrayQueueADT Queue) {
        while (!ArrayQueueADT.isEmpty(Queue)) {
            System.out.println(ArrayQueueADT.size(Queue) + " " + ArrayQueueADT.element(Queue) + " " + ArrayQueueADT.dequeue(Queue));
        }
    }

    public static void fillModule() {
        for (int i = 0; i < 100; i++) {
            ArrayQueueModule.enqueue(i);
        }
    }

    public static void dumpModule() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(ArrayQueueModule.size() + " " + ArrayQueueModule.element() + " " + ArrayQueueModule.dequeue());
        }
    }*/

    public static void fill(Queue queue) {
        for (int i = 0; i < 100; i++) {
            queue.enqueue(i);
        }
    }

    public static void dump(Queue queue) {
        while (!queue.isEmpty()) {
            System.out.println(queue.size() + " " + queue.element() + " " + queue.dequeue());
        }
    }



    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        Queue lqueue = new LinkedQueue();
       // ArrayQueueADT queueADT = new ArrayQueueADT();
        fill(queue);
        Object[] arr = queue.toArray();
        dump(queue);
        //lqueue.clear();
        lqueue.enqueue("str");
        lqueue.enqueue('c');
        System.out.println(lqueue.element());
        /*fillADT(queueADT);
        dumpADT(queueADT);
        fillModule();
        dumpModule();
        ArrayQueueModule.dequeue();*/
    }
}
