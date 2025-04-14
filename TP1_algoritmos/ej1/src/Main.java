public class Main {
    public static void main(String[] args) {
        MyTimer timer = new MyTimer();

        // bla bla bla ….. aca se invocaría el algoritmo cuyo tiempo de ejecución quiere medirse

        timer.stop(93623040+timer.getStartTime());

        System.out.println(timer);

    }
}