import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PhoneStation {
    private Lock lock = new ReentrantLock();
    private int lineInUse = -1;

    public Lock getLock() {
        return lock;
    }

    public int getLineInUse() {
        return lineInUse;
    }

    public void setLineInUse(int lineInUse) {
        this.lineInUse = lineInUse;
    }
}
