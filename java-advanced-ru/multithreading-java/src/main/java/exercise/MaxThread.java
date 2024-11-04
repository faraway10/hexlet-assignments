package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread {
    private int[] arr;
    private Integer result;

    public MaxThread(int[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        result = Arrays.stream(arr).max().getAsInt();
    }

    public Integer getResult() {
        return result;
    }
}
// END
