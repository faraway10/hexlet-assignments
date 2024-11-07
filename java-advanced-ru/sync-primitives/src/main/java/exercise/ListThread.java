package exercise;

import java.util.concurrent.ThreadLocalRandom;

// BEGIN
public class ListThread extends Thread {
    private SafetyList list;

    public ListThread(SafetyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            sleep();
            list.add(ThreadLocalRandom.current().nextInt(-99, 99 + 1));
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
// END
