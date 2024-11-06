import java.util.*;

public class DFS {
    private int vertices;
    private List<Integer>[] adjList;

    // Constructor to initialize the graph with a given number of vertices
    public DFS(int v) {
        vertices = v;
        adjList = new LinkedList[v];  // Initialize the adjacency list for each vertex
        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<>();  // Make sure each vertex's list is initialized
        }
    }

    // Method to add an edge between two nodes (directed graph)
    public void addEdge(int v, int w) {
        adjList[v].add(w); // Add the edge from start to end
    }

    // Recursive method to perform DFS traversal
    public void DFSUtil(int v, boolean[] visited) {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");

        // Explore all adjacent vertices recursively
        for (Integer neighbor : adjList[v]) {  // Loop through the list of neighbors
            if (!visited[neighbor]) {  // Visit only unvisited neighbors
                DFSUtil(neighbor, visited);
            }
        }
    }

    // Method to perform DFS traversal starting from a given vertex
    public void DFSTraversal(int startVertex) {
        boolean[] visited = new boolean[vertices];  // Initialize the visited array
        Arrays.fill(visited, false);  // Mark all vertices as unvisited
        System.out.print("DFS traversal starting from vertex " + startVertex + ": ");
        DFSUtil(startVertex, visited);  // Start DFS from the given vertex
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of vertices
        System.out.print("Enter the number of vertices: ");
        int v = scanner.nextInt();

        DFS graph = new DFS(v);

        // Input the edges (for simplicity, undirected graph example)
        System.out.println("Enter the edges (start end): ");
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (scanner.nextInt() == 1) {
                    graph.addEdge(i, j);
                }
            }
        }

        // Input the starting vertex
        System.out.print("Enter the starting vertex for DFS: ");
        int startVertex = scanner.nextInt();

        // Perform DFS traversal from the source vertex
        graph.DFSTraversal(startVertex);

        scanner.close();  // Close the scanner
    }
}