import java.util.HashSet;

public class Graph {

    HashSet<Vertex> vertices;

    // Default constructor
    public Graph() {
        vertices = new HashSet<Vertex>();
    }

    // Add a vertex to the graph
    public void addVertex(Vertex v) {
        vertices.add(v);
    }

}
