import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ArraySorter {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        int numArrays = inputScanner.nextInt();
        inputScanner.nextLine(); // Consume newline left-over

        ArrayList<int[]> arrays = new ArrayList<>();

        for (int i = 0; i < numArrays; i++) {
            String inputLine = inputScanner.nextLine();
            inputLine = inputLine.replaceAll("\\[|\\]|\\s", "");
            String[] inputElements = inputLine.split(",");

            int[] array = new int[inputElements.length];
            for (int j = 0; j < inputElements.length; j++) {
                array[j] = Integer.parseInt(inputElements[j]);
            }

            arrays.add(removeMultiplesOfFiveAndSort(array));
        }

        sortByLength(arrays);

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
        selectionSort(result);
        return result;
    }

    protected static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    protected static void sortByLength(ArrayList<int[]> arrays) {
        for (int i = 1; i < arrays.size(); i++) {
            int[] key = arrays.get(i);
            int j = i - 1;
            while (j >= 0 && arrays.get(j).length < key.length) {
                arrays.set(j + 1, arrays.get(j));
                j--;
            }
            arrays.set(j + 1, key);
        }
    }


}
