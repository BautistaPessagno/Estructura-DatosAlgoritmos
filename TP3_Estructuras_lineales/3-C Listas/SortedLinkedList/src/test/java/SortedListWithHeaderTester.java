import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SortedListWithHeaderTester {
    private SortedListWithHeader<Integer> list;

    @BeforeAll
    static void start() {
        System.out.println("Inicio de pruebas: ");
    }

    @BeforeEach
    void setUp(){
        this.list = new SortedListWithHeader<>();
    }


    @Test
    public void tetsInsert() {
        try {
            list.insert(null);
            assertNotEquals(0, list.size());
        }catch (IllegalArgumentException e){
            // exception lanzada
            // si la agarra esta perfecto
        }
        boolean ans = list.insert(1);
        assertEquals(true, ans);
        try {
            list.insert(1);
            assertNotEquals(2, list.size());
        }catch (IllegalArgumentException e){
            // exception lanzada
            // si la agarra esta perfecto
        }
        assertEquals(true, list.insert(10));
        assertEquals(true, list.insert(5));
    }
    @Test
    public void testInsertRec() {
        try {
            list.insert2(null);
            assertNotEquals(0, list.size());
        }catch (IllegalArgumentException e){
            // exception lanzada
            // si la agarra esta perfecto
        }
        boolean ans = list.insert2(1);
        assertEquals(true, ans);
        try {
            list.insert2(1);
            assertNotEquals(2, list.size());
        }catch (IllegalArgumentException e){
            // exception lanzada
            // si la agarra esta perfecto
        }
        assertEquals(true, list.insert2(10));
        assertEquals(true, list.insert2(5));
    }

    @Test
    public void testInsert3() {
        try {
            list.insert3(null);
            assertNotEquals(0, list.size());
        }catch (IllegalArgumentException e){
            // exception lanzada
            // si la agarra esta perfecto
        }
        boolean ans = list.insert3(1);
        assertEquals(true, ans);
        try {
            list.insert3(1);
            assertNotEquals(2, list.size());
        }catch (IllegalArgumentException e){
            // exception lanzada
            // si la agarra esta perfecto
        }
        assertEquals(true, list.insert3(10));
        assertEquals(true, list.insert3(5));
    }


    @Test
    public void testFind() {
        assertEquals(false, list.find(1));
        assertEquals(true, list.insert(1));
        assertEquals(true, list.insert(10));
        assertEquals(true, list.insert(5));
        assertEquals(true, list.find(1));
        assertEquals(true, list.find(10));
        assertEquals(true, list.find(5));
    }


    @Test
    public void testRemove() {
        assertEquals(false, list.remove(1));
        list.insert(1);
        list.insert(10);
        list.insert(5);
        assertEquals(true, list.remove(1));
        assertEquals(false, list.find(1));
    }
    @Test
    public void testRemove2() {
        assertEquals(false, list.remove2(1));
        list.insert(1);
        list.insert(10);
        list.insert(5);
        assertEquals(true, list.remove2(1));
        assertEquals(false, list.find(1));
    }

    @Test
    public void testRemove3() {
        assertEquals(false, list.remove3(1));
        list.insert(1);
        list.insert(10);
        list.insert(5);
        assertEquals(true, list.remove3(1));
        assertEquals(false, list.find(1));
    }

    @Test
    public void testIsEmpty() {
        assertEquals(true, list.isEmpty());
        list.insert(1);
        assertEquals(false, list.isEmpty());
        list.remove(1);
        assertEquals(true, list.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, list.size());
        list.insert(1);
        assertEquals(1, list.size());
        list.insert(10);
        assertEquals(2, list.size());
        list.insert(5);
        assertEquals(3, list.size());
        list.remove(1);
        assertEquals(2, list.size());
    }

    @Test
    public void testGetMin() {
        assertEquals(null, list.getMin());
        list.insert(1);
        assertEquals(1, list.getMin());
        list.insert(10);
        assertEquals(1, list.getMin());
        list.insert(5);
        assertEquals(1, list.getMin());
        list.remove(1);
        assertEquals(5, list.getMin());
    }

    @Test
    public void testGetMax() {
        assertEquals(null, list.getMax());
        list.insert(1);
        assertEquals(1, list.getMax());
        list.insert(10);
        assertEquals(10, list.getMax());
        list.insert(5);
        assertEquals(10, list.getMax());
        list.remove(10);
        assertEquals(5, list.getMax());
        list.insert(20);
        assertEquals(20, list.getMax());
    }
}
