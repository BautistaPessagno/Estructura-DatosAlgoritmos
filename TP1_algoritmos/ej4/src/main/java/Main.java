import org.joda.time.*;

public class Main {
    public static void main(String[] args) {
        MyTimer t1= new MyTimer();
        MyTimer t2= new MyTimer(6000);

        // bla bla bla
        t1.stop();

        // bla bla bla
        t2.stop(7000);

        System.out.println(t1);
        System.out.println(t2);


        t1= new MyTimer();

        // bla bla bla

        t1.stop(6000);

        t2= new MyTimer(6000);

        // bla bla bla

        t2.stop();

    }
}