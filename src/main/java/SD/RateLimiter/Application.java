package SD.RateLimiter;

import org.apache.catalina.User;

public class Application {

    public static void main(String[] args) {
        UserSlidingWindow userSlidingWindow = new UserSlidingWindow(1234);
        userSlidingWindow.accessApplication(1234); // Granted
        userSlidingWindow.accessApplication(1234); // Not Granted
    }
}
