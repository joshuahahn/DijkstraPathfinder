public class Edge {

    public Vertex source;
    public Vertex target;
    public Double distance;

    // Default constructor
    public Edge(Vertex source, Vertex target) {
        this.source = source;
        this.target = target;
        this.distance = 1.0;
    }

    // Constructor with distance
    public Edge(Vertex source, Vertex target, Double distance) {
        this.source = source;
        this.target = target;
        this.distance = distance;
    }
}
