import org.joda.time.*;


public class NativeTimer {
    private org.joda.time.Instant startTime, endTime;
    private org.joda.time.Period elapsedTime;

    public NativeTimer(){
        startTime = org.joda.time.Instant.now();
    }

    public NativeTimer(long start){
        this.startTime = new org.joda.time.Instant(start);
    }

    public org.joda.time.Instant getStartTime(){
        return startTime;
    }

    public void stop(){
        this.endTime = org.joda.time.Instant.now();
        checkDuration();
    }

    public void stop(long stop) {
        this.endTime = new Instant(stop);
        checkDuration();
    }

    public long getDuration(){
        return elapsedTime.toStandardDuration().getMillis();
    }

    public void checkDuration(){
        if(endTime.isBefore(startTime)){
            throw new IllegalArgumentException("End time must be after start time");
        }
        elapsedTime = new Period(startTime, endTime);
    }

    public int getDays(){
        return elapsedTime.getDays();
    }

    public int getHours(){
        return elapsedTime.getHours();
    }

    public int getMinutes(){
        return elapsedTime.getMinutes();
    }

    public int getSeconds(){
        return elapsedTime.getSeconds();
    }



    public String toString(){
        return "(%d ms) %d dia(s) %d hs %d min %d s".formatted(
                (this.getDuration()),
                this.getDays(),
                this.getHours(),
                this.getMinutes(),
                this.getSeconds()
        );
    }


}
