import org.joda.time.*;

public class MyTimer {
    private Instant startTime, endTime;
    private Period elapsedTime;

    public MyTimer(){
        startTime = Instant.now();
    }

    public MyTimer(long start){
        this.startTime = new Instant(start);
    }

    public Instant getStartTime(){
        return startTime;
    }

    public void stop(){
        this.endTime = Instant.now();
        checkDuration();
    }

    public void stop(long stop) {
        this.endTime = new Instant(stop);
        checkDuration();
    }

    public void checkDuration(){
        if(endTime.isBefore(startTime)){
            throw new IllegalArgumentException("End time must be after start time");
        }
        elapsedTime = new Period(startTime, endTime);
    }

    public String toString(){
        return "(%d ms) %d dia(s) %d hs %d min %d s".formatted(
                (elapsedTime.getMillis()),
                elapsedTime.getDays(),
                elapsedTime.getHours(),
                elapsedTime.getMinutes(),
                elapsedTime.getSeconds()
        );
    }


}
