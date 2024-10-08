import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//c)
public class ArraySorterThreadTest {
    public static void main(String[] args) throws InterruptedException {
        //根据需要调整下方参数，以比较单线程与多线程的运行速度(经过测试，一般数组大小越大，数组数量越少，多线程的提升越明显。反之单线程提升明显）
//        int numArrays = 10;//数组数量
//        int arraySize = 500000;//数组大小

        int numArrays = 1000;//数组数量
        int arraySize = 50;//数组大小

        ArrayList<int[]> arrays = generateRandomArrays(numArrays, arraySize);

        long singleThreadTime = runTest(arrays, false);
        System.out.println("Single-threaded time: " + singleThreadTime + " ms");

        long multiThreadTime = runTest(arrays, true);
        System.out.println("Multi-threaded time: " + multiThreadTime + " ms");
    }

    private static long runTest(ArrayList<int[]> arrays, boolean useMultiThread) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        if (useMultiThread) {
            ExecutorService executor = Executors.newFixedThreadPool(arrays.size());
            ArrayList<int[]> processedArrays = new ArrayList<>();

            for (final int[] array : arrays) {
                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        int[] processedArray = removeMultiplesOfFiveAndSort(array);
                        synchronized (processedArrays) {
                            processedArrays.add(processedArray);
                        }
                    }
                };
                executor.execute(task);
            }

            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } else {
            ArrayList<int[]> processedArrays = new ArrayList<>();
            for (int[] array : arrays) {
                processedArrays.add(removeMultiplesOfFiveAndSort(array));
            }
        }

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private static ArrayList<int[]> generateRandomArrays(int numArrays, int arraySize) {
        ArrayList<int[]> arrays = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numArrays; i++) {
            int[] array = new int[arraySize];
            for (int j = 0; j < arraySize; j++) {
                array[j] = random.nextInt(100) + 1;
            }
            arrays.add(array);
        }

        return arrays;
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
