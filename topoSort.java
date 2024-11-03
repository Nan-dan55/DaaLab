import java.util.*;

public class topoSort {
    private int V; // Number of vertices
    private List<Integer>[] adjList;

    // Constructor to initialize the number of vertices and adjacency list
    public topoSort(int v) {
        V = v;
        adjList = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Add an edge from vertex v to vertex w
    public void addEdge(int v, int w) {
        adjList[v].add(w);
    }

    // Utility function for topological sort using DFS
    private void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) {
        visited[v] = true; // Mark the current node as visited

        // Recur for all the vertices adjacent to this vertex
        for (Integer neighbor : adjList[v]) {
            if (!visited[neighbor]) {
                topologicalSortUtil(neighbor, visited, stack);
            }
        }

        // Push current vertex to stack which stores the topological order
        stack.push(v);
    }

    // Function to perform topological sort
    public void topologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V]; // Mark all vertices as not visited
        Arrays.fill(visited, false);

        // Call the recursive helper function for each vertex
        for (int i = 0; i < V; ++i) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        // Print the contents of the stack (the topological order)
        System.out.println("Topological Sort:");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of vertices
        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        // Create an instance of TopoSort
        topoSort g = new topoSort(V);

        // Input the adjacency matrix and construct the graph
        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (scanner.nextInt() == 1) {
                    g.addEdge(i, j);  // Add an edge from vertex i to vertex j
                }
            }
        }

        // Perform topological sort and print the result
        g.topologicalSort();

        scanner.close();  // Close the scanner
    }
}