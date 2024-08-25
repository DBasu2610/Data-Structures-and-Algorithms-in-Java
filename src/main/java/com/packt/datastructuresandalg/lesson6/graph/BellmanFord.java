import java.util.Arrays;

public class BellmanFord {
    class Edge {
        int source, destination, weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    int vertices, edges;
    Edge[] edgeList;

    BellmanFord(int vertices, int edges) {
        this.vertices = vertices;
        this.edges = edges;
        edgeList = new Edge[edges];
    }

    void addEdge(int index, int source, int destination, int weight) {
        edgeList[index] = new Edge(source, destination, weight);
    }

    void bellmanFord(int source) {
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        // Relax all edges |V| - 1 times.
        for (int i = 1; i < vertices; ++i) {
            for (int j = 0; j < edges; ++j) {
                int u = edgeList[j].source;
                int v = edgeList[j].destination;
                int weight = edgeList[j].weight;
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                }
            }
        }

        // Check for negative-weight cycles.
        for (int j = 0; j < edges; ++j) {
            int u = edgeList[j].source;
            int v = edgeList[j].destination;
            int weight = edgeList[j].weight;
            if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }

        printSolution(distance, source);
    }

    void printSolution(int[] distance, int source) {
        System.out.println("Vertex Distance from Source " + source);
        for (int i = 0; i < vertices; ++i)
            System.out.println(i + "\t\t" + distance[i]);
    }

    public static void main(String[] args) {
        int vertices = 5;
        int edges = 8;

        BellmanFord graph = new BellmanFord(vertices, edges);

        graph.addEdge(0, 0, 1, -1);
        graph.addEdge(1, 0, 2, 4);
        graph.addEdge(2, 1, 2, 3);
        graph.addEdge(3, 1, 3, 2);
        graph.addEdge(4, 1, 4, 2);
        graph.addEdge(5, 3, 2, 5);
        graph.addEdge(6, 3, 1, 1);
        graph.addEdge(7, 4, 3, -3);

        graph.bellmanFord(0);
    }
}
