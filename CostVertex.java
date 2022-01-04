public class CostVertex implements Comparable<CostVertex> {

    // CostVertex is a wrapper class for Vertex and cost.
    public Vertex vertex;
    public Double cost;

    // Default constructor
    public CostVertex(Vertex vertex, Double cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    // Return -1 if this vertex has lower cost, 1 if this vertex has higher cost,
    // 0 if costs are equal.
    public int compareTo(CostVertex other) {
        if (this.cost > other.cost) {
            return 1;
        }

        else if (this.cost < other.cost) {
            return -1;
        }

        return 0;
    }

    // Return name
    public String name() {
        return vertex.name;
    }
}
