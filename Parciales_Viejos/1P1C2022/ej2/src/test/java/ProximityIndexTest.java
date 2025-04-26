import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProximityIndexTest {
    public static ProximityIndex myIndex;

    @BeforeAll
    static void testInitialize() {
        myIndex = new ProximityIndex();
        String[] elements = {"Ana", "Carlos", "Juan", "Yolanda"};
        myIndex.initialize(elements);
    }

    @Test
    void testSearch() {
        String result = myIndex.search("Carlos", 0);
        assertEquals("Carlos", result);

        result = myIndex.search("Carlos", 3);
        assertEquals("Ana", result);

        result = myIndex.search("Ana", 14);
        assertEquals("Juan", result);

        result = myIndex.search("Ana", -2);
        assertEquals("Juan", result);

        result = myIndex.search("Ana", -17);
        assertEquals("Yolanda", result);

        result = myIndex.search("Juan", -4);
        assertEquals("Juan", result);

        result = myIndex.search("XXXX", -4);
        assertEquals(null, result);

    }
}
