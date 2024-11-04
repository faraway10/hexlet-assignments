package exercise;

import java.util.Map;
import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] arr) {
        var minThread = new MinThread(arr);
        var maxThread = new MaxThread(arr);

        minThread.start();
        maxThread.start();

        try {
            minThread.join();
            maxThread.join();
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
