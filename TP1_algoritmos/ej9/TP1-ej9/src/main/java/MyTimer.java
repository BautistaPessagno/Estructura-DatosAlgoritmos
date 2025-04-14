public class MyTimer {

    protected long startTime, endTime;

    public MyTimer(){
        startTime = System.currentTimeMillis();
    }

    public MyTimer(long startTime){
        this.startTime = startTime;
    }

    public void stop(){
        endTime = System.currentTimeMillis();
    }

    public void stop(long endTime){
        this.endTime = endTime;
    }

    public float getMs(){
        return endTime - startTime;
    }

    public double getSecs(){
        double secs = getMs() / (double)1000;
        return secs % 60;
    }

    public int getMins(){
        float mins = (getMs()/1000) / 60;
        return (int) (mins % 60);
    }

    public int getHs(){
        float hs = (getMs()/1000) / (60 * 60);
        return (int) (hs % 24);
    }

    public int getDays(){
        return (int) (getMs()/1000) / (60 * 60 * 24);
    }

    public int getElapsedTime(){
        return (int) getMs();
    }


    @Override
    public String toString(){
        if (endTime < startTime){
            throw new RuntimeException();
        }

        //return "(%l ms) %d día %d hs %d min %f seg".formatted();
        return "(%.3f ms) %d día(s) %d horas %d mins %.3f seg".formatted(getMs(), getDays(), getHs(), getMins(), getSecs());
    }

}
