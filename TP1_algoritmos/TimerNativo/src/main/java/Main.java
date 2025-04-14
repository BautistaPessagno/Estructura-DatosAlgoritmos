public class Main {
    public static void main(String[] args) {
        int startTime = 10;
        NativeTimer myCrono = new NativeTimer(startTime);
        myCrono.stop(10 + 93623040);

        System.out.println(myCrono);

    }
}