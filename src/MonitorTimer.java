/**
 *
 */
public class MonitorTimer extends AbstractTimer {
    private boolean pauseFlag;

    public MonitorTimer() {
        super();
        pauseFlag = false;
    }

    @Override
    public synchronized void setPause(boolean pauseState) throws InterruptedException {
        pauseFlag = pauseState;
    }

    @Override
    public void run() {
        while (!stopFlag) {
            try {
                do Thread.sleep(500);
                while (pauseFlag);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                currentTime++;
            }
        }
    }
}
