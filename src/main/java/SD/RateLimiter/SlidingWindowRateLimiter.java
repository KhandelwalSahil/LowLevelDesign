package SD.RateLimiter;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowRateLimiter implements RateLimiter {

    Queue<Long> slidingWindow;
    int bucketCapacity;
    long time; // time window which is allowed in minutes

    public SlidingWindowRateLimiter(int bucketCapacity, long time) {
        this.slidingWindow = new ConcurrentLinkedQueue<>();
        this.bucketCapacity = bucketCapacity;
        this.time = time*60000;
    }

    @Override
    public boolean grantAccess() {
        long currentTime = System.currentTimeMillis();
        updateQueue(currentTime);
        if (slidingWindow.size() < this.bucketCapacity) {
            slidingWindow.offer(currentTime);
            return true;
        }
        return false;
    }

    private void updateQueue(long currentTime) {
        if (slidingWindow.isEmpty()) {
            return;
        }

        long time = (currentTime - slidingWindow.peek())/1000;
        while (time >= this.time) {
            slidingWindow.poll();
            if (slidingWindow.isEmpty()) {
                return;
            }
            time = (currentTime - slidingWindow.peek())/1000;
        }
    }
}
