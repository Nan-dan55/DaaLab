import java.util.Scanner;

class TSPExp {
    int[][] weight;
    int[] tour;
    int n, finalCost;
    final int INF = Integer.MAX_VALUE; // Use max int value as a sentinel

    TSPExp() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter no. of nodes:=>");
        n = s.nextInt();

        weight = new int[n][n];
        tour = new int[n]; // Include the starting node in the tour

        // Read weight matrix with input validation
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    System.out.print("Enter weight of " + (i + 1) + " to " + (j + 1) + ":=>");
                    int w = s.nextInt();
                    if (w < 0) {
                        System.out.println("Weight cannot be negative. Please enter a non-negative value.");
                        j--; // Repeat this entry
                    } else {
                        weight[i][j] = w;
                    }
                } else {
                    weight[i][j] = 0; // Distance to itself is zero
                }
            }
        }

        System.out.println();
        System.out.println("Starting node assumed to be node 1.");
        eval();
        s.close();
    }

    public int COST(int currentNode, int[] inputSet, int setSize) {
        if (setSize == 0) {
            return weight[currentNode][0]; // Return to the starting node
        }
        int min = INF;

        for (int i = 0; i < setSize; i++) {
            int[] remainingNodes = new int[setSize - 1];
            int k = 0;

            for (int j = 0; j < setSize; j++) {
                if (inputSet[i] != inputSet[j]) {
                    remainingNodes[k++] = inputSet[j];
                }
            }

            int temp = COST(inputSet[i], remainingNodes, setSize - 1);
            min = Math.min(min, weight[currentNode][inputSet[i]] + temp);
        }
        return min;
    }

    public int MIN(int currentNode, int[] inputSet, int setSize) {
        if (setSize == 0) {
            return -1; // Return -1 if no nodes left
        }
        int min = INF;
        int minIndex = -1; // Initialize to -1

        for (int i = 0; i < setSize; i++) {
            int[] remainingNodes = new int[setSize - 1];
            int k = 0;

            for (int j = 0; j < setSize; j++) {
                if (inputSet[i] != inputSet[j]) {
                    remainingNodes[k++] = inputSet[j];
                }
            }

            int temp = COST(inputSet[i], remainingNodes, setSize - 1);
            if (weight[currentNode][inputSet[i]] + temp < min) {
                min = weight[currentNode][inputSet[i]] + temp;
                minIndex = inputSet[i];
            }
        }
        return minIndex;
    }

    public void eval() {
        int[] dummySet = new int[n - 1];
        for (int i = 1; i < n; i++) {
            dummySet[i - 1] = i;
        }
        finalCost = COST(0, dummySet, n - 1);
        constructTour();
    }

    public void constructTour() {
        int[] previousSet = new int[n - 1];
        int setSize = n - 1;
        for (int i = 1; i < n; i++) {
            previousSet[i - 1] = i;
        }
        tour[0] = 0; // Starting from node 0 (1 in the output)

        for (int i = 1; i < n; i++) {
            tour[i] = MIN(tour[i - 1], previousSet, setSize);
            // Update previousSet to remove the last visited node
            int[] newPreviousSet = new int[setSize - 1];
            int k = 0;
            for (int j = 0; j < setSize; j++) {
                if (previousSet[j] != tour[i]) {
                    newPreviousSet[k++] = previousSet[j];
                }
            }
            previousSet = newPreviousSet;
            setSize--;
        }
        display();
    }

    public void display() {
        System.out.println();
        System.out.print("The tour is 1-");
        for (int i = 1; i < n; i++) {
            System.out.print((tour[i] + 1) + (i < n - 1 ? "-" : ""));
        }
        System.out.println("-1"); // Returning to the starting node
        System.out.println("The final cost is " + finalCost);
    }
}

class TSP {
    public static void main(String[] args) {
        TSPExp obj = new TSPExp();
    }
}
