import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Initialize graph
        Graph graph = new Graph();

        /*
         * / Read in data and create appropriate vertices and edges.
         * / File format (expected name: "input.txt"):
         * / Number of available paths
         * / Start1 End1 Distance(cost)
         * / Start2 End2 Distance(cost)
         * / ...
         */
        try {
            // Text input handling
            File inputFile = new File("input.txt");
            Scanner sc = new Scanner(inputFile);

            int numEdges = Integer.parseInt(sc.nextLine());

            // Process every new edge
            for (int i = 0; i < numEdges; i++) {

                // Clean data
                String nextLine = sc.nextLine();
                String[] contents = nextLine.split(" ");
                String source = contents[0];
                String target = contents[1];
                Double cost = Double.parseDouble(contents[2]);

                Vertex sourceV;
                Vertex targetV;

                // If source vertex does not exist in graph yet
                if (!graph.getVertices().containsKey(source)) {
                    sourceV = new Vertex(source);
                    graph.addVertex(sourceV);
                }
                // If source vertex already exists in graph.
                else {
                    sourceV = graph.getVertices().get(source);
                }

                // If target vertex does not exist in graph yet
                if (!graph.getVertices().containsKey(target)) {
                    targetV = new Vertex(target);
                    graph.addVertex(targetV);
                }

                // If target vertex already exists in graph.
                else {
                    targetV = graph.getVertices().get(source);
                }

                // Assume all paths are bilateral, add both edges.
                Edge sourceToTarget = new Edge(sourceV, targetV, cost);
                Edge targetToSource = new Edge(targetV, sourceV, cost);

                sourceV.addEdge(sourceToTarget);
                targetV.addEdge(targetToSource);

            }
            sc.close();
        }

        // Error handling: file does not exist
        catch (FileNotFoundException e) {
            System.out.println("Input file should be named 'input.txt'. File not found.");
        }

        // catch (NumberFormatException e) {
        // System.out.println("Input file has incorrectly formatted costs.");
        // }

        // Take input from user.
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the source.");
        String sourceName = sc.nextLine();
        System.out.println("Please enter the target.");
        String targetName = sc.nextLine();

        boolean validSourceTarget = checkValid(graph, sourceName, targetName);

        // Error handling
        if (!validSourceTarget)
            System.out.println("Please enter a valid source & target.");

        if (validSourceTarget) {
            // Perform and print out Dijkstra.
            System.out.println(
                    doDijkstra(graph, graph.getVertices().get(sourceName), graph.getVertices().get(targetName)));

        }

        sc.close();
    }

    /*
     * / Returns the list of cities to travel to in order, as well as total cost.
     * / @param graph The graph to run Dijkstra's algorithm on
     * / @param source The source vertex.
     * / @param target The target vertex.
     * / @return A formatted string of the cities in order and the total cost.
     */
    public static String doDijkstra(Graph graph, Vertex source, Vertex target) {

        // Step 1: Annotate all vertices with appropriate annotations
        for (Vertex v : graph.getVertices().values()) {
            v.cost = Double.POSITIVE_INFINITY;
            v.visited = false;
            v.backpointer = null;
        }

        // Step 2: Iterate through all edges until all vertices are found.
        PriorityQueue<CostVertex> queue = new PriorityQueue<>();

        // Set source cost to 0 and add it to queue.
        graph.getVertices().get(source.name).cost = 0;
        CostVertex sourceCV = new CostVertex(source, 0.0);
        queue.offer(sourceCV);

        // Iterate through highest priority vertex
        while (queue.size() > 0) {

            // Set current vertex as lowest cost (highest priority) vertex.
            Vertex current = graph.getVertices().get(queue.poll().name());

            // Set visited to true.
            current.visited = true;

            // Iterate through all neighbors of current.
            for (Edge edge : current.getEdges()) {

                // Declare neighbor vertex
                Vertex neighbor = edge.target;

                // If neighbor is not visited yet (never accessed)
                if (!neighbor.visited) {

                    // Cost to get to neighbor passing through current vertex
                    double c = current.cost + edge.distance;

                    // If this path is the shortest available path
                    if (c < neighbor.cost) {

                        // Update values
                        neighbor.cost = c;
                        neighbor.backpointer = current;

                        // Add neighbor to the priorityqueue.
                        CostVertex neighborEntry = new CostVertex(neighbor, c);
                        queue.offer(neighborEntry);
                    }
                }

            }

        }

        for (Vertex v : graph.getVertices().values()) {
            System.out.print(v.name + " ");
            System.out.println(v.cost);
        }

        // Step 3: Print path from source to target.
        ArrayList<Edge> result = new ArrayList<>();

        // Edge case: source = target
        if (graph.getVertices().get(source.name) == graph.getVertices().get(target.name)) {
            return source.name;
        }

        Vertex current = graph.getVertices().get(source.name);

        // Start from target, then move backwards to source. Flip at the end.
        while (current.backpointer != null) {

            Vertex previous = current.backpointer;

            // Add edge conncting current to previous to arraylist.
            Edge newEdge = new Edge(current, previous);
            result.add(newEdge);

            // Update current
            current = current.backpointer;
        }

        // Reverse the arraylist by treating a new arraylist as a stack.
        ArrayList<Edge> stack = new ArrayList<>();

        for (Edge edge : result) {
            stack.add(edge);
        }

        int idx = stack.size() - 1;

        String resultString = source.name;

        // Add the edges back in reverse order.
        while (idx >= 0) {

            resultString += (" " + stack.get(idx).target.name);
        }

        return resultString;
    }

    /*
     * / @param graph The graph to check validity on.
     * / @param source The name of the source Vertex.
     * / @param target The name of the target Vertex.
     * / @return True if source & target are valid, return false otherwise.
     */
    public static boolean checkValid(Graph graph, String source, String target) {
        return (graph.getVertices().containsKey(source) && graph.getVertices().containsKey(target));
    }
}