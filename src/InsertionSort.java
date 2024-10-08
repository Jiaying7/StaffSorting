public class InsertionSort {
    //使用迭代实现快速排序，防止堆栈溢出
    public static void quickInsertionSort(Staff[] staffs, int low, int high, String sortBy) {
        int threshold = 16;
        int[] stack = new int[high - low + 1];
        int top = -1;

        stack[++top] = low;
        stack[++top] = high;

        while (top >= 0) {
            high = stack[top--];
            low = stack[top--];

            if (high - low <= threshold) {
                insertionSort(staffs, low, high, sortBy);
            } else {
                int pivotIndex = partition(staffs, low, high, sortBy);

                if (pivotIndex - 1 > low) {
                    stack[++top] = low;
                    stack[++top] = pivotIndex - 1;
                }

                if (pivotIndex + 1 < high) {
                    stack[++top] = pivotIndex + 1;
                    stack[++top] = high;
                }
            }
        }
    }

    public static void insertionSort(Staff[] staffs, int low, int high, String sortBy) {
        for (int i = low + 1; i <= high; i++) {
            Staff temp = staffs[i];
            int j = i - 1;
            while (j >= low && staffs[j].compare(temp, sortBy) > 0) {
                staffs[j + 1] = staffs[j];
                j--;
            }
            staffs[j + 1] = temp;
        }
    }

    public static int partition(Staff[] staffs, int low, int high, String sortBy) {
        Staff pivot = staffs[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (staffs[j].compare(pivot, sortBy) <= 0) {
                i++;
                Staff temp = staffs[i];
                staffs[i] = staffs[j];
                staffs[j] = temp;
            }
        }
        Staff temp = staffs[i + 1];
        staffs[i + 1] = staffs[high];
        staffs[high] = temp;
        return i + 1;
    }
}
