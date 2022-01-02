public class CostVertex {

    // CostVertex is a wrapper class for Vertex and cost.
    public Vertex vertex;
    public Double cost;

    // Default constructor
    public CostVertex(Vertex vertex, Double cost) {
        this.vertex = vertex;
        this.cost = cost;
    }
}
