import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {
    static int cnt=0;
    public static void quickSort(int[] arr, int low, int high)
    {

        if (low < high) {
            cnt++;
            int partitionIndex = partition(arr, low, high);
            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
            cnt++;
        }

    }
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[low]; // Pivot is the first element
        int i = low + 1; // Left-to-right scan index
        int j = high;    // Right-to-left scan index

        while (i <= j) {
            // Move `i` to the right until an element > pivot is found
            while (i <= high && arr[i] <= pivot) {
                i++;
            }
            // Move `j` to the left until an element <= pivot is found
            while (j >= low && arr[j] > pivot) {
                j--;
            }
            // Swap arr[i] and arr[j] if i and j haven't crossed
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap pivot element with arr[j] to place pivot in its correct position
        arr[low] = arr[j];
        arr[j] = pivot;

        // Return the partition index where pivot is now located
        return j;
    }


    public static void main(String[] args) {
//       Random scanner = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size:");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println("Original array: "+ Arrays.toString(arr));

        long startTime = System.nanoTime();
        quickSort(arr, 0, arr.length - 1);
        long endTime = System.nanoTime();
        System.out.println("Sorted array: "+Arrays.toString(arr));

        double timeElapsed = (endTime - startTime) / 1e6; //

        System.out.println("Time complexity: " + timeElapsed
                + " milliseconds");
        scanner.close();
//        System.out.println("the count is: "+cnt);

    }
}
