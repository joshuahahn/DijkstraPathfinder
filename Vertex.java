import java.util.LinkedList;
import java.util.List;

public class Vertex {

    public String name;
    private List<Edge> adjacent;
    public boolean visited;
    public double cost;
    public Vertex backpointer;

    // Default constructor
    public Vertex(String name) {
        this.name = name;
        this.adjacent = new LinkedList<Edge>();
    }

    // Add a new edge
    public void addEdge(Edge e) {
        this.adjacent.add(e);
    }

    // Return neighbors
    public List<Edge> getEdges() {
        return adjacent;
    }

}
