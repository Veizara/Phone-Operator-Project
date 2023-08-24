import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PhoneStationManager {
    private PhoneStation line1 = new PhoneStation();
    private PhoneStation line2 = new PhoneStation();
    private Map<Integer, Lock> friendLocks = new HashMap<>();

    public void makeCall(int personID, int friendID) throws InterruptedException {
        PhoneStation[] lines = { line1, line2 };
        Lock friendLock = getFriendLock(friendID); // Retrieve the lock for the friend

        try {
            PhoneStation lineInUse = null;
            friendLock.lock(); // Acquire the lock for the friend
            while (lineInUse == null) {
                for (PhoneStation line : lines) {
                    if (line.getLock().tryLock()) {
                        try {
                            if (line.getLineInUse() == -1) {
                                System.out.println("Person " + personID + " is making a call to Friend " + friendID +
                                        " on side B using Line " + (lines[0] == line ? "1" : "2"));
                                line.setLineInUse(personID);
                                lineInUse = line;
                                break;
                            }
                        } finally {
                            line.getLock().unlock();
                        }
                    }
                }
            }

            Thread.sleep(1000); // Simulate the call duration

            lineInUse.getLock().lock();
            try {
                System.out.println("Person " + personID + " has finished the call on Line " +
                        (lines[0] == lineInUse ? "1" : "2"));
                lineInUse.setLineInUse(-1);
            } finally {
                lineInUse.getLock().unlock();
            }
        } finally {
            friendLock.unlock(); // Release the lock for the friend
        }
    }

    private Lock getFriendLock(int friendID) {
        synchronized (friendLocks) {
            return friendLocks.computeIfAbsent(friendID, id -> new ReentrantLock());
        }
    }
}
