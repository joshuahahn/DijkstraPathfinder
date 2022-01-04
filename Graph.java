import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Graph {

    private Map<String, Vertex> vertices;

    // Default constructor
    public Graph() {
        vertices = new HashMap<String, Vertex>();
    }

    // Add a vertex to the graph
    public void addVertex(Vertex v) {
        if (vertices.containsKey(v.name)) {
            throw new IllegalArgumentException("Duplicate vertex entry.");
        }
        vertices.put(v.name, v);
    }

    // Vertices getter function
    public Map<String, Vertex> getVertices() {
        return vertices;
    }

}
