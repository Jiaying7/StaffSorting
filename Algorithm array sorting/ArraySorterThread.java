import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


//a) b)
public class ArraySorterThread {
    public static void main(String[] args) throws InterruptedException {
        Scanner inputScanner = new Scanner(System.in);
        int numArrays = inputScanner.nextInt();
        inputScanner.nextLine(); // Consume newline left-over

        ArrayList<int[]> arrays = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(numArrays);

        for (int i = 0; i < numArrays; i++) {
            String inputLine = inputScanner.nextLine();
            inputLine = inputLine.replaceAll("\\[|\\]|\\s", "");
            String[] inputElements = inputLine.split(",");

            int[] array = new int[inputElements.length];
            for (int j = 0; j < inputElements.length; j++) {
                array[j] = Integer.parseInt(inputElements[j]);
            }

            Runnable task = new Runnable() {
                @Override
                public void run() {
                    int[] processedArray = removeMultiplesOfFiveAndSort(array);
                    synchronized (arrays) {
                        arrays.add(processedArray);
                    }
                }
            };
            executor.execute(task);
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

        ArraySorter.sortByLength(arrays);

        for (int[] array : arrays) {
            System.out.println(Arrays.toString(array));
        }
    }

    private static int[] removeMultiplesOfFiveAndSort(int[] inputArray) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : inputArray) {
            if (num % 5 != 0) {
                list.add(num);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        ArraySorter.selectionSort(result);
        return result;
    }
}
