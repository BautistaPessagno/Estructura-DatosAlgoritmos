import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Evaluator {
    private final static LinkedList<Double> stack = new LinkedList<>();
    private final static LinkedList<String> stack2 = new LinkedList<>();


    // ver en el metodo toPostfix la explicacion de como funciona la matriz de precedencia
    private final static Map<String, Integer> mapping  = new HashMap<String,Integer>() {
        {
            put(")", 6);
            put("(", 5);
            put("^", 4);
            put("/", 3);
            put("*", 2);
            put("-", 1);
            put("+", 0);
        }};
    private final static boolean[][] precedenceMatrix =
        {
                {true, true, false, false, false, false, true},
                {true, true, false, false, false, false, true},
                {true, true, true, true, false, false, true},
                {true, true, true ,true, false, false, true},
                {true, true, true, true, false, false, true},
                {false, false, false, false, false, false, false},
        };

    public LinkedList<Double> getStack(){
        return stack;
    }
    public LinkedList<String> getStack2(){
        return stack2;
    }

    protected boolean getPrecedence(String tope, String current) {
        Integer topIndex;
        Integer currentIndex;
        if((topIndex = mapping.get(tope)) == null)
            throw new RuntimeException("Error: operador no reconocido: " + tope);

        else if((currentIndex = mapping.get(current)) == null)
            throw new RuntimeException("Error: operador no reconocido: " + current);

        return precedenceMatrix[topIndex][currentIndex];

    }

    public double evaluate() {
        // uso el scanner para conseguir la expresion
        String postfija = toPostfix();

        System.out.println("postfija: " + postfija);

        // uso el scanner para conseguir la expresion
        Scanner lineScanner = new Scanner(postfija).useDelimiter("\\s+");

        while (lineScanner.hasNext()) {
            String token = lineScanner.next();
            if (isOperator(token)) {
                if (stack.isEmpty()) {
                    throw new RuntimeException("operando with empty stack");
                }
                double op2 = (double) stack.pop();
                if (stack.isEmpty()) {
                    throw new RuntimeException("operando with empty stack");
                }
                double op1 = (double) stack.pop();
                double ans = operation(token, op1, op2);
                stack.push(ans);
            }else {
                double num = Double.valueOf(token);
                stack.push(num);
            }
        }
        return stack.peek();
    }

    public String toPostfix() {
        //consigo el infifa
        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        System.out.print("Introduzca la expresion en notacion infija: ");
        String line = inputScanner.nextLine();
        Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");

        String ans = "";
        while (lineScanner.hasNextLine()) {
            String token = lineScanner.next();
                if(isNumeric(token)) {
                    ans += String.format("%s ", token);
                }
                else{
                    //meintras el precedence es true agrega al tope
                    //si es false pushea al current
                    while (!stack2.isEmpty() && getPrecedence(stack2.peek(), token)) {
                        if(!stack2.peek().equals("(") || !stack2.peek().equals(")")) {
                            ans += String.format("%s ", stack2.pop());
                        }
                        else
                            stack2.pop();
                    }
                    if(!token.equals(")"))
                        stack2.push(token);
                    else
                        stack2.pop();
                }
        }
        while (!stack2.isEmpty()) {
            if(!stack2.peek().equals("("))
                ans += String.format("%s ", stack2.pop());
            else
                stack2.pop();
        }
        return ans;
    }

    protected static boolean isNumeric(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public boolean isOperator(String token) {
        return token.matches("[\\+\\-\\*\\/\\^\\(\\)]");
    }

    public static double operation(String exps, double op1, double op2){
        double ans;
        switch(exps){
            case "+": return op1 + op2;
            case "-": return op1 - op2;
            case "*": return op1 * op2;
            case "/": return op1 / op2;
            case "^": return Math.pow(op1, op2);
            default: throw new RuntimeException("Unrecognized operation: " + exps);
        }
    }
}
