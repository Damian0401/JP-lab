package threads.utils;

public abstract class RunnableAndFinishable implements Runnable{
    protected volatile boolean isFinished = false;
    public void finish(){
        isFinished = true;
    }
    protected void delay(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isFinished() {
        return isFinished;
    }
}
