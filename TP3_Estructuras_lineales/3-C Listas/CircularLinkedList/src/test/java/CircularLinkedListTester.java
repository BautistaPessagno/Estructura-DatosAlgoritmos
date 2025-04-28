import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircularLinkedListTester {

    @Test
    public void testEnque() {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        list.enqueue(70);
        list.enqueue(100);
        list.enqueue(500);
        list.enqueue(40);

        list.dump();
        System.out.printf("\n");

        assertEquals(70, list.peek());
        assertEquals(70, list.dequeue());

        assertEquals(100, list.peek());
        assertEquals(100, list.dequeue());

        assertEquals(500, list.peek());
        assertEquals(500, list.dequeue());

        assertEquals(40, list.peek());
        assertEquals(40, list.dequeue());
        list.dump();
    }
}
