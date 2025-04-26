
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexWithDuplicatesTest {
    IndexWithDuplicates index1;
    IndexWithDuplicates index2;

    @BeforeAll
    static void setUp() {
        IndexWithDuplicates index1 = new IndexWithDuplicates();
        IndexWithDuplicates index2 = new IndexWithDuplicates();

    }

    @Test
    void testCase1() {
        index1 = new IndexWithDuplicates();
        index1.initialize(new int[] {1, 3, 5, 7});
        index2 = new IndexWithDuplicates();
        index2.initialize(new int[] {2, 4, 6, 8});
        index1.merge(index2);
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        assertArrayEquals(index1.getIndexedData(), array);
    }

    @Test
    void testCase2() {
        index1 = new IndexWithDuplicates();
        index1.initialize(new int[] {1, 1, 3, 5, 7});
        index2 = new IndexWithDuplicates();
        index2.initialize(new int[] {2, 4, 4, 6, 8});
        index1.merge(index2);
        // Resultado esperado: [1, 1, 2, 3, 4, 4, 5, 6, 7, 8]
        int[] array = {1, 1, 2, 3, 4, 4, 5, 6, 7, 8};
        assertArrayEquals(index1.getIndexedData(),array );
    }

    @Test
    void testCase3() {
        index1 = new IndexWithDuplicates();
        index1.initialize(new int[] {1, 3, 5});
        index2 = new IndexWithDuplicates();
        index2.initialize(new int[] {2, 4, 6, 8, 10});
        index1.merge(index2);
        int[] array = {1, 2, 3, 4, 5, 6, 8, 10};
        // Resultado esperado: [1, 2, 3, 4, 5, 6, 8, 10]
        assertArrayEquals(index1.getIndexedData(), array);
    }

}