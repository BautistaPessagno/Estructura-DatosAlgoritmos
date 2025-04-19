import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestEvaluator {

    @Test
    public void test1() {
// inyecto entrada estandard
        String input= "( 2 + -0.1 ) / ( 10 * 2 )";
        InputStream inputstream= new ByteArrayInputStream(input.getBytes());
        System.setIn(inputstream);
        Double rta = new Evaluator().evaluate();
        assertEquals(0.095, rta);
        System.setIn(System.in);
    }

    @Test
    public void test2() {
        String input =  "( ( -9 - -1 ) / ( 10 * 2 ) ) * ( ( 1 - 5 ) / ( 2 / -3 ) )";
        InputStream inputstream= new ByteArrayInputStream(input.getBytes());
        System.setIn(inputstream);
        Double rta = new Evaluator().evaluate();
        assertEquals(Math.round(-2.4), Math.round(rta));
        System.setIn(System.in);
    }

    @Test
    public void testGetPrecedence() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Evaluator evaluator = new Evaluator();
        Method myGetPrecedence = Evaluator.class.getDeclaredMethod("getPrecedence", String.class, String.class );
        myGetPrecedence.setAccessible(true);

        boolean result = (boolean) myGetPrecedence.invoke(evaluator, "+", "-");
        assertEquals(true, result);

        result = (boolean) myGetPrecedence.invoke(evaluator, "+", "*");
        assertEquals(false, result);

        result = (boolean) myGetPrecedence.invoke(evaluator, "*", "+");
        assertEquals(true, result);

        result = (boolean) myGetPrecedence.invoke(evaluator, "*", "/");
        assertEquals(true, result);



    }
}
