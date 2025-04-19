import java.lang.reflect.Array;

public class BoundedQueue<T>{
    private T[] queue;
    private int usedSize;
    private int last;
    private int first;
    private int size;

    public BoundedQueue(int size) {
        this.size = size;
        this.last = 0;
        this.usedSize = 0;
        this.first = 0;
        this.queue = (T[]) new Object[size];
        this.queue = (T[]) Array.newInstance(Object.class, size);
    }

    public void manageSpace(){

    }

    public void enqueue(T element) {
        if (usedSize == size) {
            throw new RuntimeException("Queue is full");
        }
        else if (last == size) {
           manageSpace();
        }
        queue[last++] = element;
    }

    public T dequeue() {
        if(first == last) {
            //si el first es igual al last, significa que no hay mas elementos
            //mando el first al primer espacio
            int ans = first;
            first = 0;
            return queue[ans];
        }
        return queue[first++];
    }

    public void dump() {
        for(int i = first; i < last && queue[i] != null; i++) {
            System.out.println(queue[i]);
        }
    }
}
