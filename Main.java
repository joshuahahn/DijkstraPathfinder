import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

                // Create new vertices
                Vertex sourceV = new Vertex(source);
                Vertex targetV = new Vertex(target);

                if (!graph.getVertices().contains(sourceV))
                    graph.addVertex(sourceV);
                if (!graph.getVertices().contains(targetV))
                    graph.addVertex(targetV);

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

        catch (NumberFormatException e) {
            System.out.println("Input file has incorrectly formatted costs.");
        }

        // Prompt user to enter a source and target they wish to
        // find the shortest distance between.

        // TODO: Run a while loop. Dijkstra should be a helper function.

        // Calculate Dijkstra

        // TODO

        // Print out results

        // TODO
    }
}