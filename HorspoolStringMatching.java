import java.util.Scanner;

public class HorspoolStringMatching {

    // Method to create the shift table
    public static int[] shiftTable(String pattern) {
        int[] table = new int[256]; // Shift table for all possible ASCII characters
        int m = pattern.length();

        // Initialize all table entries to the length of the pattern
        for (int i = 0; i < 256; i++) {
            table[i] = m;
        }

        // Update the table based on the pattern
        for (int i = 0; i < m - 1; i++) {
            table[pattern.charAt(i)] = m - 1 - i;
        }
        return table;
    }

    // Horspool's search method
    public static int horspoolSearch(String text, String pattern) {
        int[] table = shiftTable(pattern);
        int m = pattern.length();
        int n = text.length();

        int i = m - 1; // Start comparing from the end of the pattern

        while (i < n) {
            int k = 0;

            // Compare the pattern with text from the rightmost end
            while (k < m && pattern.charAt(m -1 - k) == text.charAt(i - k)) {
                k++;
            }

            // If a match is found
            if (k == m) {
                return i - m + 1; // Return the starting index of the match
            } else {
                i += table[text.charAt(i)]; // Shift according to the shift table
            }
        }

        return -1; // Pattern not found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input text and pattern
        System.out.print("Enter the text: ");
        String text = scanner.nextLine();

        System.out.print("Enter the pattern to search for: ");
        String pattern = scanner.nextLine();

        // Measure time complexity
        long startTime = System.nanoTime();
        int index = horspoolSearch(text, pattern);
        long endTime = System.nanoTime();

        // Display result
        if (index != -1) {
            System.out.println("Pattern found at index: " + index);
        } else {
            System.out.println("Pattern not found.");
        }

        double timeElapsed = (endTime - startTime) / 1e6; // Convert nanoseconds to milliseconds
        System.out.println("Time complexity: " + timeElapsed + " milliseconds");

        scanner.close();
    }
}