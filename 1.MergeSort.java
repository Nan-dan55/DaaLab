import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {
    static int cnt=0;
    public static void mergeSort(int[] arr, int left, int right) {

        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
            cnt++;
        }
    }
    public static void merge(int[] arr, int left, int mid, int right) {
        int i = left; // Starting index for the left subarray
        int j = mid + 1; // Starting index for the right subarray
        int[] temp = new int[right - left + 1]; // Temporary array for merging
        int k = 0;

        // Merge the two subarrays into temp[]
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // Copy remaining elements from the left subarray, if any
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // Copy remaining elements from the right subarray, if any
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // Copy the merged elements back into the original array
        for (i = left, k = 0; i <= right; i++, k++) {
            arr[i] = temp[k];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Random scanner = new Random();
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println("Original array: " + Arrays.toString(arr));
        long startTime = System.nanoTime();
        mergeSort(arr, 0, arr.length - 1);
        long endTime = System.nanoTime();
        System.out.println("Sorted array: " + Arrays.toString(arr));
        double timeElapsed = (endTime - startTime) / 1e6;
        System.out.println("Time complexity: " + timeElapsed + "milliseconds");
        scanner.close();

        System.out.println("The count is: "+cnt);
    }
}
