package lab3;
import java.util.List;

/**
 * Created by artiom on 15.05.14.
 */

import java.util.List;

public class Worker implements Runnable {
    private List<Integer> finalList;
    private int[] array;

    public Worker(int[] array, List<Integer> finalList) {
        this.array = array;
        this.finalList = finalList;
    }

    @Override
    public void run() {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        finalList.add(max);
    }
}
