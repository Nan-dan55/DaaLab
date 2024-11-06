import java.util.Scanner;

public class Floyd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the number of vertices
        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        // Initialize the adjacency matrix
        int[][] graph = new int[vertices][vertices];

        // Get the adjacency matrix from the user (0 for no edge, a large value for no direct path)
        System.out.println("Enter the adjacency matrix (use a large number for no edge, e.g., 9999 for no path):");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        // Floyd-Warshall Algorithm
        for (int k = 0; k < vertices; k++) {
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        // Print the shortest distance matrix
        System.out.println("Shortest Path Matrix:");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (graph[i][j] == 9999) { // No path
                    System.out.print("INF ");
                } else {
                    System.out.print(graph[i][j] + " ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }
}
