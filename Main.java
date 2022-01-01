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
            File inputFile = new File("input.txt");
            Scanner sc = new Scanner(inputFile);
            int numEdges = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < numEdges; i++) {
                // If either cities have not been seen before, add them to list of vertices
                // Create new edge for each line
            }
            sc.close();
        }

        // Error handling
        catch (FileNotFoundException e) {
            System.out.println("Input file should be named 'input.txt'. File not found.");
        }

    }
}