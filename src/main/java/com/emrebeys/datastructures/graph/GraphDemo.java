package com.emrebeys.datastructures.graph;

/**
 * Graph veri yapısının temel kullanım örneklerini gösterir.
 */
public class GraphDemo {

    public static void main(String[] args) {

        System.out.println("=== UNDIRECTED GRAPH DEMO ===");

        Graph<String> graph = new Graph<>(GraphType.UNDIRECTED);

        // Vertex ekleme
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        // Edge ekleme
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");

        System.out.println("\nGraph:");
        System.out.println(graph);

        System.out.println("Vertex sayısı: " + graph.vertexCount());
        System.out.println("Edge sayısı: " + graph.edgeCount());

        // Komşuluk kontrolü
        System.out.println("\nA'nın komşuları: " + graph.getNeighbors("A"));
        System.out.println("D'nin komşuları: " + graph.getNeighbors("D"));

        // BFS
        System.out.println("\nBFS (A):");
        System.out.println(graph.bfs("A"));

        // DFS
        System.out.println("\nDFS (A):");
        System.out.println(graph.dfs("A"));

        // Edge kontrolü
        System.out.println("\nA -> B var mı? "
                + graph.containsEdge("A", "B"));

        System.out.println("A -> E var mı? "
                + graph.containsEdge("A", "E"));

        // Edge silme
        graph.removeEdge("A", "B");

        System.out.println("\nA-B edge silindikten sonra:");
        System.out.println(graph);

        // Vertex silme
        graph.removeVertex("D");

        System.out.println("D vertex'i silindikten sonra:");
        System.out.println(graph);

        /*
         * Ağırlıklı yönlü graph örneği
         */
        System.out.println("=== DIRECTED WEIGHTED GRAPH DEMO ===");

        Graph<String> weightedGraph =
                new Graph<>(GraphType.DIRECTED);

        weightedGraph.addVertex("Malatya");
        weightedGraph.addVertex("Elazığ");
        weightedGraph.addVertex("Adıyaman");
        weightedGraph.addVertex("Kayseri");

        weightedGraph.addEdge(
                "Malatya",
                "Elazığ",
                100.0
        );

        weightedGraph.addEdge(
                "Malatya",
                "Adıyaman",
                110.0
        );

        weightedGraph.addEdge(
                "Malatya",
                "Kayseri",
                340.0
        );

        weightedGraph.addEdge(
                "Elazığ",
                "Malatya",
                100.0
        );

        System.out.println("\nAğırlıklı Graph:");
        System.out.println(weightedGraph);

        System.out.println(
                "Malatya -> Elazığ ağırlığı: "
                        + weightedGraph.getEdgeWeight(
                        "Malatya",
                        "Elazığ"
                )
        );

        System.out.println(
                "Elazığ -> Adıyaman var mı? "
                        + weightedGraph.containsEdge(
                        "Elazığ",
                        "Adıyaman"
                )
        );

        System.out.println(
                "\nDirected BFS: "
                        + weightedGraph.bfs("Malatya")
        );

        System.out.println(
                "Directed DFS: "
                        + weightedGraph.dfs("Malatya")
        );

        // Graph temizleme
        weightedGraph.clear();

        System.out.println("\nClear sonrası:");
        System.out.println(
                "Vertex sayısı: "
                        + weightedGraph.vertexCount()
        );

        System.out.println(
                "Graph boş mu? "
                        + weightedGraph.isEmpty()
        );
    }
}
