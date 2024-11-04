import java.util.ArrayList;
import java.util.Scanner;

public class SubSet {
    public static boolean subsetSum(int[] arr, int sum, ArrayList<Integer> subset) {
        int n = arr.length;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true; // A sum of 0 can always be achieved with an empty subset

        for (int i = 0; i < n; i++) {
            for (int j = sum; j >= arr[i]; j--) { // Traverse backwards
                dp[j] = dp[j] || dp[j - arr[i]];
            }
        }

        if (!dp[sum]) {
            return false; // If we can't make the sum, return false
        }

        // If we reach here, it means we can make the sum, now we need to find the subset
        int j = sum;

        for (int i = n - 1; i >= 0; i--) {
            if (j >= arr[i] && dp[j - arr[i]]) {
                subset.add(arr[i]);
                j -= arr[i]; // Reduce j by the element added to the subset
            }
        }

        return true; // A subset with the given sum exists
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        // Input validation
        if (n <= 0) {
            System.out.println("Please enter a positive integer for the number of elements.");
            return;
        }

        int[] arr = new int[n];
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.print("Enter the target sum: ");
        int sum = scanner.nextInt();
        ArrayList<Integer> subset = new ArrayList<>();

        long startTime = System.nanoTime();
        boolean hasSubsetSum = subsetSum(arr, sum, subset);
        long endTime = System.nanoTime();

        System.out.println("Subset sum exists: " + hasSubsetSum);

        if (hasSubsetSum) {
            System.out.println("Subset contributing to the sum: " + subset);
        } else {
            System.out.println("No subset found that adds up to the target sum.");
        }

        double timeElapsed = (endTime - startTime) / 1e6; // Convert nanoseconds to milliseconds
        System.out.println("Execution Time: " + timeElapsed + " milliseconds");
        scanner.close();
    }
}