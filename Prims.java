import java.util.Scanner;

public class Prims {
    public static void main(String[] args) {
        int[][] w = new int[10][10];  // Adjusted array for weights
        int n, i, j, s, k , min, sum = 0, u = 0, v = 0;
        int[] sol = new int[10];  // Track vertices included in MST

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of vertices:");
        n = sc.nextInt();

        // Initialize solution array to mark vertices not in MST (0)
        for (i = 0; i < n; i++)  // Changed loop to start from 0
            sol[i] = 0;

        System.out.println("Enter the weighted graph (use 0 for no edge):");
        for (i = 0; i < n; i++) {  // Changed loop to start from 0
            for (j = 0; j < n; j++) {
                w[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter the source vertex (0 to " + (n-1) + "):");
        s = sc.nextInt();

        sol[s] = 1;  // Mark the source vertex as included in the MST
        k = 1;  // The number of edges included in MST is initially 1 (the source)

        while (k < n) {  // Loop until we have n-1 edges in the MST
            min = Integer.MAX_VALUE;  // Start with a large number to find the minimum edge

            // Find the minimum weight edge from a vertex already in the MST (sol[i] == 1)
            // to a vertex not in the MST (sol[j] == 0)
            for (i = 0; i < n; i++) {  // Changed loop to start from 0
                if (sol[i] == 1) {  // Vertex i is in MST
                    for (j = 0; j < n; j++) {
                        if (sol[j] == 0 && w[i][j] != 0 && w[i][j] < min) {  // Check for edges
                            min = w[i][j];
                            u = i;
                            v = j;
                        }
                    }
                }
            }

            // Include vertex v in the MST
            sol[v] = 1;
            sum += min;  // Add the weight of the selected edge to the total MST cost
            k++;  // Increment the number of edges included in MST

            System.out.println(u + " -> " + v + " = " + min);  // Output the selected edge
        }

        // Print the total cost of the Minimum Spanning Tree
        System.out.println("The cost of the minimum spanning tree is: " + sum);

        sc.close();  // Close the scanner to avoid resource leaks
    }
}
