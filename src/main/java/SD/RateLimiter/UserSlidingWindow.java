package SD.RateLimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserSlidingWindow {

    Map<Integer, RateLimiter> bucket;

    public UserSlidingWindow(int userId) {
        this.bucket = new HashMap<>();
        bucket.put(userId, new SlidingWindowRateLimiter(1, 10));
    }

    public void accessApplication (int userId) {
        RateLimiter userRateLimiter = bucket.get(userId);
        if (Objects.nonNull(userRateLimiter)) {
            if (userRateLimiter.grantAccess()) {
                System.out.println("Able to access");
            }
            else {
                System.out.println("Too Many Requests");
            }
        }
    }
}
