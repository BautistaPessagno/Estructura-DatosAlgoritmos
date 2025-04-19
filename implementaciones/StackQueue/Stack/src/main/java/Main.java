import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
      Evaluator evaluator = new Evaluator();
      //System.out.println("Resultado expresion = %.2f".formatted(Evaluator.evaluate()));
        EvaluatorWithVariables x = new EvaluatorWithVariables(
                new HashMap<String, Double>()
                { { put("v1", 12.3);
                    put("v3", 5.0);
                } } );
        System.out.println(x.evaluate());
        System.out.println(x.evaluate());
        System.out.println(x.evaluate());

    }
}