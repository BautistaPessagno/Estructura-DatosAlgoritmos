public class Main {
    public static void main(String[] args) {
       StringEval se = new StringEval();
        System.out.println(se.evaluate("AA BB CC DEF ^ * AE / + BC -"));
        System.out.println(se.evaluate("HOLA QUE + TAL COMO ^ ESTAS / BIEN * + BIEN -"));
    }
}