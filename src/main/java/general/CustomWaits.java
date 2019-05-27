package general;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriverException;

public class CustomWaits {

    private static Logger logger = LogManager.getLogger(CustomWaits.class);

    public static void assertEventually(int numberOfAttemps, long pauseInMiliSecongs, Runnable runnable) {
        for (int i = 0; i < numberOfAttemps; i++) {
            try {
                runnable.run();
            } catch (AssertionError | WebDriverException e) {
                logger.error("Assertion attempt = " + i + e.getMessage());
            }
            try {
                Thread.sleep(pauseInMiliSecongs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
