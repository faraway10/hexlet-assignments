package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {
    private int[] arr;
    private Integer result;

    public MinThread(int[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        System.out.println("INFO: Thread " + getName() + " started");
        result = Arrays.stream(arr).min().getAsInt();
        System.out.println("INFO: Thread " + getName() + " finished");
    }

    public Integer getResult() {
        return result;
    }
}
// END
