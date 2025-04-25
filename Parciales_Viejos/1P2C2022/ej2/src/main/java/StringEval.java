import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class StringEval {
    private final static LinkedList<String> stack = new LinkedList<>();

    public String evaluate(String expression){
        Scanner inputScanner = new Scanner(expression);
        String line = inputScanner.nextLine();
        Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");

        while (lineScanner.hasNext()) {
            String token = lineScanner.next();
            if(isString(token)) {
                String s = token;
                stack.push(s);
            }
            else if (isOperator(token)) {
                if (stack.isEmpty()) {
                    throw new RuntimeException("operando with empty stack");
                }
                String op2 = (String) stack.pop();
                if (stack.isEmpty()) {
                    throw new RuntimeException("operando with empty stack");
                }
                String op1 = (String) stack.pop();
                String ans = operation(token, op1, op2);
                stack.push(ans);
            }
        }
        return stack.peek();
    }

    public boolean isOperator(String token) {
        return token.matches("[+\\-*/^]");
    }

    public boolean isString(String token) {
        return token.matches("[a-zA-Z]+");
    }

    public static String operation(String exps, String op1, String op2){
        String ans;
        switch(exps){
            case "+": {
                ans = op1 + op2;
                return ans;
            }
            case "-":{
                int index = op1.indexOf(op2);
                if (index != -1) {
                    ans = op1.substring(0, index) + op1.substring(index + op2.length());
                } else {
                    ans = op1; // If op2 is not found in op1, return op1 unchanged
                }
                return ans;
            }
            case "*":{
                StringBuilder result = new StringBuilder();
                                int maxLength = Math.max(op1.length(), op2.length());
                                for (int i = 0; i < maxLength; i++) {
                                    if (i < op1.length()) {
                                        result.append(op1.charAt(i));
                                    }
                                    if (i < op2.length()) {
                                        result.append(op2.charAt(i));
                                    }
                                }
                                ans = result.toString();
                                return ans;
            }
            case "/": {
                StringBuilder result = new StringBuilder(op1);
                                for (char c : op2.toCharArray()) {
                                    int i;
                                    while ((i = result.indexOf(String.valueOf(c))) != -1) {
                                        result.deleteCharAt(i);
                                    }
                                }
                                ans = result.toString();
                                return ans;
            }
            case "^": {
                StringBuilder result = new StringBuilder();
                for (int i = 1; i <= op2.length(); i++) {
                    String prefix = op2.substring(0, i);
                    result.append(op1).append(prefix);
                }
                ans = result.toString();
                return ans;
            }
            default: throw new RuntimeException("Unrecognized operation: " + exps);
        }
    }

}
