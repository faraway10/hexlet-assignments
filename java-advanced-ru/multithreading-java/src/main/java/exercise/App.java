package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] arr) {
        var minThread = new MinThread(arr);
        var maxThread = new MaxThread(arr);

        minThread.start();
        LOGGER.log(Level.INFO, "Thread " + minThread.getName() + " started");
        maxThread.start();
        LOGGER.log(Level.INFO, "Thread " + maxThread.getName() + " started");

        try {
            minThread.join();
            LOGGER.log(Level.INFO, "Thread " + minThread.getName() + " finished");
            maxThread.join();
            LOGGER.log(Level.INFO, "Thread " + maxThread.getName() + " finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Map.of(
                "min", minThread.getResult(),
                "max", maxThread.getResult()
        );
    }
    // END
}
