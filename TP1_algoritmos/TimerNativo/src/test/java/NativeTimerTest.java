import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NativeTimerTest {
    private NativeTimer t1;
    private NativeTimer t2;

    @BeforeEach
    void initTimer(){
        t1 = new NativeTimer(0);
        t2 = new NativeTimer(0);
    }

    @Test
    @DisplayName("Testeando si el lapso de tiempo es correcto")
    public void getDurationTest() {
        long expected = 2000;
        t1.stop(2000);
        long ans = t1.getDuration();
        assertEquals(expected, ans, "El lapso de tiempo no es correcto");
    }

    @Test
    @DisplayName("Testeando si el getDay funciona")
    public void getDaysTesy(){
        int expected = 1;
        t1.stop(86400000);
        int ans = t1.getDays();
        assertEquals(expected, ans, "El día no es correcto");
        t2.stop(30);
        assertNotEquals(expected, t2.getDays());
    }

    @Test
    @DisplayName("Testeando si el getHours funciona")
    public void getHoursTest(){
        int expected = 1;
        t1.stop(3600000);
        int ans = t1.getHours();
        assertEquals(expected, ans, "La hora no es correcta");
        t2.stop(30);
        assertNotEquals(expected, t2.getHours());
    }

    @Test
    @DisplayName("Testeando si el getMinutes funciona")
    public void getMinutesTest(){
        int expected = 1;
        t1.stop(60000);
        int ans = t1.getMinutes();
        assertEquals(expected, ans, "El minuto no es correcto");
        t2.stop(30);
        assertNotEquals(expected, t2.getMinutes());
    }

    @Test
    @DisplayName("Testeando si el getSeconds funciona")
    public void getSecondsTest(){
        int expected = 1;
        t1.stop(1000);
        int ans = t1.getSeconds();
        assertEquals(expected, ans, "El segundo no es correcto");
        t2.stop(30);
        assertNotEquals(expected, t2.getSeconds());
    }





}
