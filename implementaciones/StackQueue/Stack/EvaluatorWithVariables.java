import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EvaluatorWithVariables extends Evaluator {
    private Map<String, Double> variables;

    public EvaluatorWithVariables(Map<String, Double> variables) {
        this.variables = variables;
    }

    public double evaluate() {
        // uso el scanner para conseguir la expresion
        String postfija = toPostfix();

        System.out.println("postfija: " + postfija);

        // uso el scanner para conseguir la expresion
        Scanner lineScanner = new Scanner(postfija).useDelimiter("\\s+");

        while (lineScanner.hasNext()) {
            String token = lineScanner.next();
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^")) {
                if (getStack().isEmpty()) {
                    throw new RuntimeException("operando with empty stack");
                }
                double op2 = (double) getStack().pop();
                if (getStack().isEmpty()) {
                    throw new RuntimeException("operando with empty stack");
                }
                double op1 = (double) getStack().pop();
                double ans = operation(token, op1, op2);
                getStack().push(ans);
            } else {
                if (variables.containsKey(token)) {
                    getStack().push(variables.get(token));
                }else if (!isNumeric(token)) {
                    throw new RuntimeException("Unknown variable: " + token);
                }
                else {
                    double num = Double.valueOf(token);
                    getStack().push(num);
                }
            }
        }
        return getStack().peek();
    }

    @Override
    public String toPostfix(){
    //consigo el infifa
        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        System.out.print("Introduzca la expresion en notacion infija: ");
        String line = inputScanner.nextLine();
        Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");

        String ans = "";
        while (lineScanner.hasNextLine()) {
            String token = lineScanner.next();
            if(isNumeric(token) || variables.containsKey(token)) {
                ans += String.format("%s ", token);
            }
            else{
                while (!getStack2().isEmpty() && getPrecedence(getStack2().peek(), token)) {
                    if(!getStack2().peek().equals("(") || !getStack2().peek().equals(")")) {
                        ans += String.format("%s ", getStack2().pop());
                    }
                    else
                        getStack2().pop();
                }
                if(!token.equals(")"))
                    getStack2().push(token);
                else
                    getStack2().pop();
            }
        }
        while (!getStack2().isEmpty()) {
            if(!getStack2().peek().equals("("))
                ans += String.format("%s ", getStack2().pop());
            else
                getStack2().pop();
        }
        return ans;
    }

}
