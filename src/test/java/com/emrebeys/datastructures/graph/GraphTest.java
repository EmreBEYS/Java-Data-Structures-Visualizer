package com.emrebeys.datastructures.graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void newGraphShouldBeEmpty() {

        Graph<String> graph =
                new Graph<>(GraphType.UNDIRECTED);

        assertTrue(graph.isEmpty());
        assertEquals(0, graph.vertexCount());
        assertEquals(0, graph.edgeCount());
    }

    @Test
    void graphTypeShouldBeStoredCorrectly() {

        Graph<String> directed =
                new Graph<>(GraphType.DIRECTED);

        Graph<String> undirected =
                new Graph<>(GraphType.UNDIRECTED);

        assertEquals(
                GraphType.DIRECTED,
                directed.getGraphType()
        );

        assertEquals(
                GraphType.UNDIRECTED,
                undirected.getGraphType()
        );
    }

    @Test
    void nullGraphTypeShouldThrowException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Graph<String>(null)
        );
    }

    @Test
    void addVertexShouldAddVertex() {

        Graph<String> graph =
                new Graph<>(GraphType.UNDIRECTED);

        assertTrue(graph.addVertex("A"));

        assertEquals(1, graph.vertexCount());
        assertTrue(graph.containsVertex("A"));
    }

    @Test
    void duplicateVertexShouldNotBeAdded() {

        Graph<String> graph =
                new Graph<>(GraphType.UNDIRECTED);

        assertTrue(graph.addVertex("A"));
        assertFalse(graph.addVertex("A"));

        assertEquals(1, graph.vertexCount());
    }

    @Test
    void graphShouldSupportNullVertex() {

        Graph<String> graph =
                new Graph<>(GraphType.UNDIRECTED);

        assertTrue(graph.addVertex(null));
        assertTrue(graph.containsVertex(null));

        assertEquals(1, graph.vertexCount());
    }

    @Test
    void removeVertexShouldRemoveExistingVertex() {

        Graph<String> graph =
                new Graph<>(GraphType.UNDIRECTED);

        graph.addVertex("A");
        graph.addVertex("B");

        assertTrue(graph.removeVertex("A"));

        assertFalse(graph.containsVertex("A"));
        assertEquals(1, graph.vertexCount());
    }

    @Test
    void removeUnknownVertexShouldReturnFalse() {

        Graph<String> graph =
                new Graph<>(GraphType.UNDIRECTED);

        graph.addVertex("A");

        assertFalse(graph.removeVertex("X"));

        assertEquals(1, graph.vertexCount());
    }

    @Test
    void directedEdgeShouldBeAddedOnlyOneWay() {

        Graph<String> graph =
                new Graph<>(GraphType.DIRECTED);

        graph.addVertex("A");
        graph.addVertex("B");

        assertTrue(graph.addEdge("A", "B"));

        assertTrue(graph.containsEdge("A", "B"));
        assertFalse(graph.containsEdge("B", "A"));

        assertEquals(1, graph.edgeCount());
    }

    @Test
    void undirectedEdgeShouldBeAddedBothWays() {

        Graph<String> graph =
                new Graph<>(GraphType.UNDIRECTED);

        graph.addVertex("A");
        graph.addVertex("B");

        assertTrue(graph.addEdge("A", "B"));

        assertTrue(graph.containsEdge("A", "B"));
        assertTrue(graph.containsEdge("B", "A"));

        // Mantıksal olarak tek edge bulunur.
        assertEquals(1, graph.edgeCount());
    }

    @Test
    void edgeShouldNotBeAddedWhenSourceDoesNotExist() {

        Graph<String> graph =
                new Graph<>(GraphType.DIRECTED);

        graph.addVertex("B");

        assertFalse(graph.addEdge("A", "B"));

        assertEquals(0, graph.edgeCount());
    }

    @Test
    void edgeShouldNotBeAddedWhenDestinationDoesNotExist() {

        Graph<String> graph =
                new Graph<>(GraphType.DIRECTED);

        graph.addVertex("A");

        assertFalse(graph.addEdge("A", "B"));

        assertEquals(0, graph.edgeCount());
    }

    @Test
    void duplicateEdgeShouldNotBeAdded() {

        Graph<String> graph =
                new Graph<>(GraphType.DIRECTED);

        graph.addVertex("A");
        graph.addVertex("B");

        assertTrue(graph.addEdge("A", "B"));
        assertFalse(graph.addEdge("A", "B"));

        assertEquals(1, graph.edgeCount());
    }

    @Test
    void weightedEdgeShouldStoreWeight() {

        Graph<String> graph =
                new Graph<>(GraphType.DIRECTED);

        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge(
                "A",
                "B",
                25.5
        );

        assertEquals(
                25.5,
                graph.getEdgeWeight("A", "B")
        );
    }

    @Test
    void defaultEdgeWeightShouldBeOne() {

        Graph<String> graph =
                new Graph<>(GraphType.DIRECTED);

        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B");

        assertEquals(
                1.0,
                graph.getEdgeWeight("A", "B")
        );
    }

    @Test
    void getEdgeWeightShouldReturnNullForUnknownEdge() {

        Graph<String> graph =
                new Graph<>(GraphType.DIRECTED);

        graph.addVertex("A");
        graph.addVertex("B");

        assertNull(
                graph.getEdgeWeight("A", "B")
        );
    }

    @Test
    void removeDirectedEdgeShouldRemoveOnlySpecifiedDirection() {

        Graph<String> graph =
                new Graph<>(GraphType.DIRECTED);

        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B");
        graph.addEdge("B", "A");

        assertTrue(
                graph.removeEdge("A", "B")
        );

        assertFalse(
                graph.containsEdge("A", "B")
        );

        assertTrue(
                graph.containsEdge("B", "A")
        );

        assertEquals(1, graph.edgeCount());
    }

    @Test
    void removeUndirectedEdgeShouldRemoveBothDirections() {

        Graph<String> graph =
                new Graph<>(GraphType.UNDIRECTED);

        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B");

        assertTrue(
                graph.removeEdge("A", "B")
        );

        assertFalse(
                graph.containsEdge("A", "B")
        );

        assertFalse(
                graph.containsEdge("B", "A")
        );

        assertEquals(0, graph.edgeCount());
    }

    @Test
    void removeUnknownEdgeShouldReturnFalse() {

        Graph<String> graph =
                new Graph<>(GraphType.UNDIRECTED);

        graph.addVertex("A");
        graph.addVertex("B");

        assertFalse(
                graph.removeEdge("A", "B")
        );

        assertEquals(0, graph.edgeCount());
    }

    @Test
    void getNeighborsShouldReturnDirectNeighbors() {

        Graph<String> graph =
                new Graph<>(GraphType.UNDIRECTED);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");

        assertEquals(
                List.of("B", "C"),
                graph.getNeighbors("A")
        );
    }

    @Test
    void getNeighborsShouldReturnEmptyListForUnknownVertex() {

        Graph<String> graph =
                new Graph<>(GraphType.UNDIRECTED);

        assertTrue(
                graph.getNeighbors("A").isEmpty()
        );
    }

    @Test
    void bfsShouldTraverseGraphInBreadthFirstOrder() {

        Graph<String> graph =
                createTraversalGraph();

        assertEquals(
                List.of(
                        "A",
                        "B",
                        "C",
                        "D",
                        "E"
                ),
                graph.bfs("A")
        );
    }

    @Test
    void dfsShouldTraverseGraphInDepthFirstOrder() {

        Graph<String> graph =
                createTraversalGraph();

        assertEquals(
                List.of(
                        "A",
                        "B",
                        "D",
                        "C",
                        "E"
                ),
                graph.dfs("A")
        );
    }

    @Test
    void bfsShouldReturnEmptyListForUnknownStartVertex() {

        Graph<String> graph =
                new Graph<>(GraphType.UNDIRECTED);

        graph.addVertex("A");

        assertTrue(
                graph.bfs("X").isEmpty()
        );
    }

    @Test
    void dfsShouldReturnEmptyListForUnknownStartVertex() {

        Graph<String> graph =
                new Graph<>(GraphType.UNDIRECTED);

        graph.addVertex("A");

        assertTrue(
                graph.dfs("X").isEmpty()
        );
    }

    @Test
    void traversalShouldHandleCyclesWithoutInfiniteLoop() {

        Graph<String> graph =
                new Graph<>(GraphType.DIRECTED);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");

        assertEquals(
                List.of("A", "B", "C"),
                graph.bfs("A")
        );

        assertEquals(
                List.of("A", "B", "C"),
                graph.dfs("A")
        );
    }

    @Test
    void traversalShouldOnlyVisitReachableVertices() {

        Graph<String> graph =
                new Graph<>(GraphType.UNDIRECTED);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");

        assertEquals(
                List.of("A", "B"),
                graph.bfs("A")
        );

        assertEquals(
                List.of("A", "B"),
                graph.dfs("A")
        );

        assertFalse(
                graph.bfs("A").contains("C")
        );
    }

    @Test
    void removingVertexShouldRemoveConnectedEdges() {

        Graph<String> graph =
                new Graph<>(GraphType.UNDIRECTED);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");

        assertEquals(2, graph.edgeCount());

        graph.removeVertex("B");

        assertFalse(
                graph.containsVertex("B")
        );

        assertFalse(
                graph.containsEdge("A", "B")
        );

        assertFalse(
                graph.containsEdge("C", "B")
        );

        assertEquals(0, graph.edgeCount());
    }

    @Test
    void directedIncomingEdgesShouldBeRemovedWithVertex() {

        Graph<String> graph =
                new Graph<>(GraphType.DIRECTED);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("C", "B");

        graph.removeVertex("B");

        assertEquals(0, graph.edgeCount());

        assertFalse(
                graph.containsEdge("A", "B")
        );

        assertFalse(
                graph.containsEdge("C", "B")
        );
    }

    @Test
    void selfLoopShouldBeSupported() {

        Graph<String> graph =
                new Graph<>(GraphType.UNDIRECTED);

        graph.addVertex("A");

        assertTrue(
                graph.addEdge("A", "A")
        );

        assertTrue(
                graph.containsEdge("A", "A")
        );

        assertEquals(1, graph.edgeCount());
    }

    @Test
    void selfLoopShouldBeRemovedCorrectly() {

        Graph<String> graph =
                new Graph<>(GraphType.UNDIRECTED);

        graph.addVertex("A");
        graph.addEdge("A", "A");

        assertTrue(
                graph.removeEdge("A", "A")
        );

        assertFalse(
                graph.containsEdge("A", "A")
        );

        assertEquals(0, graph.edgeCount());
    }

    @Test
    void clearShouldRemoveAllVerticesAndEdges() {

        Graph<String> graph =
                new Graph<>(GraphType.UNDIRECTED);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");

        graph.clear();

        assertTrue(graph.isEmpty());

        assertEquals(
                0,
                graph.vertexCount()
        );

        assertEquals(
                0,
                graph.edgeCount()
        );
    }

    @Test
    void graphShouldBeReusableAfterClear() {

        Graph<String> graph =
                new Graph<>(GraphType.DIRECTED);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        graph.clear();

        graph.addVertex("X");
        graph.addVertex("Y");
        graph.addEdge("X", "Y");

        assertEquals(
                2,
                graph.vertexCount()
        );

        assertEquals(
                1,
                graph.edgeCount()
        );

        assertTrue(
                graph.containsEdge("X", "Y")
        );

        assertFalse(
                graph.containsVertex("A")
        );
    }

    /**
     * BFS ve DFS testlerinde kullanılan ortak Graph yapısını oluşturur.
     *
     * Graph:
     *
     * A -- B
     * |    |
     * C -- D
     *      |
     *      E
     */
    private Graph<String> createTraversalGraph() {

        Graph<String> graph =
                new Graph<>(GraphType.UNDIRECTED);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");

        return graph;
    }
}
