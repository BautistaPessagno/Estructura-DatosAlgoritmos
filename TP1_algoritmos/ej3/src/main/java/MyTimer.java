public class MyTimer {
    private long startTime;
    private long endTime;

    public MyTimer(){
        startTime = System.currentTimeMillis() ;
    }

    public MyTimer(long startTime){
        this.startTime = startTime;
    }

    public long getStartTime(){
        return startTime;
    }

    public void stop(){
        this.endTime = System.currentTimeMillis();
    }

    public void stop(long endTime){
        this.endTime = endTime;
    }

    public String toString(){
        return "(%d ms) %d dia(s) %d hs %d min %d s".formatted(
                (endTime - startTime),
                (endTime - startTime) / 86_400_000,
                (endTime - startTime) / 3_600_000 % 24,
                (endTime - startTime) / 60_000L % 60,
                (endTime - startTime) / 1_000L % 60
        );
    }


}
