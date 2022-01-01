import java.util.HashSet;

public class Graph {

    private HashSet<Vertex> vertices;

    // Default constructor
    public Graph() {
        vertices = new HashSet<Vertex>();
    }

    // Add a vertex to the graph
    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    // Vertices getter function
    public HashSet<Vertex> getVertices() {
        return vertices;
    }

}
